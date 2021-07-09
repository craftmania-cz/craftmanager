package cz.wake.manager.perks.particles.capes;

import cz.craftmania.craftcore.utils.effects.UtilParticles;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class SpookyCape {

    public static final HashMap<String, Integer> turnajCloaks = new HashMap();
    boolean x = true;
    boolean y = false;
    int particles;

    @SuppressWarnings("deprecation")
    public void activate(Player p) {
        boolean[][] type = shapeTurnaj;
        int borderRed = 255;
        int borderGreen = 255;
        int borderBlue = 255;
        int textRed = 0;
        int textGreen = 0;
        int textBlue = 0;

        if (!turnajCloaks.containsKey(p.getName())) {
            particles = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                if (turnajCloaks.containsKey(p.getName())) {
                    drawParticles(p.getLocation(), p, type, borderBlue, borderGreen, borderRed,
                            textBlue, textGreen, textRed);
                }
            }, 0L, 2L).getTaskId();
            turnajCloaks.put(p.getName(), particles);
            p.closeInventory();
        }

    }

    private final boolean[][] shapeTurnaj = {
            {y, y, y, y, y,},
            {y, y, y, y, y,},
            {y, x, y, x, y,},
            {y, x, y, x, y,},
            {y, y, y, y, y,},
            {x, y, y, y, x,},
            {x, x, x, x, x,},
            {y, y, y, y, y,},
    };

    private void drawParticles(Location location, Player p, boolean[][] typeShape, int red, int green, int blue,
                               int textRed, int textGreen, int textBlue) {
        double space = 0.2;
        double defX = location.getX() - (space * typeShape[0].length / 2) + space / 2;
        double x = defX;
        double defY = location.getY() + 1.5;
        double y = defY;
        double angle = -((location.getYaw() + 180) / 60);
        angle += (location.getYaw() < -180 ? 3.25 : 2.985);
        for (int i = 0; i < typeShape.length; i++) {
            for (int j = 0; j < typeShape[i].length; j++) {
                if (typeShape[i][j]) {
                    Location target = location.clone();
                    target.setX(x);
                    target.setY(y);

                    Vector v = target.toVector().subtract(location.toVector());
                    Vector v2 = getBackVector(location);
                    v = rotateAroundAxisY(v, angle);
                    double iT = ((double) i) / 10;
                    v2.setY(0).multiply(-0.2 - iT);

                    Location loc = location.clone();

                    loc.add(v);
                    loc.add(v2);
                    if (p.isSprinting())
                        loc.setY(defY);

                    for (int k = 0; k < 3; k++)
                        UtilParticles.display(red, green, blue, loc);
                    loc.subtract(v2);
                    loc.subtract(v);
                } else {
                    Location target = location.clone();
                    target.setX(x);
                    target.setY(y);

                    Vector v = target.toVector().subtract(location.toVector());
                    Vector v2 = getBackVector(location);
                    v = rotateAroundAxisY(v, angle);
                    double iT = ((double) i) / 10;
                    v2.setY(0).multiply(-0.2 - iT);

                    Location loc = location.clone();

                    loc.add(v);
                    loc.add(v2);
                    if (p.isSprinting())
                        loc.setY(defY);

                    for (int k = 0; k < 3; k++)
                        UtilParticles.display(textRed, textGreen, textBlue, loc);
                    loc.subtract(v2);
                    loc.subtract(v);
                }
                x += space;
            }
            y -= space;
            x = defX;
        }
    }

    private static Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    private static Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 1))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 1))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }
}
