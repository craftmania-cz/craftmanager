package cz.wake.manager.objects.pvp;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

@SerializableAs("PvPSpawnLocation")
public class PvPSpawnLocation implements ConfigurationSerializable {

    private @Getter @Setter Location location;

    public PvPSpawnLocation(Location location) {
        this.location = location;
    }

    public PvPSpawnLocation() {
        this(null);
    }

    public static PvPSpawnLocation deserialize(Map<String, Object> map) {
        PvPSpawnLocation pvpSpawnLocation = new PvPSpawnLocation();

        if (map.containsKey("location")) {
            pvpSpawnLocation.location = (Location) map.get("location");
        } else {
            Log.withPrefix("Při načítání nějakého PvPSpawnLocation nebylo možné načíst lokaci: Lokace neexistuje!");
        }

        return pvpSpawnLocation;
    }

    public boolean isAnyPlayerAround() {
        for (Player player : location.getWorld().getPlayers()) {
            if (location.distance(player.getLocation()) < Main.getInstance().getPvpManager().getSpawnLocationDistance()) {
                return true;
            }
        }

        return false;
    }

    public boolean isValid() {
        return location != null;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("location", location);

        return map;
    }
}
