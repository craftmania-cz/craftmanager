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
import cz.craftmania.crafteconomy.objects.LevelType;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CshopPermsShop implements InventoryProvider {

    private final LevelType levelType;

    public CshopPermsShop(LevelType levelType) {
        this.levelType = levelType;
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        Main.getInstance().getCshopManager().getPermsShopItems().forEach((permissionItem -> {
            final ArrayList<String> lore = new ArrayList<>();
            lore.addAll(permissionItem.getLore());

            if (!(LevelAPI.getLevel(player, this.levelType) >= permissionItem.getRequiredLevel())) { // Nemá dostatečný lvl
                items.add(ClickableItem.empty(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .setName("§c" + permissionItem.getName()).setLore("§7Nemáš požadovaný lvl: " + permissionItem.getRequiredLevel()).build()));
                return;
            }

            if (player.hasPermission(permissionItem.getPermision())) { // Již má koupeno
                items.add(ClickableItem.empty(new ItemBuilder(permissionItem.getItemStack())
                        .setName("§a" + permissionItem.getName()).setGlowing().hideAllFlags().setLore("§7Již máš zakoupeno!").build()));
                return;
            }

            if (EconomyAPI.CRAFT_COINS.get(player) >= permissionItem.getPrice()) {
                items.add(ClickableItem.of(new ItemBuilder(permissionItem.getItemStack()).hideAllFlags()
                    .setName("§b" + permissionItem.getName()).setLore("§7Cena: §f" + permissionItem.getPrice() + " CC", "", "§eKliknutím zakoupíš!").build(),
                        click -> {
                            EconomyAPI.CRAFT_COINS.take(player, permissionItem.getPrice());
                            player.sendMessage("§aZakoupil jsi si " + permissionItem.getName() + "!");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set " + permissionItem.getPermision() + " " + Main.getInstance().getServerType().name.toLowerCase());
                            player.closeInventory();
                        }));
            } else {
                items.add(ClickableItem.empty(new ItemBuilder(permissionItem.getItemStack())
                        .setName("§c" + permissionItem.getName()).hideAllFlags().setLore("§7Cena: §f" + permissionItem.getPrice() + " CC", "", "§8Nemáš dostatek CraftCoins").build()));
            }
        }));

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
            SmartInventory.builder().size(6, 9).title("[" + Main.getInstance().getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
        }));

        SlotIterator slotIterator = contents.newIterator("cshop-perms", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);


    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
