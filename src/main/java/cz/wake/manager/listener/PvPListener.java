package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.managers.PvPManager;
import cz.wake.manager.objects.pvp.PvPArena;
import cz.wake.manager.objects.pvp.PvPStatistics;
import cz.wake.manager.objects.pvp.events.PlayerDeathInArenaEvent;
import cz.wake.manager.objects.pvp.events.PlayerKilledPlayerEvent;
import cz.wake.manager.objects.pvp.events.PlayerTeleportedToArenaEvent;
import cz.wake.manager.utils.BukkitUtil;
import cz.wake.manager.utils.Log;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class PvPListener implements Listener {

    private static final @Getter List<UUID> godModes = Collections.synchronizedList(new LinkedList<>()); // Radši synchronized, nechceme nikoho aby běhal s nekonečným god-modem, kdyby se stal nějaký exception
    private static final @Getter Map<UUID, BukkitTask> waitingForTeleport = Collections.synchronizedMap(new HashMap<>());
    private static final @Getter Map<UUID, PvPStatistics> pvpStatistics = Collections.synchronizedMap(new HashMap<>());

    public static void enableGodMode(Player player) {
        godModes.add(player.getUniqueId());

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            godModes.remove(player.getUniqueId());
            ChatInfo.INFO.send(player, "Skončila ti nesmrtelnost.");
        }, 40);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Main.getInstance().getPvpManager().isPlayerInAnyArena(player)) {
            sendPvPStatisticsAndRemove(player);
            if (PvPManager.getRespawnLocation() != null) {
                player.teleport(PvPManager.getRespawnLocation(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
                ChatInfo.INFO.send(player, "Byl jsi teleportován na respawn lokaci, jelikož ses odpojil z PvP Arény.");
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Player killer = event.getEntity().getKiller();

        if (!Main.getInstance().getPvpManager().isPlayerInAnyArena(player)) {
            return;
        }

        PvPArena pvpArena = Main.getInstance().getPvpManager().getArenaByWorld(player.getLocation().getWorld());
        BukkitUtil.callEvent(new PlayerDeathInArenaEvent(player, killer, pvpArena, pvpStatistics.getOrDefault(player.getUniqueId(), new PvPStatistics())));

        if (killer != null) {
            BukkitUtil.callEvent(new PlayerKilledPlayerEvent(killer, player));
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (!Main.getInstance().getPvpManager().isPlayerInAnyArena(player)) {
            return;
        }

        Location location = PvPManager.getRespawnLocation();

        if (location == null) {
            Log.withPrefix("Nebyla nastavena respawn lokace pro PvP Arény!!!");
        } else {
            event.setRespawnLocation(location);
        }
    }

    @EventHandler
    public void onPlayerDamageByPlayer(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (godModes.contains(player.getUniqueId())) {
            event.setCancelled(true);
        }

        if (waitingForTeleport.containsKey(player.getUniqueId())) {
            cancelTeleport(player);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (!(event.getDamager() instanceof Player damager)) {
            return;
        }

        if (godModes.contains(player.getUniqueId())) {
            ChatInfo.DANGER.send(damager, "Tento hráč má ještě 2 sekundovou nesmrtelnost!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (waitingForTeleport.containsKey(player.getUniqueId())) {
            Location from = event.getFrom();
            Location to = event.getTo();

            if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
                cancelTeleport(player);
            }
        }

        if (pvpStatistics.containsKey(player.getUniqueId())) {
            if (Main.getInstance().getPvpManager().isPlayerInAnyArena(player)) {
                showActionBar(player, pvpStatistics.get(player.getUniqueId()));
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location destination = event.getTo();

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.UNKNOWN) { // Nechceme znovu čekat 10 sekund, po tom, co jsme čekali 10 sekund
            return;
        }

        if (player.hasPermission("craftmania.at")) {
            sendPvPStatisticsAndRemove(player);
            return;
        }

        if (!Main.getInstance().getPvpManager().isPlayerInAnyArena(player)) {
            return;
        }

        event.setCancelled(true);
        ChatInfo.DANGER.send(player, "Jsi v PvP Aréně! Budeš teleportovaný za 10 sekund, pokud se nepohneš a nic tě netrefí.");

        waitingForTeleport.put(player.getUniqueId(), Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            sendPvPStatisticsAndRemove(player);
            ChatInfo.SUCCESS.send(player, "Teleportuji tě z PvP Arény na tvou destinaci...");
            player.teleport(destination, PlayerTeleportEvent.TeleportCause.UNKNOWN);

            waitingForTeleport.remove(player.getUniqueId());
        }, 200));
    }

    @EventHandler
    public void onPlayerTeleportedToArenaEvent(PlayerTeleportedToArenaEvent event) {
        Player player = event.getPlayer();

        pvpStatistics.remove(player.getUniqueId());
        pvpStatistics.put(player.getUniqueId(), new PvPStatistics());
    }

    @EventHandler
    public void onPlayerDeathInArenaEvent(PlayerDeathInArenaEvent event) {
        Player player = event.getPlayer();

        sendPvPStatisticsAndRemove(player);
    }

    @EventHandler
    public void onPlayerKilledPlayerEvent(PlayerKilledPlayerEvent event) {
        Player player = event.getPlayer();

        if (pvpStatistics.containsKey(player.getUniqueId())) {
            pvpStatistics.get(player.getUniqueId()).addKill();
        }
    }

    private void cancelTeleport(Player player) {
        BukkitTask task = waitingForTeleport.get(player.getUniqueId());
        waitingForTeleport.remove(player.getUniqueId());

        task.cancel();
        ChatInfo.DANGER.send(player, "Něco tě trefilo nebo ses pohl! Teleport se zrušil.");
    }

    private void showActionBar(Player player, PvPStatistics pvpStatistics) {
        player.sendActionBar(Component.text("§7Zabití: §e" + pvpStatistics.getKills() + "  §7Čas naživu: §e" + pvpStatistics.getElapsedTimePretty()));
    }

    private void sendPvPStatisticsAndRemove(Player player) {
        if (pvpStatistics.containsKey(player.getUniqueId())) {
            PvPStatistics pvpStatistics = PvPListener.pvpStatistics.get(player.getUniqueId());

            ChatInfo.DANGER.send(player, "§7Zabil jsi §e" + pvpStatistics.getKills() + "§7 hráčů za §e" + pvpStatistics.getElapsedTimePretty() + "§7");
        }

        pvpStatistics.remove(player.getUniqueId());
    }
}
