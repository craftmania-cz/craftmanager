package cz.wake.manager.commads;

import cz.wake.manager.managers.MenuManager;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;


public class Navody_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("navody", null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MenuManager.openNavody(player);
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
