package cz.wake.manager.utils;

import cz.craftmania.craftlibs.utils.ChatInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public boolean hasFullInventory(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }

    public void givePlayerItemOrDrop(final Player player, final ItemStack itemStack) {
        if (hasFullInventory(player)) {
            if (itemStack.getItemMeta() != null && itemStack.getItemMeta().hasDisplayName()) {
                ChatInfo.INFO.send(player, "Máš plný inventář, item dropnul na zem: " + itemStack.getItemMeta().getDisplayName());
            } else {
                ChatInfo.INFO.send(player, "Máš plný inventář, item dropnul na zem!");
            }
            player.getLocation().getWorld().dropItem(player.getLocation(), itemStack);
        } else {
            player.getInventory().addItem(itemStack);
            ChatInfo.SUCCESS.send(player, "Do inventáře ti byl přidán item: " + itemStack.getItemMeta().getDisplayName());
        }
    }

    public void giveItemsAdderItem(final Player player, final String itemId, final String itemName) { //TODO: Předělat
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "iagive " + player.getName() + " " + itemId + " 1");
        ChatInfo.SUCCESS.send(player, "Do inventáře ti byl přidán item: §f" + itemName);
    }
}
