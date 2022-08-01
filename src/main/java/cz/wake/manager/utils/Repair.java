package cz.wake.manager.utils;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftlibs.utils.TextComponentBuilder;
import cz.craftmania.craftlibs.utils.actions.ConfirmAction;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Repair {

    public static void repair(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta() == null) {
            ChatInfo.DANGER.send(player, "Tento item není poničen nebo nelze opravit.");
            return;
        }

        if (item.getItemMeta().hasCustomModelData()) {
            ChatInfo.DANGER.send(player, "Nelze opravovat item, který má nastavený styl.");
            return;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            ChatInfo.DANGER.send(player, "Nemáš v ruce item, který by šel opravit.");
            return;
        }
        if (!((Damageable) item.getItemMeta()).hasDamage()) {
            ChatInfo.DANGER.send(player, "Tento item není poničen.");
            return;
        }
        int enchanments = item.getEnchantments().values().stream().mapToInt(integer -> (int) Math.round(.5 * integer)).sum();
        double currentDurability = ((Damageable) item.getItemMeta()).getDamage();
        int repairCost = ((int) Math.ceil(currentDurability / 100D));
        repairCost += enchanments;

        if (player.getLevel() < repairCost) {
            ChatInfo.DANGER.send(player, "Nemáš dostatek levelů (" + repairCost + "LVL).");
            return;
        }

        try {
            final int finalRepairCost = repairCost;
            ConfirmAction.Action action = new ConfirmAction.Builder()
                    .setPlayer(player)
                    .generateIdentifier()
                    .setDelay(200L)
                    .addComponent(a -> new TextComponentBuilder("").getComponent())
                    .addComponent(a -> new TextComponentBuilder("&7Oprava tohoto itemu bude stát &e" + finalRepairCost + "LVL&7.").getComponent())
                    .addComponent(a -> new TextComponentBuilder("§a[Klikni zde opravnení itemu]")
                            .setTooltip("§7Opravit item za §e" + finalRepairCost + "LVL.\n§cTato akce již nejde vrátit.")
                            .setPerformedCommand(a.getConfirmationCommand()).getComponent())
                    .addComponent(a -> new TextComponentBuilder("").getComponent())
                    .setRunnable(player2 -> {
                        ItemStack itemStack = player2.getInventory().getItem(getItemSlot(player2, item));
                        if (itemStack == null) {
                            ChatInfo.DANGER.send(player2, "Item, který si chtěl opravit již není v tvém inventáři.");
                            return;
                        }
                        if (player2.getLevel() <= finalRepairCost) {
                            ChatInfo.DANGER.send(player2, "Nemáš dostatek levelů (" + finalRepairCost + "LVL).");
                            return;
                        }
                        ItemMeta itemStackItemMeta = itemStack.getItemMeta();
                        if (!(itemStackItemMeta instanceof Damageable)) return;
                        ((Damageable) itemStackItemMeta).setDamage(0);
                        itemStack.setItemMeta(itemStackItemMeta);

                        player2.setLevel(player2.getLevel() - finalRepairCost);
                        player2.playSound(player2.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
                        ChatInfo.SUCCESS.send(player2, "Item by kompletně opraven.");
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
