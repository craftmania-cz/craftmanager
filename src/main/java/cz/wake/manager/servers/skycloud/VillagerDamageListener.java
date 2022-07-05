package cz.wake.manager.servers.skycloud;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.Objects;

public class VillagerDamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof AbstractVillager) {
            AbstractVillager villager = (AbstractVillager)event.getEntity();
            if (villager.hasMetadata(VillagerType.SEA_VILLAGER.name())
                || villager.hasMetadata(VillagerType.RARE_VILLAGER.name())
                || villager.hasMetadata(VillagerType.NETHER_VILLAGER.name())
                || villager.hasMetadata(VillagerType.END_VILLAGER.name())
                || villager.hasMetadata(VillagerType.BUY_VILLAGER.name())
                || villager.hasMetadata(VillagerType.SELL_VILLAGER.name())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        if (entity instanceof Villager) {
            Villager villager = (Villager)entity;
            if (villager.hasMetadata(VillagerType.SELL_VILLAGER.name())) { // Name protoze maji ID
                event.setCancelled(true);
                VillagerManager.openMerchantInventory(VillagerType.SELL_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.BUY_VILLAGER.name())) {
                event.setCancelled(true);
                VillagerManager.openMerchantInventory(VillagerType.BUY_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.END_VILLAGER.name())) {
                event.setCancelled(true);
                VillagerManager.openMerchantInventory(VillagerType.END_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.NETHER_VILLAGER.name())) {
                event.setCancelled(true);
                VillagerManager.openMerchantInventory(VillagerType.NETHER_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.SEA_VILLAGER.name())) {
                event.setCancelled(true);
                VillagerManager.openMerchantInventory(VillagerType.SEA_VILLAGER, player);
            } else if (player.getInventory().getItemInMainHand().getType() == Material.NAME_TAG) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        } else if (entity instanceof WanderingTrader) {
            WanderingTrader villager = (WanderingTrader)entity;
            event.setCancelled(true);
            if (villager.hasMetadata(VillagerType.RARE_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.RARE_VILLAGER, player);
            } else if (player.getInventory().getItemInMainHand().getType() == Material.NAME_TAG) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        }
    }

    /*@EventHandler(priority = EventPriority.HIGH)
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof WanderingTrader) { // Deaktivace spawnování Wandering Tradera na Skycloudu
            if (!entity.hasMetadata(VillagerType.RARE_VILLAGER.name())) {
                event.setCancelled(true);
            }
        }
    }*/

    @EventHandler
    public void onTrade(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if (inventory != null && inventory.getType() != InventoryType.MERCHANT) {
            return;
        }

        if (!Objects.requireNonNull(player.getLocation().getWorld()).getName().equalsIgnoreCase("vsbspawn")) {
            return;
        }

        if (event.getSlotType() == InventoryType.SlotType.RESULT) {
            MerchantInventory merchantInventory = (MerchantInventory) inventory;
            if (event.getCurrentItem() == null || merchantInventory == null) {
                return;
            }
            if (merchantInventory.getSelectedRecipe() == null) {
                return;
            }
            ItemStack item = merchantInventory.getSelectedRecipe().getResult();
            int price = generatePrice(merchantInventory.getSelectedRecipe());
            Log.info("Hrac " + player.getName() + " si koupil: " + item.getType().name() + " (" + item.getAmount() + "x)");
            Main.getInstance().getMySQL().sendMarketLog(player, item.getType().name(), item.getAmount(), price, System.currentTimeMillis());
        }
    }

    private int generatePrice(MerchantRecipe recipe) { //TODO: Podpora dia / dia_blocks
        int price = 0;
        for (ItemStack itemStack : recipe.getIngredients()) {
            price += itemStack.getAmount();
        }
        return price;
    }
}
