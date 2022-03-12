package cz.wake.manager.managers;

import cz.wake.manager.Main;
import cz.wake.manager.objects.pvp.PvPArena;
import cz.wake.manager.utils.Log;
import lombok.Getter;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PvPManager {

    private @Getter int spawnLocationDistance = 15;

    private @Getter List<PvPArena> pvpArenas = new ArrayList<>();

    public static FileConfiguration getPvPConfig() {
        return Main.getInstance().getConfigAPI().getConfig("pvp").getConfig();
    }

    public static boolean isEnabled() {
        return getPvPConfig().getBoolean("enabled");
    }

    public static void setRespawnLocation(Location location) {
        getPvPConfig().set("respawn-location", location);
        Main.getInstance().getConfigAPI().saveConfigs();
    }

    public static Location getRespawnLocation() {
        Configuration config = getPvPConfig();

        if (config.isSet("respawn-location")) {
            return config.getObject("respawn-location", Location.class);
        }

        return null;
    }

    public void load() {
        Main.getInstance().getConfigAPI().getConfig("pvp").reload();
        Configuration config = getPvPConfig();

        spawnLocationDistance = config.getInt("spawn-location-distance");

        List<PvPArena> pvpArenas = (List<PvPArena>) config.getList("pvp-arenas", new ArrayList<PvPArena>());

        int index = 0;
        for (PvPArena pvpArena : pvpArenas) {
            if (pvpArena.isValid()) {
                pvpArena.updateKeepInventory();
                this.pvpArenas.add(pvpArena);
            } else {
                Log.withPrefix("Jedna z PvP Arén je špatně nastavená! (index: " + index + ")");
                Log.withPrefix("- " + pvpArena.getId());
                Log.withPrefix("- " + pvpArena.getName());
                Log.withPrefix("- " + pvpArena.getWorld());
                Log.withPrefix("- " + pvpArena.getSpawnLocations());
            }

            index++;
        }
    }

    public void save() {
        FileConfiguration config = getPvPConfig();

        config.set("pvp-arenas", pvpArenas);

        Main.getInstance().getConfigAPI().saveConfigs();
    }

    public PvPArena getArenaByWorld(World world) {
        for (PvPArena pvpArena : pvpArenas) {
            if (pvpArena.getWorld().getName().equals(world.getName())) {
                return pvpArena;
            }
        }

        return null;
    }

    public boolean isArenaInWorld(World world) {
        return getArenaByWorld(world) != null;
    }

    public void addPvPArena(PvPArena pvpArena) {
        pvpArenas.add(pvpArena);
    }

    public PvPArena getArenaById(String id) {
        for (PvPArena pvpArena : pvpArenas) {
            if (pvpArena.getId().equals(id)) {
                return pvpArena;
            }
        }

        return null;
    }

    public boolean existsArenaById(String id) {
        return getArenaById(id) != null;
    }

    public boolean isPlayerInAnyArena(Player player) {
        return isArenaInWorld(player.getWorld());
    }
}
