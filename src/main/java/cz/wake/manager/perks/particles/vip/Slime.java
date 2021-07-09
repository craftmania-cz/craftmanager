package cz.wake.manager.perks.particles.vip;

import cz.craftmania.craftcore.utils.effects.ParticleEffect;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Slime {

    public static final HashMap<String, Integer> e = new HashMap<>();
    private int task;

    public void activate(Player p) {
        if (!e.containsKey(p.getName())) {
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                if (e.containsKey(p.getName()) && p.isOnline()) {
                    ParticleEffect.SLIME.display(1.0f, 1.0f, 1.0f, 0.05f, 15, p.getLocation(), Main.getInstance().getPlayers());
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(), task);
        }
    }

}
