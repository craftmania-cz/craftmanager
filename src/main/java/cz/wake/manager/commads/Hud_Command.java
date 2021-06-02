package cz.wake.manager.commads;

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

@CommandAlias("hud")
@Description("Zobrazování polohy na vrchu obrazovky")
public class Hud_Command extends BaseCommand {


    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lHUD příkazy:");
        help.showHelp();
    }


    @Default
    public void toggleHud(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Main.getInstance().getMySQL().getSettings(player, "hud_bossbar") == 1) {
                Main.getInstance().getMySQL().updateSettings(player, "hud_bossbar", 0);
                Main.getInstance().getCompassManager().removePlayer(player);
                ChatInfo.INFO.send(player, "Deaktivoval jsi zobrazování polohy na vrchu obrazovky.");
            } else {
                Main.getInstance().getMySQL().updateSettings(player, "hud_bossbar", 1);
                Main.getInstance().getCompassManager().addPlayer(player);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi si zobrazování polohy na vrchu obrazovky.");
            }
        }
    }


}
