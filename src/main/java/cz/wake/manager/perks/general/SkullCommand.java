package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import cz.wake.manager.listener.ChatListener;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class SkullCommand {

    private static HashMap<Player, Double> _time = new HashMap<>();
    private static HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    public static void registerCommand() {

        CommandAPI.getInstance().register("skull", new String[]{"hlava"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("craftmanager.vip.skull")) {
                    if (!_time.containsKey(player)) {
                        _time.put(player, 600D + 0.1D);
                        giveHead(player);
                        _cdRunnable.put(player, new BukkitRunnable() {
                            @Override
                            public void run() {
                                _time.put(player, (_time.get(player)) - 0.1D);
                                if ((_time.get(player)) < 0.01D) {
                                    _time.remove(player);
                                    _cdRunnable.remove(player);
                                    cancel();
                                }
                            }
                        });
                        (_cdRunnable.get(player)).runTaskTimer(Main.getInstance(), 2L, 2L);
                    } else {
                        player.sendMessage("§c§l[!] §cTento prikaz muzes provadet pouze kazdych 10 minut!");
                    }
                }
            }
        });
    }

    private static void giveHead(Player p) {
        try {
            //String command = "minecraft:give %creator% skull 1 3 {SkullOwner:\"%name%\",display:{Name:\"§b§l%name%\",Lore:[\"§7Vygenerovano pomoci §e/skull\",\"§8Vytvoril: %creator%\"]}}"
            //        .replaceAll("%creator%", p.getName()).replaceAll("%name%", p.getName());
            // 1.14+
            //String command = "give %creator% minecraft:player_head{SkullOwner:\"%creator%\",display:{Lore:[\"{\"text\":\"Vygenerováno pomocí \",\"color\":\"blue\",\"extra\":[{\"text\":\"/skull\",\"color\":\"green\"}]}\"]}}"
            //        .replaceAll("%creator%", p.getName()).replaceAll("%name%", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give %creator% minecraft:player_head{\"SkullOwner\":\"%creator%\"}".replaceAll("%creator%", p.getName()));
        } catch (Exception e) {
            p.sendMessage("§c§l[!] §cChyba v API Mojangu! Zkus to znova zachvilku! :)");
        }
    }

    //TODO: Pridat do CraftCore
    public static String addUUIDDashes(String idNoDashes) {
        StringBuffer idBuff = new StringBuffer(idNoDashes);
        idBuff.insert(20, '-');
        idBuff.insert(16, '-');
        idBuff.insert(12, '-');
        idBuff.insert(8, '-');
        return idBuff.toString();
    }
}
