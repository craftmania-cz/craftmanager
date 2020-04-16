package cz.wake.manager.commads;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Menu_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("menu", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Main.getInstance().getMainGUI().openMainMenu((Player) sender);
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
