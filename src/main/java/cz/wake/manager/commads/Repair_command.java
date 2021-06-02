package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.utils.Repair;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("repairitem|fixitem")
@Description("Opraví tvůj item v ruce")
public class Repair_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lRepair commands:");
        help.showHelp();
    }

    @Default
    public void repair(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console se zatim nenaucila opravovat věci.");
            return;
        }

        if (!sender.hasPermission("craftmanager.repair")) {
            ChatInfo.DANGER.send((Player) sender, "Nemáš dostatečné oprávnení!");
            return;
        }

        Repair.repair((Player) sender);
    }
}
