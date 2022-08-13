package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftpack.api.*;
import cz.wake.manager.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("cosadmin")
@Description("Příkaz pro konzoli na dávání zakoupených nebo vyhraných cosmetics")
public class CosAdmin_command extends BaseCommand {

    private InventoryUtils inventoryUtils = new InventoryUtils();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lCosmetics Admin příkazy:");
        help.showHelp();
    }

    @Default
    @CommandCompletion("[player] [type] [loreNick]")
    @Syntax("[player] [type] [loreNick]")
    public void onCommand(CommandSender sender, String player, String type, String loreNick) {
        if (sender instanceof Player) {
            sender.sendMessage("§cTento příkaz může používat jenom konzole.");
            return;
        }
        if (player == null) {
            sender.sendMessage("§c§lNenapsal jsi nick hráče.");
            return;
        }
        if (type == null) {
            sender.sendMessage("§c§Neuvedl jsi type.");
            return;
        }
        Player onlinePlayer = Bukkit.getPlayer(player);
        if (onlinePlayer == null) {
            sender.sendMessage("§cZadaný hráč je offline!");
            return;
        }
        if (loreNick == null) {
            loreNick = onlinePlayer.getName();
        }
        switch (type) {
            case "ice_rose_set" -> {
                ItemStack ice_rose_sword = new ItemBuilder(Swords.ICE_ROSE_SWORD.getPureItemStack())
                        .setName("§b§lIce Rose Sword").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_sword);
                ItemStack ice_rose_pickaxe = new ItemBuilder(Pickaxes.ICE_ROSE_PICKAXE.getPureItemStack())
                        .setName("§b§lIce Rose Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_pickaxe);
                ItemStack ice_rose_axe = new ItemBuilder(Axes.ICE_ROSE_AXE.getPureItemStack())
                        .setName("§b§lIce Rose Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_axe);
                ItemStack ice_rose_hoe = new ItemBuilder(Hoes.ICE_ROSE_HOE.getPureItemStack())
                        .setName("§b§lIce Rose Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_hoe);
                ItemStack ice_rose_shovel = new ItemBuilder(Shovels.ICE_ROSE_SHOVEL.getPureItemStack())
                        .setName("§b§lIce Rose Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_shovel);
                ItemStack ice_rose_crown = new ItemBuilder(Hats.ICE_ROSE_CROWN.getPureItemStack())
                        .setName("§b§lIce Rose Crown").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "§8Klikni pravým k nasazení.").build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ice_rose_crown);
                sender.sendMessage("§eDal jsi hráči " + player + " Ice Rose Set!");
            }
            case "plant-set" -> {
                ItemStack plant_set_sword = new ItemBuilder(Swords.PLANT_SET_SWORD.getPureItemStack())
                        .setName("§a§lPlant Set Sword").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, plant_set_sword);
                ItemStack plant_set_pickaxe = new ItemBuilder(Pickaxes.PLANT_SET_PICKAXE.getPureItemStack())
                        .setName("§a§lPlant Set Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, plant_set_pickaxe);
                ItemStack plant_set_axe = new ItemBuilder(Axes.PLANT_SET.getPureItemStack())
                        .setName("§a§lPlant Set Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, plant_set_axe);
                ItemStack plant_set_hoe = new ItemBuilder(Hoes.PLANT_SET_HOE.getPureItemStack())
                        .setName("§a§lPlant Set Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, plant_set_hoe);
                ItemStack plant_set_shovel = new ItemBuilder(Shovels.PLANT_SET_SHOVEL.getPureItemStack())
                        .setName("§a§lPlant Set Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, plant_set_shovel);
                sender.sendMessage("§eDal jsi hráči " + player + " Plant Set!");
            }
            case "ignite-set" -> {
                ItemStack ignite_set_sword = new ItemBuilder(Material.NETHERITE_SWORD).setCustomModelData(100100)
                        .setName("§c§lIgnite Set Sword").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_set_sword);
                ItemStack ignite_set_pickaxe = new ItemBuilder(Material.NETHERITE_PICKAXE).setCustomModelData(100100)
                        .setName("§c§lIgnite Set Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_set_pickaxe);
                ItemStack ignite_set_axe = new ItemBuilder(Material.NETHERITE_AXE).setCustomModelData(100100)
                        .setName("§c§lIgnite Set Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_set_axe);
                ItemStack ignite_set_shovel = new ItemBuilder(Material.NETHERITE_SHOVEL).setCustomModelData(100100)
                        .setName("§c§lIgnite Set Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_set_shovel);
                ItemStack ignite_set_hoe = new ItemBuilder(Material.NETHERITE_HOE).setCustomModelData(100100)
                        .setName("§c§lIgnite Set Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_set_hoe);
                ItemStack ignite_crown = new ItemBuilder(Material.SUGAR).setCustomModelData(100003)
                        .setName("§c§lIgnite Crown").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "§8Klikni pravým k nasazení.").build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, ignite_crown);
                sender.sendMessage("§eDal jsi hráči " + player + " Ignite Set!");
            }
            case "clown-set" -> { //TODO: CraftCosmetics
                ItemStack clown_set_sword = new ItemBuilder(Material.NETHERITE_SWORD).setCustomModelData(100150)
                        .setName("§d§lClown Sword").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_sword);
                ItemStack clown_set_pickaxe = new ItemBuilder(Material.NETHERITE_PICKAXE).setCustomModelData(100150)
                        .setName("§d§lClown Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_pickaxe);
                ItemStack clown_set_axe = new ItemBuilder(Material.NETHERITE_AXE).setCustomModelData(100150)
                        .setName("§d§lClown Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_axe);
                ItemStack clown_set_hoe = new ItemBuilder(Material.NETHERITE_HOE).setCustomModelData(100150)
                        .setName("§d§lClown Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_hoe);
                ItemStack clown_set_shovel = new ItemBuilder(Material.NETHERITE_SHOVEL).setCustomModelData(100150)
                        .setName("§d§lClown Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_shovel);
                ItemStack clown_set_bow = new ItemBuilder(Material.BOW).setCustomModelData(100150)
                        .setName("§d§lClown Bow").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_bow);
                ItemStack clown_set_hat = new ItemBuilder(Material.SUGAR).setCustomModelData(100002)
                        .setName("§d§lClown Hat").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, clown_set_hat);

                String helmetNBTCommand = "give " + onlinePlayer.getName() + " leather_helmet{AttributeModifiers:[{Amount:3.0d,AttributeName:\"minecraft:generic.armor\",Name:\"itemsadder\",Operation:0,Slot:\"head\",UUID:[I;-640943352,475025341,-1612702938,-1029972928]},{Amount:3.0d,AttributeName:\"minecraft:generic.armor_toughness\",Name:\"itemsadder\",Operation:0,Slot:\"head\",UUID:[I;-1500238679,1679903797,-1761362150,534057705]}],CustomModelData:100150,Damage:0,HideFlags:64,display:{Name:'[{\"text\":\"Clown Helmet\",\"italic\":false,\"bold\":true,\"color\":\"light_purple\"}]',Lore:['[{\"text\":\"Zakoupil:\",\"italic\":false,\"color\":\"gray\"},{\"text\":\" \",\"color\":\"dark_purple\"},{\"text\":\"" + loreNick + "\",\"color\":\"white\"}]'],color:14024705},itemsadder:{custom_durability:592,fake_durability:55.0d,id:\"clown_helmet\",max_custom_durability:592,namespace:\"craftmania\"}} 1";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), helmetNBTCommand);

                String chestplateNBTCommand = "give " + onlinePlayer.getName() + " leather_chestplate{AttributeModifiers:[{Amount:8.0d,AttributeName:\"minecraft:generic.armor\",Name:\"itemsadder\",Operation:0,Slot:\"chest\",UUID:[I;-1505723297,1286358069,-2075431994,1677546357]},{Amount:3.0d,AttributeName:\"minecraft:generic.armor_toughness\",Name:\"itemsadder\",Operation:0,Slot:\"chest\",UUID:[I;-177746632,-1097449221,-2076924959,-1423219744]}],CustomModelData:100150,Damage:0,HideFlags:64,display:{Name:'[{\"text\":\"Clown Chestplate\",\"italic\":false,\"bold\":true,\"color\":\"light_purple\"}]',Lore:['[{\"text\":\"Zakoupil:\",\"italic\":false,\"color\":\"gray\"},{\"text\":\" \",\"color\":\"dark_purple\"},{\"text\":\"" + loreNick + "\",\"color\":\"white\"}]'],color:14024705},itemsadder:{custom_durability:592,fake_durability:80.0d,id:\"clown_chestplate\",max_custom_durability:592,namespace:\"craftmania\"}} 1";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), chestplateNBTCommand);

                String leggingsNBTCommand = "give " + onlinePlayer.getName() + " leather_leggings{AttributeModifiers:[{Amount:6.0d,AttributeName:\"minecraft:generic.armor\",Name:\"itemsadder\",Operation:0,Slot:\"legs\",UUID:[I;1415761501,445729875,-1119614081,-1995242445]},{Amount:3.0d,AttributeName:\"minecraft:generic.armor_toughness\",Name:\"itemsadder\",Operation:0,Slot:\"legs\",UUID:[I;-1937686728,14370072,-1750558610,-931238791]}],CustomModelData:100150,Damage:0,HideFlags:64,display:{Name:'[{\"text\":\"Clown Leggings\",\"italic\":false,\"bold\":true,\"color\":\"light_purple\"}]',Lore:['[{\"text\":\"Zakoupil:\",\"italic\":false,\"color\":\"gray\"},{\"text\":\" \",\"color\":\"dark_purple\"},{\"text\":\"" + loreNick + "\",\"color\":\"white\"}]'],color:14024705},itemsadder:{custom_durability:592,fake_durability:75.0d,id:\"clown_leggins\",max_custom_durability:592,namespace:\"craftmania\"}} 1";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), leggingsNBTCommand);

                String bootsNBTCommand = "give " + onlinePlayer.getName() + " leather_boots{AttributeModifiers:[{Amount:3.0d,AttributeName:\"minecraft:generic.armor\",Name:\"itemsadder\",Operation:0,Slot:\"feet\",UUID:[I;1633978470,467225177,-1598479421,1944354831]},{Amount:3.0d,AttributeName:\"minecraft:generic.armor_toughness\",Name:\"itemsadder\",Operation:0,Slot:\"feet\",UUID:[I;1787998139,1232881052,-1800790762,-1285596720]}],CustomModelData:100150,Damage:0,HideFlags:64,display:{Name:'[{\"text\":\"Clown Boots\",\"italic\":false,\"bold\":true,\"color\":\"light_purple\"}]',Lore:['[{\"text\":\"Zakoupil:\",\"italic\":false,\"color\":\"gray\"},{\"text\":\" \",\"color\":\"dark_purple\"},{\"text\":\"" + loreNick + "\",\"color\":\"white\"}]'],color:14024705},itemsadder:{custom_durability:592,fake_durability:65.0d,id:\"clown_boots\",max_custom_durability:592,namespace:\"craftmania\"}} 1";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), bootsNBTCommand);
            }
            case "pomlazka" -> {
                ItemStack pomlazka = new ItemBuilder(HandItems.EASTER_STICK.getPureItemStack())
                        .setName("§9§lPomlázka").setLore("§7Rarita: §f" + TextureItems.RARITY_LIMITED.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, pomlazka);
                sender.sendMessage("§eDal jsi hráči " + player + " Pomlázku!");
            }
            case "suitcase" -> {
                ItemStack suitcase = new ItemBuilder(HandItems.KUFR.getPureItemStack())
                        .setName("§e§lKufr").setLore("§7Item naprosto k ničemu", "§7proč ale prostě neodjet", "§7na dovolenou?!", "§7Rarita: §f" + TextureItems.RARITY_UNCOMMON.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, suitcase);
                sender.sendMessage("§eDal jsi hráči " + player + " Suitcase!");
            }
            case "katana" -> {
                ItemStack katana = new ItemBuilder(Swords.KATANA.getPureItemStack())
                        .setName("§e§lKatana").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, katana);
                sender.sendMessage("§eDal jsi hráči " + player + " Katana!");
            }
            case "enderite-sword" -> {
                ItemStack enderite = new ItemBuilder(Swords.ENDERITE.getPureItemStack())
                        .setName("§9§lEnderite").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, enderite);
                sender.sendMessage("§eDal jsi hráči " + player + " Enderite Sword!");
            }
            case "baby-yoda" -> {
                ItemStack baby_yoda = new ItemBuilder(HandItems.BABY_YODA.getPureItemStack())
                        .setName("§a§lBaby Yoda").setLore("§8Malý Yoda, přímo do ruky", "§8ochraňuj ho a neztrať ho.", "§7Majitel: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, baby_yoda);
                sender.sendMessage("§eDal jsi hráči " + player + " Baby Yoda!");
            }
            case "forest-sword" -> {
                ItemStack woodenSword = new ItemBuilder(Swords.FOREST_SWORD.getPureItemStack())
                        .setName("§a§lForest Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, woodenSword);
                sender.sendMessage("§eDal jsi hráči " + player + " Forest Sword!");
            }
            case "forest-bow" -> {
                ItemStack woodenBow = new ItemBuilder(Bows.FOREST.getPureItemStack())
                        .setName("§a§lForest Bow").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, woodenBow);
                sender.sendMessage("§eDal jsi hráči " + player + " Forest Bow!");
            }
            case "lightsaber-red" -> {
                ItemStack lightSaberRed = new ItemBuilder(Swords.LIGHTSABER_RED.getPureItemStack())
                        .setName("§e§lRed Lighsaber").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, lightSaberRed);
                sender.sendMessage("§eDal jsi hráči " + player + " Red Lightsaber!");
            }
            case "scythe" -> {
                ItemStack scythe = new ItemBuilder(Swords.SCYTHE.getPureItemStack())
                        .setName("§c§lScythe").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, scythe);
                sender.sendMessage("§eDal jsi hráči " + player + " Scythe!");
            }
            case "swords-scorpion" -> {
                ItemStack scorpion = new ItemBuilder(Swords.SCORPION.getPureItemStack())
                        .setName("§e§lScorpion Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, scorpion);
                sender.sendMessage("§eDal jsi hráči " + player + " Scorpion Sword!");
            }
            case "mecha-fist" -> {
                ItemStack mecha_fist = new ItemBuilder(HandItems.MECHA_FIST.getPureItemStack())
                        .setName("§c§lMecha-Fist").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, mecha_fist);
                sender.sendMessage("§eDal jsi hráči " + player + " Mecha-Fist!");
            }
            case "ginger-pickaxe" -> {
                ItemStack gingerPickaxe = new ItemBuilder(Pickaxes.GINGER_PICKAXE.getPureItemStack())
                        .setName("§6§lGinger Pickaxe").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, gingerPickaxe);
                sender.sendMessage("§eDal jsi hráči " + player + " Ginger-Pickaxe!");
            }
            case "frost-sword" -> {
                ItemStack frostSword = new ItemBuilder(Swords.FROST_SWORD.getPureItemStack())
                        .setName("§b§lFrost Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, frostSword);
                sender.sendMessage("§eDal jsi hráči " + player + " Frost Sword");
            }
            case "frost-bow" -> {
                ItemStack frostBow = new ItemBuilder(Bows.FROST.getPureItemStack())
                        .setName("§b§lFrost Bow").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, frostBow);
                sender.sendMessage("§eDal jsi hráči " + player + " Frost Bow");
            }
            case "baseball-bat" -> {
                ItemStack baseballBat = new ItemBuilder(HandItems.BASEBALL_BAT.getPureItemStack())
                        .setName("§e§lBaseball Bat").setLore("§7Rarita: §f" + TextureItems.RARITY_UNCOMMON.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, baseballBat);
                sender.sendMessage("§eDal jsi hráči " + player + " Baseball Bat");
            }
            case "mecha-sword" -> {
                ItemStack mecha_fist = new ItemBuilder(Swords.MECHA_SWORD.getPureItemStack())
                        .setName("§c§lMecha-Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, mecha_fist);
                sender.sendMessage("§eDal jsi hráči " + player + " Mecha-Sword!");
            }
            case "banana-pickaxe" -> {
                ItemStack banana_pickaxe = new ItemBuilder(Pickaxes.BANANA_PICKAXE.getPureItemStack())
                        .setName("§e§lBanana Pickaxe").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, banana_pickaxe);
                sender.sendMessage("§eDal jsi hráči " + player + " Banana Pickaxe!");
            }
            case "banana-sword" -> {
                ItemStack banana_sword = new ItemBuilder(Swords.BANANA_SWORD.getPureItemStack())
                        .setName("§e§lBanana Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, banana_sword);
                sender.sendMessage("§eDal jsi hráči " + player + " Banana Sword!");
            }
            case "banana-scythe" -> {
                ItemStack banana_scythe = new ItemBuilder(Swords.BANANA_SCYTHE.getPureItemStack())
                        .setName("§e§lBanana Scythe").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, banana_scythe);
                sender.sendMessage("§eDal jsi hráči " + player + " Banana Scythe!");
            }
            case "monkey-tail" -> {
                ItemStack monkey_tail = new ItemBuilder(HandItems.MONKEY_TAIL.getPureItemStack())
                        .setName("§b§lMonkey Tail").setLore("§7Rarita: §f" + TextureItems.RARITY_COMMON.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, monkey_tail);
                sender.sendMessage("§eDal jsi hráči " + player + " Monkey Tail!");
            }
            case "crab-pickaxe" -> {
                ItemStack craftPickaxe = new ItemBuilder(Pickaxes.CRAB_PICKAXE.getPureItemStack())
                        .setName("§c§lCrab Pickaxe").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, craftPickaxe);
                sender.sendMessage("§eDal jsi hráči " + player + " Crab Pickaxe!");
            }
            case "atlantis-staff" -> {
                ItemStack atlantisStaff = new ItemBuilder(HandItems.ATLANTIS_STAFF.getPureItemStack())
                        .setName("§e§lAtlantis Staff").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, atlantisStaff);
                sender.sendMessage("§eDal jsi hráči " + player + " Atlantis Staff!");
            }
            case "boxing_glove" -> {
                ItemStack atlantisStaff = new ItemBuilder(Material.STICK).setCustomModelData(100007)
                        .setName("§c§lBoxovací rukavice").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                inventoryUtils.givePlayerItemOrDrop(onlinePlayer, atlantisStaff);
                sender.sendMessage("§eDal jsi hráči " + player + " Boxovací rukavice!");
            }
        }
    }
}
