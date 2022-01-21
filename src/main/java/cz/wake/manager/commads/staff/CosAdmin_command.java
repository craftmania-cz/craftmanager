package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftpack.api.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("cosadmin")
@Description("Příkaz pro konzoli na dávání zakoupených nebo vyhraných cosmetics")
public class CosAdmin_command extends BaseCommand {

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
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_sword);
                ItemStack ice_rose_pickaxe = new ItemBuilder(Pickaxes.ICE_ROSE_PICKAXE.getPureItemStack())
                        .setName("§b§lIce Rose Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_pickaxe);
                ItemStack ice_rose_axe = new ItemBuilder(Axes.ICE_ROSE_AXE.getPureItemStack())
                        .setName("§b§lIce Rose Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_axe);
                ItemStack ice_rose_hoe = new ItemBuilder(Hoes.ICE_ROSE_HOE.getPureItemStack())
                        .setName("§b§lIce Rose Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_hoe);
                ItemStack ice_rose_shovel = new ItemBuilder(Shovels.ICE_ROSE_SHOVEL.getPureItemStack())
                        .setName("§b§lIce Rose Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_shovel);
                ItemStack ice_rose_crown = new ItemBuilder(Hats.ICE_ROSE_CROWN.getPureItemStack())
                        .setName("§b§lIce Rose Crown").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "§8Klikni pravým k nasazení.").build();
                this.givePlayerItemOrDrop(onlinePlayer, ice_rose_crown);
                sender.sendMessage("§eDal jsi hráči " + player + " Ice Rose Set!");
            }
            case "plant-set" -> {
                ItemStack plant_set_sword = new ItemBuilder(Swords.PLANT_SET_SWORD.getPureItemStack())
                        .setName("§a§lPlant Set Sword").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, plant_set_sword);
                ItemStack plant_set_pickaxe = new ItemBuilder(Pickaxes.PLANT_SET_PICKAXE.getPureItemStack())
                        .setName("§a§lPlant Set Pickaxe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, plant_set_pickaxe);
                ItemStack plant_set_axe = new ItemBuilder(Axes.PLANT_SET.getPureItemStack())
                        .setName("§a§lPlant Set Axe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, plant_set_axe);
                ItemStack plant_set_hoe = new ItemBuilder(Hoes.PLANT_SET_HOE.getPureItemStack())
                        .setName("§a§lPlant Set Hoe").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, plant_set_hoe);
                ItemStack plant_set_shovel = new ItemBuilder(Shovels.PLANT_SET_SHOVEL.getPureItemStack())
                        .setName("§a§lPlant Set Shovel").setLore("§7Zakoupil: §f" + loreNick, "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, plant_set_shovel);
                sender.sendMessage("§eDal jsi hráči " + player + " Plant Set!");
            }
            case "pomlazka" -> {
                ItemStack pomlazka = new ItemBuilder(HandItems.EASTER_STICK.getPureItemStack())
                        .setName("§9§lPomlázka").setLore("§7Rarita: §f" + TextureItems.RARITY_LIMITED.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, pomlazka);
                sender.sendMessage("§eDal jsi hráči " + player + " Pomlázku!");
            }
            case "suitcase" -> {
                ItemStack suitcase = new ItemBuilder(HandItems.KUFR.getPureItemStack())
                        .setName("§e§lKufr").setLore("§7Item naprosto k ničemu", "§7proč ale prostě neodjet", "§7na dovolenou?!", "§7Rarita: §f" + TextureItems.RARITY_UNCOMMON.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, suitcase);
                sender.sendMessage("§eDal jsi hráči " + player + " Suitcase!");
            }
            case "katana" -> {
                ItemStack katana = new ItemBuilder(Swords.KATANA.getPureItemStack())
                        .setName("§e§lKatana").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, katana);
                sender.sendMessage("§eDal jsi hráči " + player + " Katana!");
            }
            case "enderite-sword" -> {
                ItemStack enderite = new ItemBuilder(Swords.ENDERITE.getPureItemStack())
                        .setName("§9§lEnderite").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, enderite);
                sender.sendMessage("§eDal jsi hráči " + player + " Enderite Sword!");
            }
            case "baby-yoda" -> {
                ItemStack baby_yoda = new ItemBuilder(HandItems.BABY_YODA.getPureItemStack())
                        .setName("§a§lBaby Yoda").setLore("§8Malý Yoda, přímo do ruky", "§8ochraňuj ho a neztrať ho.", "§7Majitel: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, baby_yoda);
                sender.sendMessage("§eDal jsi hráči " + player + " Baby Yoda!");
            }
            case "forest-sword" -> {
                ItemStack woodenSword = new ItemBuilder(Swords.FOREST_SWORD.getPureItemStack())
                        .setName("§a§lForest Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, woodenSword);
                sender.sendMessage("§eDal jsi hráči " + player + " Forest Sword!");
            }
            case "forest-bow" -> {
                ItemStack woodenBow = new ItemBuilder(Bows.FOREST.getPureItemStack())
                        .setName("§a§lForest Bow").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, woodenBow);
                sender.sendMessage("§eDal jsi hráči " + player + " Forest Bow!");
            }
            case "lightsaber-red" -> {
                ItemStack lightSaberRed = new ItemBuilder(Swords.LIGHTSABER_RED.getPureItemStack())
                        .setName("§e§lRed Lighsaber").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, lightSaberRed);
                sender.sendMessage("§eDal jsi hráči " + player + " Red Lightsaber!");
            }
            case "scythe" -> {
                ItemStack scythe = new ItemBuilder(Swords.SCYTHE.getPureItemStack())
                        .setName("§c§lScythe").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, scythe);
                sender.sendMessage("§eDal jsi hráči " + player + " Scythe!");
            }
            case "swords-scorpion" -> {
                ItemStack scorpion = new ItemBuilder(Swords.SCORPION.getPureItemStack())
                        .setName("§e§lScorpion Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, scorpion);
                sender.sendMessage("§eDal jsi hráči " + player + " Scorpion Sword!");
            }
            case "mecha-fist" -> {
                ItemStack mecha_fist = new ItemBuilder(HandItems.MECHA_FIST.getPureItemStack())
                        .setName("§c§lMecha-Fist").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, mecha_fist);
                sender.sendMessage("§eDal jsi hráči " + player + " Mecha-Fist!");
            }
            case "ginger-pickaxe" -> {
                ItemStack gingerPickaxe = new ItemBuilder(Pickaxes.GINGER_PICKAXE.getPureItemStack())
                        .setName("§6§lGinger Pickaxe").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, gingerPickaxe);
                sender.sendMessage("§eDal jsi hráči " + player + " Ginger-Pickaxe!");
            }
            case "frost-sword" -> {
                ItemStack frostSword = new ItemBuilder(Swords.FROST_SWORD.getPureItemStack())
                        .setName("§b§lFrost Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, frostSword);
                sender.sendMessage("§eDal jsi hráči " + player + " Frost Sword");
            }
            case "frost-bow" -> {
                ItemStack frostBow = new ItemBuilder(Bows.FROST.getPureItemStack())
                        .setName("§b§lFrost Bow").setLore("§7Rarita: §f" + TextureItems.RARITY_LEGENDARY.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, frostBow);
                sender.sendMessage("§eDal jsi hráči " + player + " Frost Bow");
            }
            case "baseball-bat" -> {
                ItemStack baseballBat = new ItemBuilder(HandItems.BASEBALL_BAT.getPureItemStack())
                        .setName("§e§lBaseball Bat").setLore("§7Rarita: §f" + TextureItems.RARITY_UNCOMMON.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, baseballBat);
                sender.sendMessage("§eDal jsi hráči " + player + " Baseball Bat");
            }
            case "mecha-sword" -> {
                ItemStack mecha_fist = new ItemBuilder(Swords.MECHA_SWORD.getPureItemStack())
                        .setName("§c§lMecha-Sword").setLore("§7Rarita: §f" + TextureItems.RARITY_EPIC.getRender()).build();
                this.givePlayerItemOrDrop(onlinePlayer, mecha_fist);
                sender.sendMessage("§eDal jsi hráči " + player + " Mecha-Sword!");
            }
        }
    }

    private boolean hasFullInventory(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }

    private void givePlayerItemOrDrop(final Player player, final ItemStack itemStack) {
        if (hasFullInventory(player)) {
            if (itemStack.getItemMeta() != null && itemStack.getItemMeta().hasDisplayName()) {
                ChatInfo.INFO.send(player, "Máš plný inventář, item dropnul na zem: " + itemStack.getItemMeta().getDisplayName());
            } else {
                ChatInfo.INFO.send(player, "Máš plný inventář, item dropnul na zem!");
            }
            player.getLocation().getWorld().dropItem(player.getLocation(), itemStack);
        } else {
            player.getInventory().addItem(itemStack);
            ChatInfo.SUCCESS.send(player, "Do inventáře ti byl přidán item: " + itemStack.getItemMeta().getDisplayName());
        }
    }
}
