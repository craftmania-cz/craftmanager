package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
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
                ItemStack ice_rose_sword = new ItemBuilder(Material.NETHERITE_SWORD).setCustomModelData(100004)
                        .setName("§b§lIce Rose Sword").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢").build();
                onlinePlayer.getInventory().addItem(ice_rose_sword);
                ItemStack ice_rose_pickaxe = new ItemBuilder(Material.NETHERITE_PICKAXE).setCustomModelData(100001)
                        .setName("§b§lIce Rose Pickaxe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢").build();
                onlinePlayer.getInventory().addItem(ice_rose_pickaxe);
                ItemStack ice_rose_axe = new ItemBuilder(Material.NETHERITE_AXE).setCustomModelData(100001)
                        .setName("§b§lIce Rose Axe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢").build();
                onlinePlayer.getInventory().addItem(ice_rose_axe);
                ItemStack ice_rose_hoe = new ItemBuilder(Material.NETHERITE_HOE).setCustomModelData(100001)
                        .setName("§b§lIce Rose Hoe").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢").build();
                onlinePlayer.getInventory().addItem(ice_rose_hoe);
                ItemStack ice_rose_shovel = new ItemBuilder(Material.NETHERITE_SHOVEL).setCustomModelData(100001)
                        .setName("§b§lIce Rose Shovel").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢").build();
                onlinePlayer.getInventory().addItem(ice_rose_shovel);
                ItemStack ice_rose_crown = new ItemBuilder(Material.SUGAR).setCustomModelData(100001)
                        .setName("§b§lIce Rose Crown").setLore("§7Zakoupil: §f" + onlinePlayer.getName(), "§7Rarita: §f⼢", "§8Klikni pravým k nasazení.").build();
                onlinePlayer.getInventory().addItem(ice_rose_crown);
                sender.sendMessage("§eDal jsi hráči " + player + " Ice Rose Set!");
                break;
        }
    }
}
