package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.inventory.builder.content.SlotIterator;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CshopCosmeticShop implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        Main.getInstance().getCshopManager().getCosmeticsShopItems().forEach(cosmeticItem -> {
            final ArrayList<String> lore = new ArrayList<>();
            lore.addAll(cosmeticItem.getLore());

            if (player.hasPermission(cosmeticItem.getPermision())) { // Již má koupeno
                //items.add(ClickableItem.empty(new ItemBuilder(cosmeticItem.getItemStack())
                //        .setName("§a" + cosmeticItem.getName()).setGlowing().hideAllFlags().setLore("§7Již máš zakoupeno!").build()));
                return;
            }

            if (CraftCoinsAPI.getCoins(player) >= cosmeticItem.getPrice()) {
                items.add(ClickableItem.of(new ItemBuilder(cosmeticItem.getItemStack()).hideAllFlags()
                                .setName("§b" + cosmeticItem.getName()).setLore("§7Cena: §f" + cosmeticItem.getPrice() + " CC", "", "§eKliknutím zakoupíš!").build(),
                        click -> {
                            CraftCoinsAPI.takeCoins(player, cosmeticItem.getPrice());
                            player.sendMessage("§a§l[*] §aZakoupil jsi si " + cosmeticItem.getName() + "! §eNyní jej najdeš v /cosmetics");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set " + cosmeticItem.getPermision());
                            player.closeInventory();
                        }));
                return;
            } else {
                items.add(ClickableItem.empty(new ItemBuilder(cosmeticItem.getItemStack())
                        .setName("§c" + cosmeticItem.getName()).hideAllFlags().setLore("§7Cena: §f" + cosmeticItem.getPrice() + " CC", "", "§8Nemáš dostatek CraftCoins").build()));
            }
        });

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(18);

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
            SmartInventory.builder().size(6, 9).title("[" + Main.getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
        }));

        SlotIterator slotIterator = contents.newIterator("cshop-cosmetics", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
