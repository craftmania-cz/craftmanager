package cz.wake.manager.commads.servers;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("vanilla")
@Description("Teleportuje tě na Vanilla: Lands")
public class Vanilla_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVanilla: Lands commands:");
        help.showHelp();
    }

    @Default
    public void connectToVanilla(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                ChatInfo.INFO.send(player, "Teleportuji na server §fVanilla: Lands");
                Main.getInstance().sendToServer(player, "vanilla");
            } catch (Exception e) {
                e.printStackTrace();
                ChatInfo.DANGER.send(player, "Teleport na se nezdařil: §fVanilla: Lands");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
