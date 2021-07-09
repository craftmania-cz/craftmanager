package cz.wake.manager.utils.tasks;

import cz.craftmania.craftcore.messages.BossBar;
import cz.wake.manager.Main;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.ChannelNotRegisteredException;

import java.util.concurrent.TimeUnit;

public class RestartTask implements Runnable {


    /*
    Spustí task, ktorý bude checkovať koľko času zostáva do reštartu.
    Ak čas uplynie tak pošle všetkých hráčov na lobby server a server uzamkne a reštartuje.
     */

    String reason = Main.restartReason;
    Long when = Main.restartTime;
    Long remaining = when - System.currentTimeMillis();
    BossBar bb = RestartManager_command.bb;

    @Override
    public void run() {
        if (when == null) return;
        remaining = when - System.currentTimeMillis();
        reason = Main.restartReason;
        if (remaining > 3600 * 1000) {
          bb.setTitle("§fRestart za §a" + TimeUnit.MILLISECONDS.toHours(remaining) + "h " + TimeUnit.MILLISECONDS.toMinutes(remaining) % 60 + "m " + TimeUnit.MILLISECONDS.toSeconds(remaining) % 60 % 60 + "s");
          bb.setColor("GREEN");
        }
        if (remaining > 60 * 1000 && remaining < 3600 * 1000) {
          bb.setTitle("§fRestart za §a" + TimeUnit.MILLISECONDS.toMinutes(remaining) + "m " + TimeUnit.MILLISECONDS.toSeconds(remaining) % 60 % 60 + "s");
          bb.setColor("GREEN");
        } else if (remaining < 60 * 1000 && remaining > 10 * 1000) {
          bb.setTitle("§fRestart za §e" + TimeUnit.MILLISECONDS.toSeconds(remaining) + "s");
          bb.setColor("YELLOW");
        } else if (remaining < 10 * 1000 && remaining > 1000) {
          bb.setTitle("§fRestart za §c" + TimeUnit.MILLISECONDS.toSeconds(remaining) + "s");
          bb.setColor("RED");
        }
        else if (remaining <= 0) {
          for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            try {
              Main.getInstance().sendToServer(p, "lobby");
            } catch (ChannelNotRegisteredException e) {
              Log.normalMessage("Nepodarilo se poslat hrace " + p.getName() + " na Lobby server.");
            }
          }
          Bukkit.shutdown();
          bb.hide();
          return;
        }

        bb.setProgress((double) Math.round(((remaining.doubleValue() / 6000) / Double.valueOf(RestartManager_command.min) / 10)* 1000d )/ 1000d);
        //System.out.print(remaining / 1000);


    }
}
