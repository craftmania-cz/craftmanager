package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Skyblock_command{

    public static void registerCommand() {

        CommandAPI.getInstance().register("skyblock", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                try {
                    player.sendMessage("§eTeleportuji na server §fSkyblock");
                    Main.getInstance().sendToServer(player, "skyblock");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fSkyblock §cse nezdaril!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
