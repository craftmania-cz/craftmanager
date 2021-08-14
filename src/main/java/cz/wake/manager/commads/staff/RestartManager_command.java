package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftcore.messages.BossBar;
import cz.wake.manager.Main;
import cz.wake.manager.utils.tasks.RestartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@CommandAlias("restartmanager|rm")
@Description("Umožní restart serveru")
public class RestartManager_command extends BaseCommand {

    public static BossBar bb = new BossBar("msg", "GREEN", "SEGMENTED_20", 1.0);
    public static List<BukkitTask> runnables = new ArrayList<>();
    public static int min;
    String combinedArgs;

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lRestartmanager commands:");
        help.showHelp();
    }

    @Default
    public void showCurrentRestart(CommandSender sender) {
        if (sender.hasPermission("craftmanager.restartmanager")) {
            if (Main.restartTime != null) {
                Long remaining = Main.restartTime - System.currentTimeMillis();
                sender.sendMessage("§e§l[*] §eAktualne je naplanovany §c§lRESTART §e(za " + TimeUnit.MILLISECONDS.toMinutes(remaining) + "m " + TimeUnit.MILLISECONDS.toSeconds(remaining) % 60 % 60 + "s)");
            }
            sendUsage(sender);
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
        }
    }

    @Subcommand("stop")
    public void stopRestart(CommandSender sender){
        if (sender.hasPermission("craftmanager.restartmanager")) {
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
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
        }
    }

    @Subcommand("start")
    @CommandCompletion("[minuty] @range:0-80")
    @Syntax("[minuty] @range:0-80")
    public void startRestart(CommandSender sender, int min, String text) {
        if (sender.hasPermission("craftmanager.restartmanager")) {
            if (Main.restartTime != null) {
                sender.sendMessage("§c§l[!] §cJiz probiha nejaky restart, musis ho nejdrive zrusit!");
                return;
            }
            try {
                Main.restartReason = text;
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
                    return;
                } else {
                    Bukkit.getServer().broadcastMessage("§7§m---------§7[§c§l Restart serveru §7]§m---------\n");
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("   §7Server bude restartovan za §f" + TimeUnit.MINUTES.toMinutes(min) + "m§7.");
                    Bukkit.getServer().broadcastMessage("   §7Duvod restartu: §f" + Main.restartReason);
                    Bukkit.getServer().broadcastMessage("§f");
                    Bukkit.getServer().broadcastMessage("§7§m-------------------------------------");
                    return;
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§c§l[!] §cNespravny format cisel!");
                return;
            }
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
        }
    }

    private void sendUsage(CommandSender p) {
        p.sendMessage("§e§l[*] §ePouziti: §f/rm start/stop <minuty> <duvod>");
    }
}
