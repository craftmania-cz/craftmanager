package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Creative_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("creative", new String[]{"crea"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                try {
                    player.sendMessage("§e§l[*] §eTeleportuji na server §fCreative");
                    Main.getInstance().sendToServer(player, "creative");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fCreative §cse nezdaril!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
