package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

@CommandAlias("skull|hlava")
@Description("Dá ti tvojí hlavu")
public class SkullCommand extends BaseCommand {

    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkull commands:");
        help.showHelp();
    }

    @Default
    public void giveSkull(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Main.getInstance().getServerType() == ServerType.HARDCORE_VANILLA) {
                ChatInfo.DANGER.send(player, "Na tomto serveru tato výhoda neplatí!");
                return;
            }
            if (player.hasPermission("craftmanager.vip.skull")) {
                if (!this._time.containsKey(player)) {
                    this._time.put(player, 600D + 0.1D);
                    giveHead(player);
                    this._cdRunnable.put(player, new BukkitRunnable() {
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
                    (this._cdRunnable.get(player)).runTaskTimer(Main.getInstance(), 2L, 2L);
                } else {
                    ChatInfo.INFO.send(player, "Tento příkaz lze použít pouze 1x za 10 minut.");
                }
            }
        }
    }

    private void giveHead(Player p) {
        try {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give %creator% minecraft:player_head{\"SkullOwner\":\"%creator%\"}".replaceAll("%creator%", p.getName()));
        } catch (Exception e) {
            ChatInfo.DANGER.send(p, "Chyba v API Mojangu! Zkus to znova zachvilku! :)");
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
