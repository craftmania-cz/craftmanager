package cz.wake.manager.commads;

import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Vote_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("vote", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                sendVoteLink(p);
            }
        });
    }

    public static void sendVoteLink(final Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
        p.sendMessage("");
        p.sendMessage("§7K hlasovani klikni na tento odkaz:");
        p.sendMessage("§c1. https://czech-craft.eu/server/craftmania/vote/?user=" + p.getName());
        p.sendMessage("§c2. http://craftlist.org/craftmania?nickname=" + p.getName());
        p.sendMessage("");
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
    }
}
