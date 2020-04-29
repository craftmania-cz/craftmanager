package cz.wake.manager.commads.staff;

import cz.craftmania.craftcore.spigot.messages.BossBar;
import cz.wake.manager.Main;
import cz.wake.manager.utils.tasks.RestartTask;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.arguments.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RestartManager_command {

    public static BossBar bb = new BossBar("msg", "GREEN", "SEGMENTED_20", 1.0);
    public static List<BukkitTask> runnables = new ArrayList<>();
    public static int min;
    String combinedArgs;

    public static void registerCommand() {

        // Default: /restartmanager
        CommandAPI.getInstance().register("restartmanager", new String[]{"rm"}, null, (sender, args) -> {
            if (Main.restartTime != null) {
                Long remaining = Main.restartTime - System.currentTimeMillis();
                sender.sendMessage("§e§l[*] §eAktualne je naplanovany §c§lRESTART §e(za " + TimeUnit.MILLISECONDS.toMinutes(remaining) + "m " + TimeUnit.MILLISECONDS.toSeconds(remaining) % 60 % 60 + "s)");
            }
            sender.sendMessage("§e§l[*] §ePouziti: §f/rm start/stop <minuty> <duvod>");
        });

        // Defualt: /restartmanager start <minuty> <důvod>
        LinkedHashMap<String, Argument> rmArgs = new LinkedHashMap<>();
        rmArgs.put("start", new LiteralArgument("start"));
        rmArgs.put("minuty", new IntegerArgument());
        rmArgs.put("duvod", new GreedyStringArgument());

        CommandAPI.getInstance().register("restartmanager", new String[]{"rm"}, rmArgs, (sender, args) -> {
            if (!sender.hasPermission("craftmanager.restartmanager")) {
                sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
                return;
            }

            if (Main.restartTime != null) {
                sender.sendMessage("§c§l[!] §cJiz probiha nejaky restart, musis ho nejdrive zrusit!");
                return;
            }
            try {
                min = (int) args[0];
                Main.restartReason = (String) args[1];
                Main.restartTime = System.currentTimeMillis() + (min * 1000 * 60);
                runnables.add(Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new RestartTask(), 0, 20));

                for (Player pl : Bukkit.getOnlinePlayers()) {
                    bb.addPlayer(pl);
                }

                bb.setVisible(true);

                if (min > 60) {
                    Bukkit.getServer().broadcastMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("   §7Server bude restartovan za §f" + TimeUnit.MINUTES.toHours(min) + "h " + TimeUnit.MINUTES.toMinutes(min) % 60 + "m§7.");
                    Bukkit.getServer().broadcastMessage("   §7Duvod restartu: §f" + Main.restartReason);
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("§7§m-------------------------------------");
                } else {
                    Bukkit.getServer().broadcastMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("   §7Server bude restartovan za §f" + TimeUnit.MINUTES.toMinutes(min) + "m§7.");
                    Bukkit.getServer().broadcastMessage("   §7Duvod restartu: §f" + Main.restartReason);
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("§7§m-------------------------------------");
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§c§l[!] §cNespravny format cisel!");
            }
        });

        rmArgs.clear();
        rmArgs.put("stop", new LiteralArgument("stop"));
        CommandAPI.getInstance().register("restartmanager", new String[]{"rm"}, rmArgs, (sender, args) -> {
            if (!sender.hasPermission("craftmanager.restartmanager")) {
                sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
                return;
            }

            if (Main.restartTime == null) {
                sender.sendMessage("§c§l[!] §cMomentalne neni naplanovan zadny restart.");
                return;
            }
            Main.restartTime = null;
            Main.restartReason = null;
            sender.sendMessage("§e§l[*] §cNaplanovany restart byl uspesne zrusen.");
            for (BukkitTask task : runnables) {
                task.cancel();
            }
            bb.hide();
        });
    }

    private void sendUsage(CommandSender p) {
        p.sendMessage("§e§l[*] §ePouziti: §f/rm start/stop <minuty> <duvod>");
    }
}
