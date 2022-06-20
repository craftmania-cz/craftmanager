package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

@CommandAlias("skull|hlava")
@Description("Dá ti tvojí hlavu")
public class SkullCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkull commands:");
        help.showHelp();
    }

    @Default
    public void giveSkull(CommandSender sender) {
        if (sender instanceof Player player) {
            if (player.hasPermission("craftmanager.vip.skull")) {
                if (!player.hasPermission("craftmanager.cooldown.skull-command")) {
                    giveHead(player);
                } else {
                    ChatInfo.INFO.send(player, "Tento příkaz lze použít pouze 1x za 10 minut.");
                }
            }
        }
    }

    private void giveHead(Player p) {
        try {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give %creator% minecraft:player_head{\"SkullOwner\":\"%creator%\"}".replaceAll("%creator%", p.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user %creator% permission settemp craftmanager.cooldown.skull-command true 10m".replaceAll("%creator%", p.getName()));
        } catch (Exception e) {
            ChatInfo.DANGER.send(p, "Chyba v API Mojangu! Zkus to znova zachvilku! :)");
        }
    }

    //TODO: Pridat do CraftCore
    public static String addUUIDDashes(String idNoDashes) {
        StringBuffer idBuff = new StringBuffer(idNoDashes);
        idBuff.insert(20, '-');
        idBuff.insert(16, '-');
        idBuff.insert(12, '-');
        idBuff.insert(8, '-');
        return idBuff.toString();
    }
}
