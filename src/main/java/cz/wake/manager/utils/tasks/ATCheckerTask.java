package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ATCheckerTask implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().at_list.contains(p)) {
                Main.getInstance().getMySQL().updateAtPlayerTime(p);
            }
        }
    }
}
