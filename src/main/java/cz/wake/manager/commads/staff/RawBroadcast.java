package cz.wake.manager.commads.staff;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class RawBroadcast {

    public static void registerCommand() {

        //Default: /rawbroadcast
        CommandAPI.getInstance().register("rawbroadcast", CommandPermission.fromString("craftmanager.rawbroadcast"), new String[]{"rb"}, null, (sender, args) -> {
            sender.sendMessage("§c§l[!] §cSpatne pouziti prikazu! Zkus /rawbroadcast [text]");
        });

        //Default: /rawbroadcast [text]
        LinkedHashMap<String, Argument> rbArgs = new LinkedHashMap<>();
        rbArgs.put("text", new GreedyStringArgument());
        CommandAPI.getInstance().register("rawbroadcast", CommandPermission.fromString("craftmanager.rawbroadcast"), new String[]{"rb"}, rbArgs, (sender, args) -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("");
                p.sendMessage((String)args[0]);
                p.sendMessage("");
            }
            sender.sendMessage("§e§l[*] §eText byl odeslan vsem online hracum!");
        });
    }
}
