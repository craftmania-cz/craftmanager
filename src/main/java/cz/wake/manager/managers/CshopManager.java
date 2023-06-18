package cz.wake.manager.managers;

import cz.craftmania.crafteconomy.objects.LevelType;
import cz.craftmania.craftpack.api.*;
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
    private static List<VoteItem> seasonShopItems = new ArrayList<>();
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
        loadSeasonShopItems();
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

    public List<VoteItem> getSeasonShopItems() {
        return seasonShopItems;
    }

    private void loadPermsShopItems() {
        if (Main.getInstance().getServerType() == ServerType.SURVIVAL && Main.getInstance().getLevelType() == LevelType.SURVIVAL_117_LEVEL) {
            permsShopItems.add(new PermissionItem().setName("Residence 200x200").setItemStack(Material.WOODEN_HOE).setPrice(1000).setPermision("residence.group.boost_200").setRequiredLevel(15));
            permsShopItems.add(new PermissionItem().setName("Residence 300x300").setItemStack(Material.STONE_HOE).setPrice(2500).setPermision("residence.group.boost_300").setRequiredLevel(20));
            permsShopItems.add(new PermissionItem().setName("Residence 400X400").setItemStack(Material.STONE_HOE).setPrice(5000).setPermision("residence.group.boost_400").setRequiredLevel(23));
            permsShopItems.add(new PermissionItem().setName("Residence 500x500").setItemStack(Material.IRON_HOE).setPrice(7500).setPermision("residence.group.boost_500").setRequiredLevel(26));
            permsShopItems.add(new PermissionItem().setName("Residence 600x600").setItemStack(Material.IRON_HOE).setPrice(12500).setPermision("residence.group.boost_600").setRequiredLevel(35));
            permsShopItems.add(new PermissionItem().setName("Residence 750x750").setItemStack(Material.DIAMOND_HOE).setPrice(25000).setPermision("residence.group.boost_750").setRequiredLevel(40));
            permsShopItems.add(new PermissionItem().setName("Residence 1000x1000").setItemStack(Material.DIAMOND_HOE).setPrice(25000).setPermision("residence.group.boost_1000").setRequiredLevel(50));
        }
        if (Main.getInstance().getServerType() == ServerType.SURVIVAL && Main.getInstance().getLevelType() == LevelType.SURVIVAL_118_LEVEL) {
            permsShopItems.add(new PermissionItem().setName("Lands: Být 10x přidán v Landu").setItemStack(Material.BEETROOT).setPrice(1500).setPermision("lands.lands.10").setRequiredLevel(10));
            permsShopItems.add(new PermissionItem().setName("Lands: Být 20x přidán v Landu").setItemStack(Material.BEETROOT).setPrice(3500).setPermision("lands.lands.20").setRequiredLevel(20));
        }
    }

    private void loadVoteShopItems() {
        if (Main.getInstance().getServerType() == ServerType.SKYBLOCK) { // Od Skyblocku 1.19 již neexistuje Vote Crate
            voteShopItems.add(new VoteItem().setName("1x BasicCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(3).setCommandToExecute("crate give physical Basic 1 %player%"));
            voteShopItems.add(new VoteItem().setName("5x BasicCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(15).setCommandToExecute("crate give physical Basic 5 %player%"));
            voteShopItems.add(new VoteItem().setName("10x BasicCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(30).setCommandToExecute("crate give physical Basic 10 %player%"));
            voteShopItems.add(new VoteItem().setName("32x BasicCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(96).setCommandToExecute("crate give physical Basic 32 %player%"));
            voteShopItems.add(new VoteItem().setName("64x BasicCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(192).setCommandToExecute("crate give physical Basic 64 %player%"));
        } else if (Main.getInstance().getServerType() == ServerType.SURVIVAL || Main.getInstance().getServerType() == ServerType.PRISON) {
            voteShopItems.add(new VoteItem().setName("1x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(2).setCommandToExecute("crate give physical Vote 1 %player%"));
            voteShopItems.add(new VoteItem().setName("5x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(10).setCommandToExecute("crate give physical Vote 5 %player%"));
            voteShopItems.add(new VoteItem().setName("10x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(20).setCommandToExecute("crate give physical Vote 10 %player%"));
            voteShopItems.add(new VoteItem().setName("32x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(64).setCommandToExecute("crate give physical Vote 32 %player%"));
            voteShopItems.add(new VoteItem().setName("64x VoteCrate Key").setItemStack(Material.TRIPWIRE_HOOK).setPrice(128).setCommandToExecute("crate give physical Vote 64 %player%"));
        }
        if (Main.getInstance().getServerType() == ServerType.SKYCLOUD) {
            voteShopItems.add(new VoteItem().setName("1x Emerald").setPrice(2).setEconomyReward(1, RewardType.MONEY));
            voteShopItems.add(new VoteItem().setName("4x Emerald").setPrice(7).setEconomyReward(7, RewardType.MONEY));
        }
        if (Main.getInstance().getServerType() == ServerType.CREATIVE) {
            voteShopItems.add(new VoteItem().setItemStack(Material.WOODEN_AXE).setRequiredLevel(3).setName("WorldEdit (2h)").setPrice(2).setTimed(2).setPermisions("worldedit.brush.*", "worldedit.clipboard.(copy|cut|flip|paste|rotate)", "worldedit.fill", "worldedit.wand", "worldedit.history.(redo|undo)", "worldedit.selection.*", "worldedit.tool.*", "worldedit.region.*", "worldedit.(drain|replacenear|snow|thaw|regen)", "worldedit.generation.*", "worldedit.navigation.up", "fawe.worldguard"));
        }
        voteShopItems.add(new VoteItem().setName("15 CraftCoins").setPrice(1).setEconomyReward(15, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("60 CraftCoins").setPrice(4).setEconomyReward(60, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("120 CraftCoins").setPrice(8).setEconomyReward(120, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("240 CraftCoins").setPrice(16).setEconomyReward(240, RewardType.CRAFTCOINS));
        voteShopItems.add(new VoteItem().setName("Cosmetics: Mechafist Hand").setItemStack(Material.STICK).setPrice(200).setCommandToExecute("cosadmin %player% mecha-fist %player%"));
        voteShopItems.add(new VoteItem().setName("Cosmetics: Baseball Bat").setItemStack(Material.STICK).setPrice(50).setCommandToExecute("cosadmin %player% baseball-bat %player%"));
    }

    private void loadItemShopItems() {
        itemsShopItems.add(new VoteItem().setName("Vlastní hlava").setItemStack(Material.PLAYER_HEAD).setPrice(750).setCommandToExecute("give %player% minecraft:player_head{\"SkullOwner\":\"%player%\"}"));
    }

    private void loadEventShopItems() {
        eventShopItems.add(new VoteItem().setName("20x CraftCoins").setPrice(2).setEconomyReward(20, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("50x CraftCoins").setPrice(5).setEconomyReward(50, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("100x CraftCoins").setPrice(10).setEconomyReward(100, RewardType.CRAFTCOINS));
        eventShopItems.add(new VoteItem().setName("1x CraftToken").setPrice(200).setEconomyReward(1, RewardType.CRAFTTOKEN));
        eventShopItems.add(new VoteItem().setName("§bCosmetics: §fCandy").setPrice(50).setCommandToExecute("lp user %player% permission set craftmanager.hats.candy_hat"));
        eventShopItems.add(new VoteItem().setName("§bCosmetics: §fTiki Mask").setPrice(50).setCommandToExecute("lp user %player% permission set craftmanager.hats.tiki_mask"));
        if (Main.getInstance().getServerType() == ServerType.CREATIVE || Main.getInstance().getServerType() == ServerType.SKYCLOUD || Main.getInstance().getServerType() == ServerType.SURVIVAL || Main.getInstance().getServerType() == ServerType.SKYBLOCK) {
            eventShopItems.add(new VoteItem().setName("ArmorStandEditor (2h)").setPrice(10).setCommandToExecute("lp user %player% permission settemp asedit.* true 2h %server%"));
        }
        eventShopItems.add(new VoteItem().setName("Cosmetics: Drak Balloon").setPrice(30).setCommandToExecute("lp user %player% permission set craftmanager.balloons.kite"));
        eventShopItems.add(new VoteItem().setName("Cosmetics: Crystal Wings").setPrice(50).setCommandToExecute("lp user %player% permission set craftmanager.backpack.wings_crystal"));
        eventShopItems.add(new VoteItem().setName("Cosmetics: Crystal Dream Wings").setPrice(60).setCommandToExecute("lp user %player% permission set craftmanager.backpack.wings_crystal_dream"));
        eventShopItems.add(new VoteItem().setName("Cosmetics: Roses Old Wings").setPrice(50).setCommandToExecute("lp user %player% permission set craftmanager.backpack.wings_roses_old"));
    }

    private void loadCosmeticShopItems() {
        cosmeticsShopItems.add(new PermissionItem().setName("§fKoala").setLore("§7Čepice koaly pro kamaráda pandy!").setItemStack(Hats.KOALA.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.koala"));
        cosmeticsShopItems.add(new PermissionItem().setName("§fPanda").setLore("§7Čepice pandy pro kamaráda koaly!").setItemStack(Hats.PANDA.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.panda"));
        cosmeticsShopItems.add(new PermissionItem().setName("§dKlaun").setLore("§7Chceš vypadat jako klaun? Nebo už jsi?!").setItemStack(Hats.CLOWN.getPureItemStack()).setPrice(800).setPermision("craftmanager.hats.clown"));
        cosmeticsShopItems.add(new PermissionItem().setName("§fUnicorn").setLore("§7Čepice se kterou si nasadíš na hlavu roh.").setItemStack(Hats.UNICORN.getPureItemStack()).setPrice(150).setPermision("craftmanager.hats.unicorn"));
        cosmeticsShopItems.add(new PermissionItem().setName("§bBeer Hat").setLore("§7S touto čepicí už nikdy nebudeš mít žízeň.").setItemStack(Hats.BEER_HAT.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.beer_hat"));
        cosmeticsShopItems.add(new PermissionItem().setName("§6Trex").setLore("§7Velká tlama, velký zuby a všichni utekli!").setItemStack(Hats.TREX.getPureItemStack()).setPrice(1000).setPermision("craftmanager.hats.trex"));
        cosmeticsShopItems.add(new PermissionItem().setName("§eXXXTentacion").setLore("§7Pořádný vlasy, a rap co ti zvedne tep.").setItemStack(Hats.XXXTENCATION.getPureItemStack()).setPrice(800).setPermision("craftmanager.hats.xxxtencation")); // Old název, bug wake je boomer a posral název no
        cosmeticsShopItems.add(new PermissionItem().setName("§dBunny Ears").setLore("§7Velké uši, velký čumák.. králíček!").setItemStack(Hats.BUNNY_EARS.getPureItemStack()).setPrice(500).setPermision("craftmanager.hats.bunny_ears"));
        cosmeticsShopItems.add(new PermissionItem().setName("§aŽabička").setLore("§7Žabičky ze všech bažin jsou zde!").setItemStack(Hats.ZABICKA.getPureItemStack()).setPrice(500).setPermision("craftmanager.hats.zabicka"));
        cosmeticsShopItems.add(new PermissionItem().setName("§eKachnička").setLore("§7Kachničky od řeky přímo na hlavu.").setItemStack(Hats.KACHNICKA.getPureItemStack()).setPrice(500).setPermision("craftmanager.hats.kachnicka"));
        cosmeticsShopItems.add(new PermissionItem().setName("§bDiamond Block na prutu").setLore("§7Každý jde za diamanty, půjdeš i za tímhle?").setItemStack(Hats.DIAMOND_PRUT.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.diamond_prut"));
        cosmeticsShopItems.add(new PermissionItem().setName("§9Nerdy Glasses").setLore("§7Koukáním do počítače časem ztratíš zrak...", "§7s těmito brýlemi ne!").setItemStack(Hats.NERD_GLASSES_1.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.nerdy_glasses"));
        cosmeticsShopItems.add(new PermissionItem().setName("§cDeer").setLore("§7Jelenovipivonelej").setItemStack(Hats.DEER.getPureItemStack()).setPrice(500).setPermision("craftmanager.hats.deer"));
        cosmeticsShopItems.add(new PermissionItem().setName("§aShrek").setLore("§7Správný vládce bažiny má svojí čepici!").setItemStack(Material.BOOK).setPrice(400).setPermision("craftmanager.hats.shrek"));
        cosmeticsShopItems.add(new PermissionItem().setName("§aFlowers").setLore("§7Kytičky rostou všude, i na tví hlavě?!").setItemStack(Hats.FLOWERS.getPureItemStack()).setPrice(500).setPermision("craftmanager.hats.flowers"));
        cosmeticsShopItems.add(new PermissionItem().setName("§eCat Ears").setLore("§7Kočička je hodná, do doby než tě poškrábe!").setItemStack(Hats.CAT_EARS.getPureItemStack()).setPrice(200).setPermision("craftmanager.hats.cat_ears"));
        cosmeticsShopItems.add(new PermissionItem().setName("§6Pirate Hat").setLore("§7Každý správný pirát má pásku přes oko!").setItemStack(Hats.PIRATE_HAT.getPureItemStack()).setPrice(250).setPermision("craftmanager.hats.pirate_hat"));
        cosmeticsShopItems.add(new PermissionItem().setName("§eHalo Ring").setLore("§7Jen anděl může mít tento kroužek nad hlavou!").setItemStack(Hats.HALO.getPureItemStack()).setPrice(250).setPermision("craftmanager.hats.halo_ring"));
        cosmeticsShopItems.add(new PermissionItem().setName("§bPenguin").setLore("§7Tučnáci jsou všude...").setItemStack(Hats.PENGUIN.getPureItemStack()).setPrice(400).setPermision("craftmanager.hats.penguin"));
        cosmeticsShopItems.add(new PermissionItem().setName("§cImpostor").setLore("§7Někdo tu je sus a Lilmayu to není?!").setItemStack(Hats.IMPOSTOR.getPureItemStack()).setPrice(300).setPermision("craftmanager.hats.impostor"));
        cosmeticsShopItems.add(new PermissionItem().setName("§bShark").setLore("§7Zakousl se ti žralok do hlavy..").setItemStack(Hats.SHARK.getPureItemStack()).setPrice(700).setPermision("craftmanager.hats.shark_hat"));
    }

    private void loadSeasonShopItems() {
        /*if (System.currentTimeMillis() >= 1638486000000L) {
            seasonShopItems.add(new VoteItem().setName("§c§lBaseball Bat").setHideWhenBuy("craftmanager.cshop.baseball_bat").setPrice(1).setItemStack(HandItems.BASEBALL_BAT.getPureItemStack()).setCommandToExecute("cosadmin %player% baseball-bat %player%"));
        }
        if (System.currentTimeMillis() >= 1639609200000L) {
            seasonShopItems.add(new VoteItem().setName("§6§lGinger Pickaxe").setHideWhenBuy("craftmanager.cshop.ginger_pickaxe").setPrice(1).setItemStack(Pickaxes.GINGER_PICKAXE.getPureItemStack()).setCommandToExecute("cosadmin %player% ginger-pickaxe %player%"));
        }
        if (System.currentTimeMillis() >= 1640300400000L) {
            seasonShopItems.add(new VoteItem().setName("§b§lFrost Bow").setHideWhenBuy("craftmanager.cshop.frost_bow").setPrice(1).setItemStack(Bows.FROST.getPureItemStack()).setCommandToExecute("cosadmin %player% frost-bow %player%"));
        }
        if (System.currentTimeMillis() >= 1640473200000L) {
            seasonShopItems.add(new VoteItem().setName("§b§lFrost Sword").setHideWhenBuy("craftmanager.cshop.frost_sword").setPrice(1).setItemStack(Swords.FROST_SWORD.getPureItemStack()).setCommandToExecute("cosadmin %player% frost-sword %player%"));
        }*/
        //seasonShopItems.add(new VoteItem().setName("§d§lClown Set").setPrice(1).setItemStack(Material.NETHERITE_SWORD).setCommandToExecute("cosadmin %player% clown-set %player%"));
    }

}
