package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("vote|hlasovani|hlasovat")
@Description("Vypíše ti vote odkazy do chatu")
public class Vote_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVote commands:");
        help.showHelp();
    }

    @Default
    public void printVoteLinks(CommandSender Sender) {
        if (Sender instanceof Player) {
            sendVoteLink((Player) Sender);
        }
    }

    public static void sendVoteLink(final Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        player.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
        player.sendMessage("");
        player.sendMessage("§7K hlasovani klikni na tento odkaz:");
        player.sendMessage("§c1. https://www.minecraft-list.cz/server/craftmania/vote?name=" + player.getName());
        player.sendMessage("§c2. http://craftlist.org/craftmania?nickname=" + player.getName());
        player.sendMessage("§c3. https://minecraftservery.eu/server/453/vote/" + player.getName());
        player.sendMessage("");
        player.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
    }
}
