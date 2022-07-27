package cz.wake.manager.listener;

import com.francobm.magicosmetics.api.Cosmetic;
import com.francobm.magicosmetics.api.CosmeticType;
import com.francobm.magicosmetics.api.MagicAPI;
import com.francobm.magicosmetics.cache.PlayerData;
import cz.craftmania.craftcore.events.worldguard.RegionEnterEvent;
import cz.craftmania.craftcore.events.worldguard.RegionLeaveEvent;
import cz.craftmania.craftlibs.utils.ChatInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegionListener implements Listener {

    private static final HashMap<Player, Cosmetic> activeCosmeticBagpacks = new HashMap<>();
    private static final HashMap<Player, Cosmetic> activeCosmeticBalloons = new HashMap<>();
    private static final List<Player> activeGlowing = new ArrayList<>();

    @EventHandler
    public void onRegionEnter(RegionEnterEvent event) {
        switch (event.getRegion().getId()) {
            case "spawn-pinata" -> {
                Player player = event.getPlayer();
                if (MagicAPI.hasEquipCosmetic(player, CosmeticType.BALLOON)) {
                    Cosmetic cosmetic = PlayerData.getPlayer(player).getBalloon();
                    activeCosmeticBalloons.put(player, cosmetic);
                    MagicAPI.UnEquipCosmetic(player, CosmeticType.BALLOON);
                    ChatInfo.INFO.send(player, "Nasazený balének ti byl odendán. Vstoupil jsi do chráněného regionu.");
                }
                if (MagicAPI.hasEquipCosmetic(player, CosmeticType.BAG)) {
                    Cosmetic cosmetic = PlayerData.getPlayer(player).getBag();
                    activeCosmeticBagpacks.put(player, cosmetic);
                    MagicAPI.UnEquipCosmetic(player, CosmeticType.BAG);
                    ChatInfo.INFO.send(player, "Nasazený batoh ti byl odendán. Vstoupil jsi do chráněného regionu.");
                }
                if (player.isGlowing()) {
                    player.setGlowing(false);
                    activeGlowing.add(player);
                    ChatInfo.INFO.send(player, "Glowing efekt byl deaktivován.");
                }
            }
        }
    }

    @EventHandler
    public void onRegionLeave(RegionLeaveEvent event) {
        switch (event.getRegion().getId()) {
            case "spawn-pinata" -> {
                Player player = event.getPlayer();
                if (activeCosmeticBalloons.containsKey(player)) {
                    PlayerData.getPlayer(player).setCosmetic(activeCosmeticBalloons.get(player));
                    activeCosmeticBalloons.remove(player);
                }
                if (activeCosmeticBagpacks.containsKey(player)) {
                    PlayerData.getPlayer(player).setCosmetic(activeCosmeticBagpacks.get(player));
                    activeCosmeticBagpacks.remove(player);
                }
                if (activeGlowing.contains(player)) {
                    activeGlowing.remove(player);
                    player.setGlowing(true);
                }
            }
        }
    }
}
