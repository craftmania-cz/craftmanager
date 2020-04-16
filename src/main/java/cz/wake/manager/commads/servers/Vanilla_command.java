package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Vanilla_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("vanilla", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                try {
                    player.sendMessage("§eTeleportuji na server §fVanilla");
                    Main.getInstance().sendToServer(player, "vanilla");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fVanilla §cse nezdaril!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
