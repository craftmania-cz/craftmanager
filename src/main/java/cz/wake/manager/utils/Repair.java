package cz.wake.manager.utils;

import cz.craftmania.craftcore.spigot.messages.chat.ChatInfo;
import cz.craftmania.craftlibs.utils.TextComponentBuilder;
import cz.craftmania.craftlibs.utils.actions.ConfirmAction;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Repair {

    public static void repair(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item.getItemMeta() == null) {
            p.sendMessage("§c§l[!] §cTento item není poničen nebo nelze opravit.");
            return;
        }

        if (item.getItemMeta().hasCustomModelData()) {
            p.sendMessage("§c§l[!] §cNelze opravovat item, který má nastavený styl.");
            return;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            p.sendMessage("§c§l[!] §cNemáš v ruce item, který by šel opravit.");
            return;
        }
        if (!((Damageable) item.getItemMeta()).hasDamage()) {
            p.sendMessage("§c§l[!] §cTento item není poničen.");
            return;
        }
        int enchanments = item.getEnchantments().values().stream().mapToInt(integer -> (int) Math.round(.5 * integer)).sum();
        double currentDurability = ((Damageable) item.getItemMeta()).getDamage();
        int repairCost = ((int) Math.ceil(currentDurability / 100D));
        if (currentDurability / item.getType().getMaxDurability() < 0.15D) repairCost += enchanments;

        if (p.getLevel() < repairCost) {
            ChatInfo.error(p, "Nemáš dostatek levelů (" + repairCost + "LVL).");
            return;
        }

        try {
            final int finalRepairCost = repairCost;
            ConfirmAction.Action action = new ConfirmAction.Builder()
                    .setPlayer(p)
                    .generateIdentifier()
                    .setDelay(200L)
                    .addComponent(a -> new TextComponentBuilder("&7Oprava tohoto itemu bude stát &e" + finalRepairCost + "LVL&7.").getComponent())
                    .addComponent(a -> new TextComponentBuilder("§a[Klikni zde opravnení itemu]")
                            .setTooltip("Opravit item za " + finalRepairCost + "LVL.\nTato akce již nejde vrátit.")
                            .setPerformedCommand(a.getConfirmationCommand()).getComponent())
                    .setRunnable(player -> {
                        ItemStack itemStack = player.getInventory().getItem(getItemSlot(player, item));
                        if (itemStack == null) {
                            ChatInfo.error(player, "Item, který si chtěl opravit již není v tvém inventáři.");
                            return;
                        }
                        if (player.getLevel() < finalRepairCost) {
                            ChatInfo.error(player, "Nemáš dostatek levelů (" + finalRepairCost + "LVL).");
                            return;
                        }
                        ItemMeta itemStackItemMeta = itemStack.getItemMeta();
                        if (!(itemStackItemMeta instanceof Damageable)) return;
                        ((Damageable) itemStackItemMeta).setDamage(0);
                        itemStack.setItemMeta(itemStackItemMeta);

                        player.setLevel(player.getLevel() - finalRepairCost);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
                        ChatInfo.success(player, "Item byl opraven.");
                    })
                    .build();
            action.sendTextComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 0 - 35

    /**
     * @param player Player
     * @param itemStack ItemStack to find
     * @return Slot where itemStack is located. Returns -1 if not found.
     */
    public static int getItemSlot(@NotNull Player player, @NotNull ItemStack itemStack) {
        for (int slot = 0; slot <= 35; slot++) {
            if (player.getInventory().getItem(slot) == null) continue;
            if (Objects.requireNonNull(player.getInventory().getItem(slot)).isSimilar(itemStack)) return slot;
        }
        return -1;
    }
}
