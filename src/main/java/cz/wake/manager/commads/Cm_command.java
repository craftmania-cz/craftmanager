package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.wake.manager.Main;
import org.bukkit.command.CommandSender;

@CommandAlias("cm|craftmanager")
@Description("Reloadne config")
public class Cm_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lCm commands:");
        help.showHelp();
    }

    @Default
    public void reloadWrong(CommandSender sender) {
        if (sender.hasPermission("craftmanager.reload")) {
            sender.sendMessage("§c/cm reload");
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
        }
    }

    @Subcommand("reload")
    public void reload(CommandSender sender) {
        if (sender.hasPermission("craftmanager.reload")) {
            Main.getInstance().reloadConfig();
            Main.getInstance().getScoreboardManager().update();
            sender.sendMessage("§e§l[*] §eConfig a scoreboard uspesne reloadnut.");
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
        }
    }
}
