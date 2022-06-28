package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.crafteconomy.utils.VaultUtils;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftlibs.utils.ServerColors;
import cz.craftmania.craftpack.api.Buttons;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RechargeGUI implements InventoryProvider {

    private final VaultUtils vaultUtils = new VaultUtils();

    @Override
    public void init(Player player, InventoryContents content) {

        ItemStack money200 = new ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get() + "Obnovit: +1000")
                        .setLore("§eCena: §6$§f200", "", ServerColors.DARK_GRAY.get() + "Kliknutím si dobiješ fly").build();
        content.set(1, 2, ClickableItem.of(money200, (inventoryClickEvent -> {
            if (vaultUtils.getBalance(player) < 200) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$200");
                return;
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi flightcharge add %nick% 1000".replace("%nick%", player.getName()));
            vaultUtils.withdrawPlayer(player, 200);
            this.openRechargerGui(player);
        })));

        ItemStack money500 = new ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get() + "Obnovit: +2500")
                .setLore("§eCena: §6$§f500", "", ServerColors.DARK_GRAY.get() + "Kliknutím si dobiješ fly").build();
        content.set(2, 2, ClickableItem.of(money500, (inventoryClickEvent -> {
            if (vaultUtils.getBalance(player) < 500) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$500");
                return;
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi flightcharge add %nick% 2500".replace("%nick%", player.getName()));
            vaultUtils.withdrawPlayer(player, 500);
            this.openRechargerGui(player);
        })));

        ItemStack money1000 = new ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get() + "Obnovit: +5300")
                .setLore("§eCena: §6$§f1,000", "", ServerColors.DARK_GRAY.get() + "Kliknutím si dobiješ fly").build();
        content.set(3, 2, ClickableItem.of(money1000, (inventoryClickEvent -> {
            if (vaultUtils.getBalance(player) < 1000) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$1000");
                return;
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi flightcharge add %nick% 5300".replace("%nick%", player.getName()));
            vaultUtils.withdrawPlayer(player, 1000);
            this.openRechargerGui(player);
        })));

        content.set(1, 4, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));
        content.set(2, 4, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));
        content.set(3, 4, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));

        content.set(1, 6, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));
        content.set(2, 6, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));
        content.set(3, 6, ClickableItem.empty(new ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build()));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        InventoryProvider.super.update(player, contents);
    }

    private void openRechargerGui(final Player player) {
        SmartInventory.builder().provider(new RechargeGUI()).title(":offset_-18::recharge_menu:").size(5, 9).build().open(player);
    }
}
