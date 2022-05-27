package cz.wake.manager.listener;

import cz.craftmania.craftcore.events.worldguard.RegionEnterEvent;
import cz.wake.manager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WardrobeRegionListener implements Listener {

    @EventHandler
    public void onWardrobeEnter(RegionEnterEvent event) {
        Player player = event.getPlayer();
        if (event.getRegion().getId().equalsIgnoreCase("spawn_wardrobe")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cosmetic wardrobe " + player.getName());
            Log.normalMessage("[Wardrobe] Aktivace pro: " + player.getName());
        }
    }
}
