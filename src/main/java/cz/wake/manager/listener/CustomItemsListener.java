package cz.wake.manager.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

/**
 * Blokace na custom items = čepice => Material.PAPER
 */
public class CustomItemsListener implements Listener {

    @EventHandler
    public void onPlaceBlock(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta().hasCustomModelData() && event.getItemInHand().getType() == Material.PAPER) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemMove(final InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        if (itemStack.getItemMeta() == null) {
            return;
        }
        if (itemStack.getItemMeta().hasCustomModelData() && event.getCurrentItem().getType() == Material.PAPER) {
            event.setCancelled(true);
            if (event.getSlot() != 39) {
                event.setCurrentItem(new ItemStack(Material.AIR));
                ((Player)event.getWhoClicked()).updateInventory();
            }
        }
    }

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.getItemMeta() == null) {
            return;
        }
        if (itemStack.getItemMeta().hasCustomModelData() && event.getItemDrop().getItemStack().getType() == Material.PAPER) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(final PlayerDeathEvent event) {
        List<ItemStack> list = event.getDrops();
        list.removeIf(item -> item.getItemMeta().hasCustomModelData() && item.getType() == Material.PAPER);
    }
}
