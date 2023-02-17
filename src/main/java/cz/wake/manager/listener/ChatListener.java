package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatListener implements Listener {

    private HashMap<Player, Double> _time = new HashMap();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap();
    private HashMap<Player, Long> cd = new HashMap<>();
    private List<Player> _toSend = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player writingPlayer = e.getPlayer();
        String msg = e.getMessage();

        if (Main.getInstance().at_afk.containsKey(writingPlayer)) {
            if (Main.getInstance().at_afk.get(writingPlayer) != 0) {
                Main.getInstance().at_afk.put(e.getPlayer(), 0);
            }
        }
        if (Main.getInstance().at_list.contains(writingPlayer)) {
            if (!this._time.containsKey(writingPlayer)) {
                this._time.put(writingPlayer, 60D + 0.1D);
                Main.getInstance().getMySQL().updateAtLastActive(writingPlayer, System.currentTimeMillis());
                Main.getInstance().getMySQL().updateAtPoints(writingPlayer);
                this._cdRunnable.put(writingPlayer, new BukkitRunnable() {
                    @Override
                    public void run() {
                        ChatListener.this._time.put(writingPlayer, (ChatListener.this._time.get(writingPlayer)) - 0.1D);
                        if ((ChatListener.this._time.get(writingPlayer)) < 0.01D) {
                            ChatListener.this._time.remove(writingPlayer);
                            ChatListener.this._cdRunnable.remove(writingPlayer);
                            cancel();
                        }
                    }
                });
                (this._cdRunnable.get(writingPlayer)).runTaskTimer(Main.getInstance(), 2L, 2L);
            }
        }
    }
}
