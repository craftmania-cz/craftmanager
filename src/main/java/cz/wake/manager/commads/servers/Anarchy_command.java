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

@CommandAlias("anarchy")
@Description("Připojí tě na Vanilla: Anarchy")
public class Anarchy_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVanilla: Anarchy commands:");
        help.showHelp();
    }

    @Default
    public void connectToCreative(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                ChatInfo.INFO.send(player, "Teleportuji na server §fVanilla: Anarchy");
                Main.getInstance().sendToServer(player, "anarchy");
            } catch (Exception e) {
                e.printStackTrace();
                ChatInfo.DANGER.send(player, "Teleport na se nezdařil: §fVanilla: Anarchy");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
