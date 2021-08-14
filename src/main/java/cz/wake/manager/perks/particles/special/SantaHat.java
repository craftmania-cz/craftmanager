package cz.wake.manager.perks.particles.special;

import cz.craftmania.craftcore.utils.effects.UtilParticles;
import cz.wake.manager.Main;
import cz.wake.manager.utils.UtilMath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class SantaHat implements Listener {

    public static final HashMap<String, Integer> sh = new HashMap<>();
    public static int task16;
    public int particles = 12;

    public void activateHat(Player p) {
        if (!sh.containsKey(p.getName())) {
            task16 = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                if (sh.containsKey(p.getName())) {
                    Location location = p.getEyeLocation().add(0, 0.3, 0);
                    float radius = 0.25f;
                    drawCircle(radius + 0.1f, -0.05f, location, false);
                    for (int i = 0; i < 5; i++) {
                        double x = UtilMath.randomDouble(-0.05, 0.05);
                        double z = UtilMath.randomDouble(-0.05, 0.05);
                        location.add(x, 0.46f, z);
                        UtilParticles.display(255, 255, 255, location);
                        location.subtract(x, 0.46f, z);
                    }
                    for (float f = 0; f <= 0.4f; f += 0.1f) {
                        if (radius >= 0) {
                            drawCircle(radius, f, location, true);
                            radius -= 0.09f;
                        }
                    }
                }

            }, 0L, 2L).getTaskId();
            sh.put(p.getName(), task16);
        }
    }

    private void drawCircle(float radius, float height, Location location, boolean red) {
        for (int i = 0; i < particles; i++) {
            double inc = (2 * Math.PI) / particles;
            float angle = (float) (i * inc);
            float x = UtilMath.cos(angle) * radius;
            float z = UtilMath.sin(angle) * radius;
            location.add(x, height, z);
            UtilParticles.display(red ? 0 : 255, red ? 0 : 255, 255, location); // Popis obráceně?
            location.subtract(x, height, z);
        }
    }


}
