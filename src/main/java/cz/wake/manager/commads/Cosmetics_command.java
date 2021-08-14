package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.wake.manager.menu.CosmeticMainGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("cosmetics")
@Description("Hlavní menu pro všechny cosmetics")
public class Cosmetics_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lCosmetics commands:");
        help.showHelp();
    }

    @Default
    public void openCosmeticsMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            SmartInventory.builder().size(6, 9).title("Cosmetics Menu").provider(new CosmeticMainGUI()).build().open(player);
        }
    }


}
