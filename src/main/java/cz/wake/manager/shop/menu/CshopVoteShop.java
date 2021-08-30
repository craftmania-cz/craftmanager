package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.inventory.builder.content.SlotIterator;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.LevelAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.craftmania.crafteconomy.managers.BasicManager;
import cz.craftmania.crafteconomy.utils.VaultUtils;
import cz.wake.manager.Main;
import cz.wake.manager.shop.types.RewardType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CshopVoteShop implements InventoryProvider {

    private BasicManager basicManager = new BasicManager();

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        Main.getInstance().getCshopManager().getVoteShopItems().forEach(voteItem -> {

            if (voteItem.getPermisions() != null) { // Blokace skrz práva
                /*if (!player.hasPermission(voteItem.getPermisions())) {
                    items.add(ClickableItem.empty(new ItemBuilder(Material.BARRIER)
                            .setName("§c" + voteItem.getName()).setLore("§7Nemůžeš si zakoupit tuto výhodu!").build()));
                    return;
                }*/
            }

            if (!(LevelAPI.getLevel(player, basicManager.getLevelByServer()) >= voteItem.getRequiredLevel())) { // Nemá dostatečný lvl
                items.add(ClickableItem.empty(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .setName("§c" + voteItem.getName()).setLore("§7Nemáš požadovaný lvl: " + voteItem.getRequiredLevel()).build()));
                return;
            }

            if (!(VoteTokensAPI.getVoteTokens(player) >= voteItem.getPrice())) { // Kontrola zda má dostatek VT
                items.add(ClickableItem.empty(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .setName("§c" + voteItem.getName()).setLore("§7Nemáš dostatek VoteTokenů: §f" + voteItem.getPrice() + " VT").build()));
                return;
            }

            if (voteItem.getRewardType() == RewardType.COMMAND) {
                items.add(ClickableItem.of(new ItemBuilder(voteItem.getItemStack()).setName("§a" + voteItem.getName()).setLore("§7Cena: §f" + voteItem.getPrice() + " VT").hideAllFlags().build(), click -> {
                    VoteTokensAPI.takeVoteTokens(player, voteItem.getPrice());
                    player.sendMessage("§aZakoupi jsi si " + voteItem.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), voteItem.getCommandToExecute().replace("%player%", player.getName()));
                    player.closeInventory();
                }));
                return;
            }

            if (voteItem.getRewardType() == RewardType.CRAFTCOINS) {
                items.add(ClickableItem.of(new ItemBuilder(Material.GOLD_INGOT).setName("§e" + voteItem.getName()).setLore("§7Cena: §f" + voteItem.getPrice() + " VT").hideAllFlags().build(), click -> {
                    VoteTokensAPI.takeVoteTokens(player, voteItem.getPrice());
                    CraftCoinsAPI.giveCoins(player, voteItem.getEconomyReward());
                    player.closeInventory();
                }));
                return;
            }

            if (voteItem.getRewardType() == RewardType.MONEY) {
                items.add(ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§b" + voteItem.getName()).setLore("§7Cena: §f" + voteItem.getPrice() + " VT").hideAllFlags().build(), click -> {
                    VoteTokensAPI.takeVoteTokens(player, voteItem.getPrice());
                    VaultUtils vault = new VaultUtils();
                    vault.depositPlayer(player, voteItem.getEconomyReward());
                    player.closeInventory();
                }));
                return;
            }

            if (voteItem.getRewardType() == RewardType.PERMISSION) {
                items.add(ClickableItem.of(new ItemBuilder(voteItem.getItemStack()).setName("§b" + voteItem.getName()).setLore("§7Cena: §f" + voteItem.getPrice() + " VT").hideAllFlags().build(), click -> {
                    VoteTokensAPI.takeVoteTokens(player, voteItem.getPrice());
                    voteItem.getPermisions().forEach((permission) -> {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission settemp " + permission + " true " + voteItem.getTimed() + "h " + Main.getInstance().getServerType().name.toLowerCase());
                    });
                    player.sendMessage("§aZakoupi jsi si §f" + voteItem.getName());
                    player.closeInventory();
                }));
                return;
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
            SmartInventory.builder().size(6, 9).title("[" + Main.getInstance().getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
        }));

        SlotIterator slotIterator = contents.newIterator("cshop-voteshop", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
