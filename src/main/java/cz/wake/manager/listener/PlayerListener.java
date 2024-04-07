package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.utils.ServerType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage(null);

        //Oprava pro skoncene VIP hrace s Glowing
        p.setGlowing(false);

        //RestartManager
        if (Main.restartTime != null) {
            RestartManager_command.bb.addPlayer(p);
        }

        //Efekty
        if (!Main.getInstance().isVisibleForPlayer(p)) {
            Main.getInstance().addPlayer(p);
        }

        //AT
        if (Main.getInstance().getMySQL().isAT(p)) {
            Main.getInstance().at_list.add(p);
        }

        //ScoreboardManager
        if (Main.getInstance().getScoreboardManager() != null) { // Null když je vypnutý
            if (Main.getInstance().getMySQL().getSettings(p, "show_scoreboard") == 1) {
                Main.getInstance().getServer().getScheduler().runTaskLater(Main.getInstance(), () -> {
                    Main.getInstance().getScoreboardManager().setupPlayer(p);
                }, 10L);
            }
        }

        // HUD Compass
        if (Main.getInstance().getMySQL().getSettings(e.getPlayer(), "hud_bossbar") == 1) {
            assert Main.getInstance().getCompassManager() != null;
            Main.getInstance().getCompassManager().addPlayer(e.getPlayer());
        }

        if (Main.getInstance().getServerType() == ServerType.VANILLA) {
            Main.getInstance().getMySQL().registerDragonSlayer(e.getPlayer());
        }

        if (Main.restartReason != null) {
            long min = Main.restartTime - System.currentTimeMillis();
            if (min > 60 * 60 * 1000) {
                p.sendMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                p.sendMessage("§f");
                p.sendMessage("   §7Server bude restartovan za §f" + TimeUnit.MILLISECONDS.toHours(min) + "h " + TimeUnit.MILLISECONDS.toMinutes(min) % 60 + "m§7.");
                p.sendMessage("   §7Duvod restartu: §f" + Main.restartReason);
                p.sendMessage("§f");
                p.sendMessage("§7§m-------------------------------------");
            } else {
                p.sendMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                p.sendMessage("§f");
                p.sendMessage("   §7Server bude restartovan za §f" + TimeUnit.MILLISECONDS.toMinutes(min) + "m§7 §f" + TimeUnit.MILLISECONDS.toSeconds(min) % 60 + "s§7.");
                p.sendMessage("   §7Duvod restartu: §f" + Main.restartReason);
                p.sendMessage("§f");
                p.sendMessage("§7§m-------------------------------------");
            }
        }
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        e.setQuitMessage(null);

        try {
            //AT
            if (Main.getInstance().at_list.contains(p)) {
                Main.getInstance().at_list.remove(p);
            }

            //RestartManager
            RestartManager_command.bb.removePlayer(p);

            //ScoreboardManager
            if (Main.getInstance().getScoreboardManager() != null) {
                Main.getInstance().getScoreboardManager().removePlayer(p);
            }

            if (Main.getInstance().getServerType() == ServerType.ANARCHY) {
                assert Main.getInstance().getCompassManager() != null;
                Main.getInstance().getCompassManager().removePlayer(e.getPlayer());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void onKick(final PlayerKickEvent e) {
        final Player p = e.getPlayer();

        try {
            //AT
            if (Main.getInstance().at_list.contains(p)) {
                Main.getInstance().at_list.remove(p);
            }

            //ScoreboardManager
            if (Main.getInstance().getScoreboardManager() != null) {
                Main.getInstance().getScoreboardManager().removePlayer(p);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {
        String damager = e.getDamager().toString();

        // Blokace fireworku - damage na hrace
        if ((e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && (damager.equalsIgnoreCase("CraftFirework"))) {
            e.setCancelled(true);
        }

        // Blokace sipu na ArmorStandy
        if ((e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                && (e.getDamager().getType() == EntityType.PLAYER) && (e.getEntity().getType() == EntityType.ARMOR_STAND)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();

        // Pouze Creative
        if (Main.getInstance().getServerType() != ServerType.CREATIVE) {
            return;
        }

        // Deaktivace Spectatoru
        if (e.getNewGameMode() == GameMode.SPECTATOR && !p.hasPermission("craftmanager.spectatorallow")) {
            e.setCancelled(true);
            ChatInfo.DANGER.send(p, "Nelze si změnit GameMode na Spectatora!");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        // Automatický port na spawn, když hráč padá do voidu
        if (player.getWorld().getName().equalsIgnoreCase("spawn")) {
            if (player.getLocation().getY() <= 0) {
                player.performCommand("spawn");
            }
        }
    }

    /*
     * Blokace prutování hráčů.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerFishPlayer(PlayerFishEvent e){
        if(e.getState() != PlayerFishEvent.State.CAUGHT_ENTITY) return;
        final boolean isPlayer = e.getCaught() instanceof HumanEntity;
        if (isPlayer) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    private void giveEasterCosmetics(Player player, String name, String permission, Long date) {
        if (System.currentTimeMillis() >= date && !player.hasPermission(permission)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " true");
            ChatInfo.SERVER.send(player, "Byla ti aktivována Velikonoční čepice: §e" + name);
        }
    }

}
