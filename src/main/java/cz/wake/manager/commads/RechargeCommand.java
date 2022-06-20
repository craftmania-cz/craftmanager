package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.wake.manager.menu.RechargeGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("recharge")
@Description("Doplnění paliva do fly")
public class RechargeCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lRecharge commands:");
        help.showHelp();
    }

    @Default
    public void openMenu(CommandSender Sender) {
        if (Sender instanceof Player player) {
            SmartInventory.builder().provider(new RechargeGUI())
                    .title(":offset_-18::recharge_menu:").size(5, 9).build().open(player);
        }
    }


}
