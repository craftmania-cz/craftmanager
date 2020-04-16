package cz.wake.manager.perks.general;

import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Glow_command {

    public static void registerCommand() {
        CommandAPI.getInstance().register("glow", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("craftmanager.glow")) {
                    if (!player.isGlowing()) {
                        player.setGlowing(true);
                        player.sendMessage("§e§l[*] §eAktivoval jsi efekt §5Glowing!");
                    } else {
                        player.setGlowing(false);
                        player.sendMessage("§e§l[*] §eDeaktivoval jsi efekt §5Glowing!");
                    }
                } else {
                    player.sendMessage("§c§l[!] §cK pouziti teto schopnosti musis vlastnit VIP!");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
