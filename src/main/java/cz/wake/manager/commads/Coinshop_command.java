package cz.wake.manager.commads;

import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.shop.menu.CshopMainMenu;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;

public class Coinshop_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("coinshop", new String[]{"ccshop", "cs"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                SmartInventory.builder().size(6, 9).title("[" + Main.getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
