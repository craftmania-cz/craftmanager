package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.menu.DisguiseGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("morph|morphs")
@Description("Převtělení do monstra nebo zvířátka.. BUUU")
public class MorphCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lMorph příkazy:");
        help.showHelp();
    }

    @Default
    public void morphMenu(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return;
        }
        if (Main.isLibsDisguiseEnabled()) {
            SmartInventory.builder().provider(new DisguiseGUI()).title("Morphy").size(6, 9).build().open((Player) sender);
        } else {
            ChatInfo.DANGER.send((Player) sender, "Morphy jsou na tomto serveru deaktivovány.");
        }
    }
}
