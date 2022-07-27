package cz.wake.manager.listener;

import com.francobm.magicosmetics.api.CosmeticType;
import com.francobm.magicosmetics.api.MagicAPI;
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

    private static final HashMap<Player, String> activeCosmeticBagpacks = new HashMap<>();
    private static final HashMap<Player, String> activeCosmeticBalloons = new HashMap<>();
    private static final List<Player> activeGlowing = new ArrayList<>();

    @EventHandler
    public void onRegionEnter(RegionEnterEvent event) {
        switch (event.getRegion().getId()) {
            case "spawn-pinata" -> {
                Player player = event.getPlayer();
                if (MagicAPI.hasEquipCosmetic(player, CosmeticType.BALLOON)) {
                    ItemStack itemStack = MagicAPI.getEquipped(player, CosmeticType.BALLOON);
                    MagicAPI.UnEquipCosmetic(player, CosmeticType.BALLOON);
                    String cosmeticName = ChatColor.stripColor(itemStack.displayName().toString());
                    activeCosmeticBalloons.put(player, getCosmeticIdByName(cosmeticName));
                    ChatInfo.INFO.send(player, "Nasazený balének ti byl odendán. Vstoupil jsi do chráněného regionu.");
                }
                if (MagicAPI.hasEquipCosmetic(player, CosmeticType.BAG)) {
                    ItemStack itemStack = MagicAPI.getEquipped(player, CosmeticType.BAG);
                    String cosmeticName = ChatColor.stripColor(itemStack.displayName().toString());
                    activeCosmeticBagpacks.put(player, getCosmeticIdByName(cosmeticName));
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
                    MagicAPI.EquipCosmetic(player, activeCosmeticBalloons.get(player), "");
                    activeCosmeticBalloons.remove(player);
                }
                if (activeCosmeticBagpacks.containsKey(player)) {
                    MagicAPI.EquipCosmetic(player, activeCosmeticBagpacks.get(player), "");
                    activeCosmeticBagpacks.remove(player);
                }
                if (activeGlowing.contains(player)) {
                    activeGlowing.remove(player);
                    player.setGlowing(true);
                }
            }
        }
    }

    private String getCosmeticIdByName(String cosmeticDisplayName) {
        switch (cosmeticDisplayName) {
            case "Axolotl" -> {
                return "axolotl_backpack";
            }
            case "Backpack" -> {
                return "back_backpack";
            }
            case "Crown Cape" -> {
                return "crown_cape_backpack";
            }
            case "Golden Cape" -> {
                return "golden_cape_backpack";
            }
            case "Heart Cape" -> {
                return "heart_cape_backpack";
            }
            case "Cyber Wings" -> {
                return "cyber_wings_backpack";
            }
            case "Dragon Tail" -> {
                return "dragon_tail_backpack";
            }
            case "VIP Gold Cape" -> {
                return "vip_gold_cape_backpack";
            }
            case "VIP Diamond Cape" -> {
                return "vip_diamond_cape_backpack";
            }
            case "VIP Emerald Cape" -> {
                return "vip_emerald_cape_backpack";
            }
            case "VIP Obsidian Cape" -> {
                return "vip_obsidian_cape_backpack";
            }
            case "Tester Cape" -> {
                return "tester_cape_surv118_backpack";
            }
            case "Kontrabas" -> {
                return "bass_backpack";
            }
            case "Tortoise Shell" -> {
                return "tortoise_shell";
            }
            case "Kraken Arms" -> {
                return "kraken_arms";
            }
            case "Hotdog" -> {
                return "hotdog";
            }
            case "Jednorožší kruh" -> {
                return "unicorn_floaty";
            }

            // Balloons
            case "Hot air balloon" -> {
                return "hot_air_balloon";
            }
            default -> {
                return null;
            }
        }
    }
}
