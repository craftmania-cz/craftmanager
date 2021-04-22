package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftlibs.utils.ExperienceUtil;
import cz.wake.manager.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class OnEXPBottleThrownListener implements Listener {

    @EventHandler
    public void OnEXPBottleThrownEvent(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof ThrownExpBottle) {
            ThrownExpBottle thrownExpBottle = (ThrownExpBottle) event.getEntity();
            ItemStack thrownedExpBottle = thrownExpBottle.getItem();
            NamespacedKey customExpMetaKey = new NamespacedKey(Main.getInstance(), "givecustomexp");
            ItemMeta itemMeta = thrownedExpBottle.getItemMeta();
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            if (container.has(customExpMetaKey, PersistentDataType.LONG)) {
                if (event.getEntity().getShooter() instanceof Player) {
                    Player player = (Player) event.getEntity().getShooter();
                    long expToGive = container.get(customExpMetaKey, PersistentDataType.LONG);
                    ExperienceUtil.changeExp(player, expToGive - 1);
                    removeExpBottleFromPlayer(player);
                    event.setCancelled(true);
                    ChatInfo.SUCCESS.send(player, "Bylo ti přidáno §e" + expToGive + "EXP");
                }
            }
        }
    }

    // Utils
    private void removeExpBottleFromPlayer(Player player) {
        NamespacedKey customExpMetaKey = new NamespacedKey(Main.getInstance(), "givecustomexp");
        ItemStack inMainHand = player.getInventory().getItemInMainHand();
        ItemMeta inMainHandMeta = inMainHand.getItemMeta();
        if (inMainHandMeta != null) {
            if (inMainHandMeta.getPersistentDataContainer().has(customExpMetaKey, PersistentDataType.LONG)) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                return;
            }
        }
        ItemStack inOffHand = player.getInventory().getItemInOffHand();
        ItemMeta inOffHandMeta = inOffHand.getItemMeta();
        if (inOffHandMeta != null) {
            if (inOffHandMeta.getPersistentDataContainer().has(customExpMetaKey, PersistentDataType.LONG)) {
                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
            }
        }
    }
}
