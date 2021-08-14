package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CosmeticDropListener implements Listener {

    private HashMap<Player, Pair<ItemStack, Long>> dropRequests = new HashMap<>();

    @EventHandler
    public void onCosmeticDrop(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItemDrop().getItemStack();

        if (event.isCancelled()) {
            ChatInfo.DANGER.send(player, "Zde nelze vyhodit tento item, je to blokováno jiným pluginem.");
            return;
        }

        if (itemStack.getItemMeta() == null) {
            return;
        }

        if (!itemStack.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (Main.getInstance().getConfig().getStringList("protect-drop-cosmetic").contains(itemStack.getType().toString())) {
            if (!dropRequests.containsKey(player) || dropRequests.get(player).getValue() < System.currentTimeMillis()) {
                event.setCancelled(true);
                ChatInfo.INFO.send(player, "Pokud chceš vyhodit item na zem, stiskni znova Q!");
                dropRequests.put(player, Pair.of(itemStack, System.currentTimeMillis() + 5000)); // 5 seconds
            } else {
                event.setCancelled(false);
                ChatInfo.DANGER.send(player, "Vyhodil jsi chráněný item na zem!");
                dropRequests.remove(player);
            }
        }
    }

    @EventHandler
    public void onCosmeticInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();

        if (itemStack == null) {
            return;
        }

        if (itemStack.getType() != Material.SUGAR) {
            return;
        }

        if (itemStack.getItemMeta() == null) {
            return;
        }

        if (!itemStack.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.SUGAR) {

                if (player.getInventory().getHelmet() != null) {
                    ChatInfo.DANGER.send(player, "Nelze si nasadit čepici, když máš již něco na hlavě!");
                    return;
                }

                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 100001) {
                        player.getInventory().setHelmet(itemStack);
                        ChatInfo.SUCCESS.send(player, "Nasadil jsi si na hlavu: §f" + itemStack.getItemMeta().getDisplayName());
                        player.getInventory().setItemInHand(null);
                    }
                }
            }
        }
    }
}
