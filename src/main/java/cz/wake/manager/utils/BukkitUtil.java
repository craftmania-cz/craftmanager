package cz.wake.manager.utils;

import cz.wake.manager.Main;
import cz.wake.manager.objects.pvp.events.PlayerDeathInArenaEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

public class BukkitUtil {

    public static void runAsync(Runnable runnable) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public static void runSync(Runnable runnable) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTask(Main.getInstance());
    }


    public static String makePrettyLocation(Location location) {
        return "X: " + location.getBlockX() + " Y: " + location.getBlockY() + " Z: " + location.getBlockZ();
    }

    public static void callEvent(Event event) {
        runSync(() -> Bukkit.getServer().getPluginManager().callEvent(event));
    }
}
