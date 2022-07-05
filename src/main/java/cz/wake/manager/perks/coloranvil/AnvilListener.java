package cz.wake.manager.perks.coloranvil;

import cz.wake.manager.utils.tasks.AnvilTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class AnvilListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAnvilGUIClick(final InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getWhoClicked() instanceof Player) {
            final Player player = (Player) event.getWhoClicked();
            final AnvilInventory inv = (AnvilInventory) event.getInventory();

            AnvilTask task = AnvilTask.getTask(inv);
            if (task == null) {
                task = new AnvilTask(inv, player);
            }

            if (event.getCurrentItem() == null) {
                return;
            }

            // Blokace prejmenovani spawneru + crates
            if (event.getCurrentItem().getType() == Material.SPAWNER || event.getCursor().getType() == Material.SPAWNER
                    || event.getCurrentItem().getType() == Material.CHEST || event.getCursor().getType() == Material.TRIPWIRE_HOOK) {
                event.setCancelled(true);
            }

            if (event.getRawSlot() == 2) {
                if(inv.getItem(2) == null){
                    return;
                }
                final ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv, task);
                if(translatedItem.getType() == Material.CHEST || translatedItem.getType() == Material.TRAPPED_CHEST){
                    event.setCurrentItem(new ItemStack(Material.POISONOUS_POTATO));
                }
                event.setCurrentItem(translatedItem);
            }
        }
    }
}
