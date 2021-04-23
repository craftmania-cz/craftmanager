package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.utils.ServerType;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

public class PlayerListener implements Listener {

    private ParticlesAPI partAPI = new ParticlesAPI();

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
            Main.getInstance().at_afk.put(p, 0);
        }

        //Death messages
        if (Main.getInstance().getMySQL().getSettings(p, "death_messages") == 1) {
            Main.getInstance().death_messages.add(p);
        }

        //Mentions
        if (Main.getInstance().getMySQL().getSettingsString(p, "mention_sound") == null && Main.getInstance().getMySQL().getSettingsString(p, "mention_sound").equals("")) {
            Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        }

        //ScoreboardManager
        if (Main.getInstance().getScoreboardManager() != null) { // Null když je vypnutý
            if (Main.getInstance().getMySQL().getSettings(p, "show_scoreboard") == 1) {
                Main.getInstance().getServer().getScheduler().runTaskLater(Main.getInstance(), () -> {
                    Main.getInstance().getScoreboardManager().setupPlayer(p);
                }, 10L);
            }
        }

        if (Main.getServerType() == ServerType.ANARCHY) {
            if (Main.getInstance().getMySQL().getSettings(e.getPlayer(), "hud_bossbar") == 1) {
                assert Main.getInstance().getCompassManager() != null;
                Main.getInstance().getCompassManager().addPlayer(e.getPlayer());
            }

            p.sendMessage("");
            p.sendMessage("§c§lVanilla: Anarchy");
            p.sendMessage("§7Na tomto serveru není žádná ochrana regionů.");
            p.sendMessage("§7Kdokoliv na tebe může udělat tpa-kill, jakkoliv tě zabít.");
            p.sendMessage("§7Používání cheatů, xraye atd. je stále zakázáno.");
            p.sendMessage("");
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
            if (Main.getInstance().isVisibleForPlayer(p)) {
                partAPI.deactivateParticles(p);
                partAPI.deaktivateCapes(p);
                Main.getInstance().removePlayer(p);
            }

            //AT
            if (Main.getInstance().at_list.contains(p)) {
                Main.getInstance().at_list.remove(p);
            }

            if (Main.getInstance().at_afk.containsKey(p)) {
                Main.getInstance().at_afk.remove(p);
            }

            //Death messages
            if (Main.getInstance().death_messages.contains(p)) {
                Main.getInstance().death_messages.remove(p);
            }

            //RestartManager
            RestartManager_command.bb.removePlayer(p);

            //ScoreboardManager
            if (Main.getInstance().getScoreboardManager() != null) {
                Main.getInstance().getScoreboardManager().removePlayer(p);
            }

            if (Main.getServerType() == ServerType.ANARCHY) {
                assert Main.getInstance().getCompassManager() != null;
                Main.getInstance().getCompassManager().removePlayer(e.getPlayer());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Main.getInstance().sendSentryException(exception);
        }
    }

    @EventHandler
    public void onKick(final PlayerKickEvent e) {
        final Player p = e.getPlayer();

        try {
            if (Main.getInstance().isVisibleForPlayer(p)) {
                partAPI.deactivateParticles(p);
                partAPI.deaktivateCapes(p);
                Main.getInstance().removePlayer(p);
            }

            //AT
            if (Main.getInstance().at_list.contains(p)) {
                Main.getInstance().at_list.remove(p);
            }

            if (Main.getInstance().at_afk.containsKey(p)) {
                Main.getInstance().at_afk.remove(p);
            }

            //Death messages
            if (Main.getInstance().death_messages.contains(p)) {
                Main.getInstance().death_messages.remove(p);
            }

            //ScoreboardManager
            if (Main.getInstance().getScoreboardManager() != null) {
                Main.getInstance().getScoreboardManager().removePlayer(p);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Main.getInstance().sendSentryException(exception);
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
        if (Main.getServerType() != ServerType.CREATIVE) {
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

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            if(Main.getInstance().at_afk.containsKey(player)) {
                if(Main.getInstance().at_afk.get(player) != 0) {
                    Main.getInstance().at_afk.put(player, 0);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (damager.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                ChatInfo.DANGER.send(damager, "Nelze zabíjet hráče s Invisibility Potionem.");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSplash(EntityPotionEffectEvent event) {
        if (event.getEntity() instanceof  Player) {
            if (event.getAction() == EntityPotionEffectEvent.Action.ADDED || event.getAction() == EntityPotionEffectEvent.Action.CHANGED) {
                if (event.getCause() == EntityPotionEffectEvent.Cause.AREA_EFFECT_CLOUD
                        || event.getCause() == EntityPotionEffectEvent.Cause.POTION_SPLASH
                        || event.getCause() == EntityPotionEffectEvent.Cause.ARROW) {
                    if (event.getModifiedType().equals(PotionEffectType.INVISIBILITY)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
