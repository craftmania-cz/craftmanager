package cz.wake.manager.objects.pvp.events;

import cz.wake.manager.objects.pvp.PvPArena;
import cz.wake.manager.objects.pvp.PvPStatistics;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerDeathInArenaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final @Getter Player player;
    private final @Getter Player killer;
    private final @Getter PvPArena pvpArena;
    private final @Getter PvPStatistics pvpStatistics;

    public PlayerDeathInArenaEvent(Player player, Player killer, PvPArena pvpArena, PvPStatistics pvpStatistics) { // TODO: Čas a počet killů
        this.player = player;
        this.killer = killer;
        this.pvpArena = pvpArena;
        this.pvpStatistics = pvpStatistics;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
