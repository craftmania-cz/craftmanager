package cz.wake.manager.commads;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.LiteralArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.LinkedHashMap;

public class Cm_command implements CommandExecutor {

    public static void registerCommand() {

        CommandAPI.getInstance().register("cm", CommandPermission.fromString("craftmanager.reload"), null, (sender, args) -> {
            sender.sendMessage("§c§l[!] §cSpatne pouziti prikazu! Zkus /cm reload");
        });
        LinkedHashMap<String, Argument> cmArgs = new LinkedHashMap<>();
        cmArgs.put("akce", new LiteralArgument("reload"));
        CommandAPI.getInstance().register("cm", CommandPermission.fromString("craftmanager.reload"), cmArgs, (sender, args) -> {
            Main.getInstance().reloadConfig();
            sender.sendMessage("§e§l[*] §eConfig uspesne reloadnut.");
        });
    }




    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("craftmanager.reload")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("reload")) {
                    Main.getInstance().reloadConfig();
                    sender.sendMessage("§e§l[*] §eConfig uspesne reloadnut.");
                    return true;
                }
                else {
                    sender.sendMessage("§c/cm reload");
                    return true;
                }
            } else {
                sender.sendMessage("§c/cm reload");
                return true;
            }
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
            return true;
        }
    }
}
