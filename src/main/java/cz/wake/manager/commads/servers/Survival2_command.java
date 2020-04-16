package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Survival2_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("survival2", new String[]{"surv2"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                try {
                    player.sendMessage("§eTeleportuji na server §fSurvival 1.12");
                    Main.getInstance().sendToServer(player, "survival");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fSurvival 1.12 §cse nezdaril!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}