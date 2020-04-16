package cz.wake.manager.perks.general;

import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.menu.ParticlesMainGUI;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Particles_command{

    public static void registerCommand() {
        CommandAPI.getInstance().register("particles", new String[]{"part"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                SmartInventory.builder().provider(new ParticlesMainGUI()).title("Particles").size(5, 9).build().open(player);
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
