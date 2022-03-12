package cz.wake.manager.objects.pvp.events;

import cz.wake.manager.objects.pvp.PvPArena;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTeleportedToArenaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final @Getter Player player;
    private final @Getter PvPArena pvpArena;

    public PlayerTeleportedToArenaEvent(Player player, PvPArena pvpArena) {
        this.player = player;
        this.pvpArena = pvpArena;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
