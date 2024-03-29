package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.inventory.builder.content.SlotIterator;
import cz.craftmania.crafteconomy.api.EconomyAPI;
import cz.craftmania.crafteconomy.api.LevelAPI;
import cz.wake.manager.Main;
import cz.wake.manager.shop.types.RewardType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CshopItemShop implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        Main.getInstance().getCshopManager().getItemsShopItems().forEach(voteItem -> {

            if (!(LevelAPI.getLevel(player, Main.getInstance().getLevelType()) >= voteItem.getRequiredLevel())) { // Nemá dostatečný lvl
                items.add(ClickableItem.empty(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .setName("§c" + voteItem.getName()).setLore("§7Nemáš požadovaný lvl: " + voteItem.getRequiredLevel()).build()));
                return;
            }

            if (!(EconomyAPI.CRAFT_COINS.get(player) >= voteItem.getPrice())) { // Kontrola zda má dostatek VT
                items.add(ClickableItem.empty(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .setName("§c" + voteItem.getName()).setLore("§7Nemáš dostatek CraftCoins: §f" + voteItem.getPrice() + " CT").build()));
                return;
            }

            if (voteItem.getRewardType() == RewardType.COMMAND) {
                items.add(ClickableItem.of(new ItemBuilder(voteItem.getItemStack()).setName("§a" + voteItem.getName()).setLore("§7Cena: §f" + voteItem.getPrice() + " CC").hideAllFlags().build(), click -> {
                    EconomyAPI.CRAFT_COINS.take(player, voteItem.getPrice());
                    player.sendMessage("§aZakoupi jsi si " + voteItem.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), voteItem.getCommandToExecute().replace("%player%", player.getName()));
                    player.closeInventory();
                }));
                return;
            }
        });

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(36);

        if (items.size() > 0 && !pagination.isLast()) {
            contents.set(5, 7, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lDalší stránka").build(), e -> {
                contents.inventory().open(player, pagination.next().getPage());
            }));
        }
        if (!pagination.isFirst()) {
            contents.set(5, 1, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lPředchozí stránka").build(), e -> {
                contents.inventory().open(player, pagination.previous().getPage());
            }));
        }

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.ARROW).setName("§aZpět do menu").build(), e -> {
            SmartInventory.builder().size(6, 9).title("[" + Main.getInstance().getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
        }));

        SlotIterator slotIterator = contents.newIterator("cshop-items", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
