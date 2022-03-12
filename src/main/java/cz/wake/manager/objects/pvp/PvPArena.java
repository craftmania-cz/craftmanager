package cz.wake.manager.objects.pvp;

import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.listener.PvPListener;
import cz.wake.manager.objects.pvp.events.PlayerTeleportedToArenaEvent;
import cz.wake.manager.utils.BukkitUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SerializableAs("PvPArena")
public class PvPArena implements ConfigurationSerializable {

    private @Getter @Setter String id = null; // id
    private @Getter @Setter String name = null; // resource pack symbol
    private @Getter boolean dropsEnabled = false;
    private @Getter @Setter boolean effectsEnabled = false;

    private @Getter @Setter World world = null;
    private @Getter List<PvPSpawnLocation> spawnLocations = new ArrayList<>();

    public PvPArena() {
    }

    public void addPvPSpawnLocation(PvPSpawnLocation pvpSpawnLocation) {
        spawnLocations.add(pvpSpawnLocation);
    }

    public boolean removePvPSpawnLocation(PvPSpawnLocation pvpSpawnLocation) {
        return spawnLocations.remove(pvpSpawnLocation);
    }

    public boolean removePvPSpawnLocation(int index) {
        PvPSpawnLocation pvpSpawnLocation = getPvPSpawnLocationByIndex(index);

        if (pvpSpawnLocation == null) {
            return false;
        }

        return removePvPSpawnLocation(pvpSpawnLocation);
    }

    public PvPSpawnLocation getPvPSpawnLocationByIndex(int index) {
        try {
            return spawnLocations.get(index);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static PvPArena deserialize(Map<String, Object> map) {
        PvPArena pvpArena = new PvPArena();

        pvpArena.id = (String) map.getOrDefault("id", null);
        pvpArena.name = (String) map.getOrDefault("name", null);
        pvpArena.spawnLocations = (ArrayList<PvPSpawnLocation>) map.getOrDefault("spawn-locations", new ArrayList<>());
        pvpArena.dropsEnabled = (boolean) map.getOrDefault("drops-enabled", false);
        pvpArena.effectsEnabled = (boolean) map.getOrDefault("effects-enabled", false);

        String worldName = (String) map.getOrDefault("world-name", null);
        if (worldName != null) {
            pvpArena.setWorld(Bukkit.getWorld(worldName));
        }

        return pvpArena;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("id", id);
        map.put("name", name);
        map.put("world-name", world.getName());
        map.put("spawn-locations", spawnLocations);
        map.put("drops-enabled", dropsEnabled);
        map.put("effects-enabled", effectsEnabled);

        return map;
    }

    public boolean isValid() {
        return id != null && name != null && world != null && spawnLocations != null;
    }

    public void updateKeepInventory() {
        if (!dropsEnabled) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
        } else {
            world.setGameRule(GameRule.KEEP_INVENTORY, false);
        }
    }

    public void setDropsEnabled(boolean dropsEnabled) {
        this.dropsEnabled = dropsEnabled;
        updateKeepInventory();
    }

    public PvPSpawnLocation getRandomSpawnLocation() {
        if (spawnLocations.size() == 0) {
            return null;
        }

        Random random = new Random();

        int retry = -1;
        while (retry < spawnLocations.size()) {
            retry++;

            PvPSpawnLocation spawnLocation = getPvPSpawnLocationByIndex(random.nextInt(spawnLocations.size()));

            if (spawnLocation == null) {
                continue;
            }

            if (!spawnLocation.isAnyPlayerAround()) {
                return spawnLocation;
            }
        }

        // Nebylo možné najít žádné random místo...
        return getPvPSpawnLocationByIndex(random.nextInt(spawnLocations.size()));
    }

    public void teleport(Player player) {
        if (!canTeleport(player)) {
            ChatInfo.DANGER.send(player, "Nebyl jsi teleportován, jelikož máš na sobě efekty!");
            return;
        }

        PvPSpawnLocation spawnLocation = getRandomSpawnLocation();

        if (spawnLocation == null) {
            ChatInfo.DANGER.send(player, "Nebylo možné najít lokaci v PvP Aréně pro teleportaci. Zkus to prosím znovu!");
            return;
        }

        if (player.teleport(spawnLocation.getLocation())) {
            BukkitUtil.callEvent(new PlayerTeleportedToArenaEvent(player, this));
            ChatInfo.SUCCESS.send(player, "Byl jsi teleportován.");
            PvPListener.enableGodMode(player);
        }
    }

    public static boolean canTeleport(Player player) {
        if (player.getActivePotionEffects().size() != 0) {
            return false;
        }

        return true;
    }
}
