package cz.wake.manager.commads.servers;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("survival")
@Description("Připojí tě na Survival")
public class Survival_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSurvival commands:");
        help.showHelp();
    }

    @Default
    public void connectToSurvival(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                ChatInfo.INFO.send(player, "Teleportuji na server §fSurvival [1.19]");
                Main.getInstance().sendToServer(player, "survival");
            } catch (Exception e) {
                e.printStackTrace();
                ChatInfo.DANGER.send(player, "Teleport na se nezdařil: §fSurvival [1.19]");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
