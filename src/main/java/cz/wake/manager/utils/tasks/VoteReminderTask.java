package cz.wake.manager.utils.tasks;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static cz.wake.manager.utils.Request.readJsonFromUrl;

public class VoteReminderTask implements Runnable {

    public static HashMap<Player, Long> cooldown = new HashMap<>();

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        for(Player player : Bukkit.getOnlinePlayers()){
            try {
                JSONObject mainObject = readJsonFromUrl("https://api.craftmania.cz/player/" + player.getName());
                JSONObject dataObject = mainObject.getJSONObject("data");
                JSONObject voteObject = dataObject.getJSONObject("votes");
                if (voteObject.getLong("last_vote") < (currentTime - 7200000)) { // Pokud je to dýl jak 2h
                    if (!cooldown.containsKey(player)) {
                        cooldown.put(player, voteObject.getLong("last_vote"));
                    }
                    if (cooldown.get(player) < (currentTime - 1800000)) { // 30m cooldown
                        if (voteObject.getInt("month") == 0) {
                            player.sendMessage("");
                            player.sendMessage("§aTento měsíc jsi ještě nehlasoval(a)! Podpoř nás hlasem:");
                            player.sendMessage("§7pomocí příkazu: §b/vote");
                            player.sendMessage("");
                        } else {
                            player.sendMessage("");
                            player.sendMessage("§eTento měsíc máš §c" + voteObject.getInt("month") + " §ehlasů! §aNyní můžeš hlasovat znova! §b/vote");
                            player.sendMessage("");
                        }
                        cooldown.remove(player);
                        cooldown.put(player, System.currentTimeMillis());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
