package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Prison_command{

    public static void registerCommand() {

        CommandAPI.getInstance().register("prison", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                try {
                    player.sendMessage("§c§l[!] §cZadany server neexistuje?!");
                    //player.sendMessage("§eTeleportuji na server §fPrison");
                    //Main.getInstance().sendToServer(player, "prison");
                } catch (Exception e) {
                    e.printStackTrace();
                    //player.sendMessage("§cTeleport na server §fPrison §cse nezdaril!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
