package cz.wake.manager.commads;

import cz.wake.manager.utils.Repair;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Repair_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("repair", new String[]{"cmrepair"}, null, (sender, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Console se zatim nenaucila opravovat věci.");
                return;
            }

            if (!sender.hasPermission("craftmanager.repair")) {
                sender.sendMessage("§c§l[!] §cNemas dostatecne opravneni!");
                return;
            }

            Repair.repair((Player) sender);
        });
    }
}
