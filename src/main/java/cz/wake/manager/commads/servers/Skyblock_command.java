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

@CommandAlias("skyblock")
@Description("Připojí tě na Skyblock")
public class Skyblock_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkyblock commands:");
        help.showHelp();
    }

    @Default
    public void connectToSkyblock(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                ChatInfo.INFO.send(player, "Teleportuji na server §fSkyblock");
                Main.getInstance().sendToServer(player, "skyblock");
            } catch (Exception e) {
                e.printStackTrace();
                ChatInfo.DANGER.send(player, "Teleport na se nezdařil: §fSkyblock");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
