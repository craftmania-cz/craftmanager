package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.crafteconomy.achievements.Rarity;
import cz.craftmania.craftpack.api.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    @CommandCompletion("[player] [type]")
    @Syntax("[player] [type]")
    public void onCommand(CommandSender sender, String player, String type) {
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
        switch (type) {
            case "ice_rose_set":
                ItemStack ice_rose_sword = new ItemBuilder(Swords.ICE_ROSE_SWORD.getPureItemStack())
                        .setName("§b§lIce Rose Sword").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                onlinePlayer.getInventory().addItem(ice_rose_sword);
                ItemStack ice_rose_pickaxe = new ItemBuilder(Pickaxes.ICE_ROSE_PICKAXE.getPureItemStack())
                        .setName("§b§lIce Rose Pickaxe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                onlinePlayer.getInventory().addItem(ice_rose_pickaxe);
                ItemStack ice_rose_axe = new ItemBuilder(Axes.ICE_ROSE_AXE.getPureItemStack())
                        .setName("§b§lIce Rose Axe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                onlinePlayer.getInventory().addItem(ice_rose_axe);
                ItemStack ice_rose_hoe = new ItemBuilder(Hoes.ICE_ROSE_HOE.getPureItemStack())
                        .setName("§b§lIce Rose Hoe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                onlinePlayer.getInventory().addItem(ice_rose_hoe);
                ItemStack ice_rose_shovel = new ItemBuilder(Shovels.ICE_ROSE_SHOVEL.getPureItemStack())
                        .setName("§b§lIce Rose Shovel").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender()).build();
                onlinePlayer.getInventory().addItem(ice_rose_shovel);
                ItemStack ice_rose_crown = new ItemBuilder(Hats.ICE_ROSE_CROWN.getPureItemStack())
                        .setName("§b§lIce Rose Crown").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "§8Klikni pravým k nasazení.").build();
                onlinePlayer.getInventory().addItem(ice_rose_crown);
                sender.sendMessage("§eDal jsi hráči " + player + " Ice Rose Set!");
                break;
            case "pomlazka":
                ItemStack pomlazka = new ItemBuilder(HandItems.EASTER_STICK.getPureItemStack())
                        .setName("§9§lPomlázka").setLore("§7Rarita: §f" + TextureItems.RARITY_LIMITED.getRender()).build();
                onlinePlayer.getInventory().addItem(pomlazka);
                break;
            case "suitcase":
                ItemStack suitcase = new ItemBuilder(HandItems.KUFR.getPureItemStack())
                        .setName("§e§lKufr").setLore("§7Item naprosto k ničemu", "§7proč ale prostě neodjet", "§7na dovolenou?!", "§7Rarita: §f" + TextureItems.RARITY_UNCOMMON.getRender()).build();
                onlinePlayer.getInventory().addItem(suitcase);
                break;
            case "katana":
                ItemStack katana = new ItemBuilder(Swords.KATANA.getPureItemStack())
                        .setName("§e§lKatana").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                onlinePlayer.getInventory().addItem(katana);
                break;
            case "enderite-sword":
                ItemStack enderite = new ItemBuilder(Swords.ENDERITE.getPureItemStack())
                        .setName("§9§lEnderite").setLore("§7Rarita: §f" + TextureItems.RARITY_RARE.getRender()).build();
                onlinePlayer.getInventory().addItem(enderite);
                break;

        }
    }
}
