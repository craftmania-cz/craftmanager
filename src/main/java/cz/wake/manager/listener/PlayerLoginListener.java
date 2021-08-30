package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void dynamicSlotsLoginCheck(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        int totalSlots = Main.getInstance().getConfig().getInt("totalSlots");
        int reservedSlots = Main.getInstance().getConfig().getInt("reservedSlots");
        int playerCount = Bukkit.getOnlinePlayers().size();

        if (Main.getInstance().getServerType() == ServerType.ANARCHY) {
            if (player.hasPermission("craftmania.at")) {
                return;
            }
        }

        if (playerCount < totalSlots || player.hasPermission("craftmania.at")) { //Jestli momentálních hráčů je méně než slotů povolených, hráče to připojí
            return;
        } else {
            if (player.hasPermission("craftmanager.vip.login-bypass")) {
                if (playerCount < (totalSlots + reservedSlots)) { //Hráč má práva na login-bypass a server ještě není úplně plný
                    return;
                } else { //Hráč sice má práva na login-bypass, ale server presáhl svou maximální kapacitu
                    event.disallow(PlayerLoginEvent.Result.KICK_FULL, "Server je plný!\nServer dosáhl své maximální kapacity pro hráče, VIP i AT! Budeš si muset počkat, než se uvolní místo pro tebe.");
                }
            } else { //Hráč nemá práva na login-bypass a server je plný -> Kick message pro hráče
                event.disallow(PlayerLoginEvent.Result.KICK_FULL, "Server je plný!\nPokud se chceš připojit na plný server, musíš si zakoupit VIP!");
            }
        }
    }
}
