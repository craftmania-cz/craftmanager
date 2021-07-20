package cz.wake.manager.servers.vanilla;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import org.bukkit.*;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.stream.Collectors;

public class DragonSlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onDragonSlay(final EntityDeathEvent event) {
        if (!(event.getEntity() instanceof EnderDragon)) return;

        final LivingEntity dragonEntity = event.getEntity();
        final World theEnd = dragonEntity.getWorld();

        if (theEnd.getEnvironment() != World.Environment.THE_END) return;

        event.setDroppedExp(0);

        theEnd.spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, dragonEntity.getLocation(), 1200, 2, 1, 2, 1);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {

            // Expy pro všechny, co se zučastnili boje
            this.giveExperienceAllPlayers(dragonEntity, theEnd);

            // Spawn vejce na středu
            this.spawnDragonEgg(theEnd);

            // Oznámení a uložení toho, kdo zabil draka
            this.killerAnnounce(dragonEntity.getKiller(), dragonEntity.getLastDamageCause(), theEnd);
        }, 80L);
    }

    private void killerAnnounce(final Player killer, final EntityDamageEvent damage, final World theEnd) {
        final String killerName = getKillerName(killer, damage);
        /*final List<String> playerNames = getPlayersInEndByRadius(
                theEnd,
                128
        ).stream().map(HumanEntity::getName).collect(Collectors.toList());*/
        Main.getInstance().getMySQL().dragonSlayerKillData(killer);
        theEnd.getPlayers().forEach((player -> {
            ChatInfo.INFO.send(player, "Drak byl zabit hráčem §e" + killerName);
        }));
    }

    private void giveExperienceAllPlayers(final LivingEntity dragonEntity, final World theEnd) {
        for (Player player : getPlayersInEndByRadius(theEnd, 128)) { // Radius 128 pro celý ostrov
            player.giveExpLevels(68); // 68 levelu každý dostane
            ChatInfo.SUCCESS.send(player, "Obdržel jsi: §f68 LVL");
        }
    }

    private void spawnDragonEgg(final World theEnd) {
        if (!theEnd.getEnderDragonBattle().hasBeenPreviouslyKilled()) return; // První vejce generuje sám MC
        Location eggLocation = new Location(theEnd, 0, 65, 0);
        eggLocation.getBlock().setType(Material.DRAGON_EGG);
        theEnd.getPlayers().forEach((player -> {
            ChatInfo.INFO.send(player, "Dragon Egg se objevil u hlavního portálu.");
        }));
    }

    private List<Player> getPlayersInEndByRadius(final World theEnd, final int radius) {
        final Location worldCenter = new Location(theEnd, 0, 65, 0);
        return theEnd.getPlayers().stream()
                .filter(p -> p.getLocation().distance(worldCenter) <= radius)
                .collect(Collectors.toList());
    }

    private static String getKillerName(Player killer, EntityDamageEvent damage) {
        if (killer == null) {
            return damage.getCause().toString().replace('_', ' ');
        }
        return killer.getName();
    }
}
