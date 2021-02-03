package cz.wake.manager.managers;

import cz.wake.manager.Main;
import cz.wake.manager.shop.types.PermissionItem;
import cz.wake.manager.shop.types.RewardType;
import cz.wake.manager.shop.types.VoteItem;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CshopManager {

    private static List<PermissionItem> permsShopItems = new ArrayList<>();
    private static List<VoteItem> voteShopItems = new ArrayList<>();
    private static List<VoteItem> itemsShopItems = new ArrayList<>(); // Nice jméno
    private static List<VoteItem> eventShopItems = new ArrayList<>();
    private static List<PermissionItem> cosmeticsShopItems = new ArrayList<>();

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
        loadEventShopItems();
        loadCosmeticShopItems();
    }

    public List<PermissionItem> getPermsShopItems() {
        return permsShopItems;
    }

    public List<VoteItem> getVoteShopItems() {
        return voteShopItems;
    }

    public List<VoteItem> getItemsShopItems() {
        return itemsShopItems;
    }

    public List<VoteItem> getEventShopItems() {
        return eventShopItems;
    }

    public List<PermissionItem> getCosmeticsShopItems() { return cosmeticsShopItems; }

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
        if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
            voteShopItems.add(new VoteItem().setName("1x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(1).setCommandToExecute("crate give physical Vote 1 %player%"));
            voteShopItems.add(new VoteItem().setName("5x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(5).setCommandToExecute("crate give physical Vote 5 %player%"));
        }
        if (Main.getServerType() == ServerType.SKYCLOUD) {
            voteShopItems.add(new VoteItem().setName("1x Emerald").setPrice(2).setEconomyReward(1, RewardType.MONEY));
            voteShopItems.add(new VoteItem().setName("4x Emerald").setPrice(7).setEconomyReward(7, RewardType.MONEY));
        }
        if (Main.getServerType() == ServerType.CREATIVE) {
            voteShopItems.add(new VoteItem().setItemStack(Material.WOODEN_AXE).setRequiredLevel(5).setName("WorldEdit (2h)").setPrice(2).setTimed(2).setPermisions("worldedit.brush.*", "worldedit.clipboard.(copy|cut|flip|paste|rotate)", "worldedit.fill", "worldedit.wand", "worldedit.history.(redo|undo)", "worldedit.region.(center|set|walls|move|overlay)", "worldedit.selection.(pos|chunk)", "worldedit.navigation.up", "fawe.worldguard"));
        }
        voteShopItems.add(new VoteItem().setName("30 CraftCoins").setPrice(1).setEconomyReward(30, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("100 CraftCoins").setPrice(3).setEconomyReward(100, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("220 CraftCoins").setPrice(6).setEconomyReward(220, RewardType.CRAFTCOINS));
    }

    private void loadItemShopItems() {
        itemsShopItems.add(new VoteItem().setName("Vlastní hlava").setItemStack(Material.PLAYER_HEAD).setPrice(750).setCommandToExecute("give %player% minecraft:player_head{\"SkullOwner\":\"%player%\"}"));
        if (Main.getServerType() == ServerType.SKYBLOCK) {
            itemsShopItems.add(new VoteItem().setName("Minion: Sheeps").setRequiredLevel(3).setItemStack(Material.WHITE_WOOL).setPrice(1500).setCommandToExecute("msetup give minion sheep %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Nether Wart").setRequiredLevel(5).setItemStack(Material.NETHER_WART).setPrice(3000).setCommandToExecute("msetup give minion netherwart %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Dark Oak").setRequiredLevel(2).setItemStack(Material.DARK_OAK_LOG).setPrice(800).setCommandToExecute("msetup give minion darkoak %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Carrot Farmer").setRequiredLevel(5).setItemStack(Material.CARROT).setPrice(1000).setCommandToExecute("msetup give minion carrot %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Wheat Farmer").setRequiredLevel(5).setItemStack(Material.WHEAT).setPrice(1000).setCommandToExecute("msetup give minion wheat %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Fisherman").setRequiredLevel(2).setItemStack(Material.FISHING_ROD).setPrice(500).setCommandToExecute("msetup give minion fisher %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Cactus").setRequiredLevel(5).setItemStack(Material.CACTUS).setPrice(1500).setCommandToExecute("msetup give minion cactus %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Birch").setRequiredLevel(2).setItemStack(Material.BIRCH_LOG).setPrice(800).setCommandToExecute("msetup give minion birch %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Chicken").setRequiredLevel(2).setItemStack(Material.COOKED_CHICKEN).setPrice(1000).setCommandToExecute("msetup give minion chicken %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Pig").setRequiredLevel(2).setItemStack(Material.PORKCHOP).setPrice(1000).setCommandToExecute("msetup give minion pig %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Coal").setRequiredLevel(10).setItemStack(Material.COAL_ORE).setPrice(3500).setCommandToExecute("msetup give minion coal %player% 1"));
            itemsShopItems.add(new VoteItem().setName("Minion: Collector").setLore("§7Speciální Minion, co sbírá", "§7itemy v okolí do truhly").setRequiredLevel(15).setItemStack(Material.HOPPER).setPrice(5000).setCommandToExecute("msetup give minion collector %player% 1"));
            //TODO: Lapis
        }
    }

    private void loadEventShopItems() {
        eventShopItems.add(new VoteItem().setName("50x CraftCoins").setPrice(2).setEconomyReward(50, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("120x CraftCoins").setPrice(5).setEconomyReward(120, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("280x CraftCoins").setPrice(12).setEconomyReward(280, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("1x CraftToken").setPrice(125).setEconomyReward(1, RewardType.CRAFTTOKEN));
        if (Main.getServerType() == ServerType.CREATIVE || Main.getServerType() == ServerType.SKYCLOUD || Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK) {
            eventShopItems.add(new VoteItem().setName("ArmorStandEditor (2h)").setPrice(10).setCommandToExecute("lp user %player% permission settemp asedit.* true 2h %server%"));
        }
    }

    private void loadCosmeticShopItems() {
        cosmeticsShopItems.add(new PermissionItem().setName("§fKoala").setLore("§7Čepice koaly pro kamaráda pandy!").setItemStack(Material.BOOK).setPrice(350).setPermision("craftmanager.hats.koala"));
        cosmeticsShopItems.add(new PermissionItem().setName("§fPanda").setLore("§7Čepice pandy pro kamaráda koaly!").setItemStack(Material.BOOK).setPrice(350).setPermision("craftmanager.hats.panda"));
        cosmeticsShopItems.add(new PermissionItem().setName("§dKlaun").setLore("§7Chceš vypadat jako klaun? Nebo už jsi?!").setItemStack(Material.BOOK).setPrice(500).setPermision("craftmanager.hats.clown"));
        cosmeticsShopItems.add(new PermissionItem().setName("§fUnicorn").setLore("§7Čepice se kterou si nasadíš na hlavu roh.").setItemStack(Material.BOOK).setPrice(350).setPermision("craftmanager.hats.unicorn"));
        cosmeticsShopItems.add(new PermissionItem().setName("§bBeer Hat").setLore("§7S touto čepicí už nikdy nebudeš mít žízeň.").setItemStack(Material.BOOK).setPrice(400).setPermision("craftmanager.hats.beer_hat"));
        cosmeticsShopItems.add(new PermissionItem().setName("§6Trex").setLore("§7Velká tlama, velký zuby a všichni utekli!").setItemStack(Material.BOOK).setPrice(750).setPermision("craftmanager.hats.trex"));
        cosmeticsShopItems.add(new PermissionItem().setName("§eXXXTENCATION").setLore("§7Pořádný vlasy, a rap co ti zvedne tep.").setItemStack(Material.BOOK).setPrice(500).setPermision("craftmanager.hats.xxxtencation"));
    }

}
