package cz.wake.manager.managers;

import cz.wake.manager.utils.bossbar.PlayerCompass;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CompassManager {

    public static List<PlayerCompass> compassList = new ArrayList<>();

    public void addPlayer(final Player player) {
        PlayerCompass playerCompass = new PlayerCompass();
        playerCompass.send(player);
        compassList.add(playerCompass);
    }

    public void removePlayer(final Player player) {
        for (int i = 0; i < compassList.size(); i++) {
            PlayerCompass playerCompass = compassList.get(i);
            if (playerCompass.getPlayer() == player) {
                compassList.remove(playerCompass);
                playerCompass.remove();
            }
        }
    }

    public List<PlayerCompass> getCompassList() {
        return compassList;
    }
}
