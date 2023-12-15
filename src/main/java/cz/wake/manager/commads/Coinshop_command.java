package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.shop.menu.CshopMainMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("coinshop|cshop|ccs")
@Description("Otevře ti Coinshop")
public class Coinshop_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lCoinshop commands:");
        help.showHelp();
    }

    @Default
    public void openCCSMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                SmartInventory.builder().size(6, 9).title("[" + Main.getInstance().getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
