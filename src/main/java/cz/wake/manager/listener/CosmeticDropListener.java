package cz.wake.manager.listener;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import org.bukkit.entity.Player;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CosmeticDropListener implements Listener {

    private HashMap<Player, Pair<ItemStack, Long>> dropRequests = new HashMap<>();

    @EventHandler
    public void onCosmeticDrop(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItemDrop().getItemStack();

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
}
