package cz.wake.manager.managers;

import cz.wake.manager.Main;
import cz.wake.manager.shop.types.PermissionItem;
import cz.wake.manager.shop.types.VoteItem;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CshopManager {

    private static List<PermissionItem> permsShopItems = new ArrayList<>();
    private static List<VoteItem> voteShopItems = new ArrayList<>();
    private static List<Object> itemsShopItems = new ArrayList<>(); // Nice jméno

    private Main plugin;

    public CshopManager(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Načte všechny itemy do listů podle typu serveru
     */
    public void loadCshop() {
        loadPermsShopItems();
        loadVoteShopItems();
        loadItemShopItems();
    }

    public List<PermissionItem> getPermsShopItems() {
        return permsShopItems;
    }

    public List<VoteItem> getVoteShopItems() {
        return voteShopItems;
    }

    public List<Object> getItemsShopItems() {
        return itemsShopItems;
    }

    private void loadPermsShopItems() {
        if (Main.getServerType() == ServerType.SURVIVAL) {
            permsShopItems.add(new PermissionItem().setName("Residence 200x200").setItemStack(Material.WOODEN_HOE).setPrice(1000).setPermision("residence.group.boost_200").setRequiredLevel(15));
            permsShopItems.add(new PermissionItem().setName("Residence 300x300").setItemStack(Material.STONE_HOE).setPrice(2500).setPermision("residence.group.boost_300").setRequiredLevel(20));
            permsShopItems.add(new PermissionItem().setName("Residence 400X400").setItemStack(Material.STONE_HOE).setPrice(5000).setPermision("residence.group.boost_400").setRequiredLevel(23));
            permsShopItems.add(new PermissionItem().setName("Residence 500x500").setItemStack(Material.IRON_HOE).setPrice(7500).setPermision("residence.group.boost_500").setRequiredLevel(26));
            permsShopItems.add(new PermissionItem().setName("Residence 600x600").setItemStack(Material.IRON_HOE).setPrice(12500).setPermision("residence.group.boost_600").setRequiredLevel(35));
            permsShopItems.add(new PermissionItem().setName("Residence 750x750").setItemStack(Material.DIAMOND_HOE).setPrice(25000).setPermision("residence.group.boost_750").setRequiredLevel(40));
            permsShopItems.add(new PermissionItem().setName("Residence 1000x1000").setItemStack(Material.DIAMOND_HOE).setPrice(25000).setPermision("residence.group.boost_1000").setRequiredLevel(50));
        }
    }

    private void loadVoteShopItems() {
        if (Main.getServerType() == ServerType.SURVIVAL) {
            voteShopItems.add(new VoteItem().setName("1x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(1).setCommandToExecute("crate give physical Vote 1 %player%"));
            voteShopItems.add(new VoteItem().setName("5x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(5).setCommandToExecute("crate give physical Vote 5 %player%"));
        }
        voteShopItems.add(new VoteItem().setName("30 CraftCoins").setPrice(1).setEconomyReward(30));
        voteShopItems.add(new VoteItem().setName("100 CraftCoins").setPrice(3).setEconomyReward(100));
        voteShopItems.add(new VoteItem().setName("220 CraftCoins").setPrice(6).setEconomyReward(220));

    }

    private void loadItemShopItems() {

    }

}