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
import cz.wake.manager.utils.ServerType;
import cz.wake.manager.utils.inventories.VIPMenu_inv;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("vip|vipmenu")
@Description("Otevře ti menu pro koupi VIP")
public class VIP_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVIP commands:");
        help.showHelp();
    }

    @Default
    public void openMenu(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Main.getServerType() == ServerType.SURVIVAL
                    || Main.getServerType() == ServerType.SKYBLOCK
                    || Main.getServerType() == ServerType.CREATIVE
                    || Main.getServerType() == ServerType.PRISON
                    || Main.getServerType() == ServerType.VANILLA) {
                SmartInventory.builder().size(5, 9).title("[VIP] Menu").provider(new VIPMenu_inv()).build().open(player);
            } else {
                ChatInfo.INFO.send(player, "Na tomto serveru nelze zobrazit VIP, jelikož zde zatím žádné není");
            }
        } else {
            sender.sendMessage("Z konzole VIP nefunguje!");
        }
    }
}
