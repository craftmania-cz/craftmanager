package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.managers.PvPManager;
import cz.wake.manager.objects.pvp.PvPArena;
import cz.wake.manager.objects.pvp.PvPSpawnLocation;
import cz.wake.manager.utils.BukkitUtil;
import cz.wake.manager.utils.Log;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("/pvp|/pvp-arena|pvpadmin")
@CommandPermission("craftmania.at")
public class PvPAdmin_command extends BaseCommand {

    @Subcommand("respawn-location")
    public void setRespawnLocation(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager.setRespawnLocation(player.getLocation());
        ChatInfo.SUCCESS.send(player, "Na tvojí lokaci §e" + BukkitUtil.makePrettyLocation(player.getLocation()) + "{c} byla nastavena Respawn lokace!");
    }

    @Subcommand("create")
    @Syntax("[arena_id] [name] [dropsEnabled] [effectsEnabled]")
    @CommandCompletion("[id] [name] [dropsEnabled] [effectsEnabled]")
    public void createPvPArena(CommandSender sender, String id, String name, boolean dropsEnabled, boolean effectsEnabled) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (pvpManager.existsArenaById(id)) {
            ChatInfo.DANGER.send(player, "PvP Aréna s tímto ID už existuje!");
            return;
        }

        if (pvpManager.isArenaInWorld(world)) {
            ChatInfo.DANGER.send(player, "V tomto světě se už PvP Aréna nachází!");
            return;
        }

        PvPArena pvpArena = new PvPArena();
        pvpArena.setId(id);
        pvpArena.setName(name);
        pvpArena.setWorld(world);
        pvpArena.setDropsEnabled(dropsEnabled);
        pvpArena.setEffectsEnabled(effectsEnabled);

        pvpManager.addPvPArena(pvpArena);

        ChatInfo.SUCCESS.send(player, "PvP Aréna byla vytvořena! Příkazem §e//pvp edit spawn-location <list|add|remove [index]>{c} lze upravovat spawn lokace.");
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit spawn-location list")
    public void listSpawnLocations(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        List<PvPSpawnLocation> locations = pvpManager.getArenaByWorld(world).getSpawnLocations();

        ChatInfo.INFO.send(player, "Spawn lokací: " + locations.size());

        int index = 0;
        for (PvPSpawnLocation pvpSpawnLocation : pvpManager.getArenaByWorld(world).getSpawnLocations()) {
            ChatInfo.INFO.send(player, "[" + index + "]: " + BukkitUtil.makePrettyLocation(pvpSpawnLocation.getLocation()));
            index++;
        }
    }

    @Subcommand("edit spawn-location add")
    public void addSpawnLocation(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        pvpManager.getArenaByWorld(world).addPvPSpawnLocation(new PvPSpawnLocation(player.getLocation()));
        ChatInfo.SUCCESS.send(player, "Na tvojí lokaci §e" + BukkitUtil.makePrettyLocation(player.getLocation()) + "{c} byla přidána Spawn lokace!");
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit spawn-location remove")
    public void addSpawnLocation(CommandSender sender, int index) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        boolean success = pvpManager.getArenaByWorld(world).removePvPSpawnLocation(index);

        if (success) {
            ChatInfo.SUCCESS.send(player, "Odstranil jsi spawn lokaci s indexem §e" + index + "{c}!");
        } else {
            ChatInfo.DANGER.send(player, "Nepovedlo se odstranit spawn lokaci s indexem §e" + index + "{c}. Zkontroluj si zadaný index!");
        }
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit set drops")
    public void setDrops(CommandSender sender, boolean dropsEnabled) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        pvpManager.getArenaByWorld(world).setDropsEnabled(dropsEnabled);

        if (dropsEnabled) {
            ChatInfo.SUCCESS.send(player, "Zapnul jsi dropy v této PvP Aréně!");
        } else {
            ChatInfo.DANGER.send(player, "Vypnul jsi dropy v této PvP Aréně!");
        }
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit set effects")
    public void setEffects(CommandSender sender, boolean effectsEnabled) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        pvpManager.getArenaByWorld(world).setEffectsEnabled(effectsEnabled);

        if (effectsEnabled) {
            ChatInfo.SUCCESS.send(player, "Zapnul jsi efekty v této PvP Aréně!");
        } else {
            ChatInfo.DANGER.send(player, "Vypnul jsi efekty v této PvP Aréně!");
        }
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit set id")
    public void setId(CommandSender sender, String id) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        pvpManager.getArenaByWorld(world).setId(id);

        ChatInfo.SUCCESS.send(player, "Nastavil jsi id na §e" + id + "{c}!");
        Main.getInstance().getPvpManager().save();
    }

    @Subcommand("edit set name")
    public void setName(CommandSender sender, String name) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je pro hráče!");
            return;
        }

        PvPManager pvpManager = Main.getInstance().getPvpManager();
        World world = player.getWorld();

        if (!processArenaNotInWorld(player)) {
            return;
        }

        pvpManager.getArenaByWorld(world).setName(name);

        ChatInfo.SUCCESS.send(player, "Nastavil jsi name na §r" + name + "{c}!");
        Main.getInstance().getPvpManager().save();
    }

    private boolean processArenaNotInWorld(Player player) {
        PvPManager pvpManager = Main.getInstance().getPvpManager();

        if (!pvpManager.isArenaInWorld(player.getWorld())) {
            ChatInfo.DANGER.send(player, "V tomto světě se nenachází PvP Aréna!");
            return false;
        }

        return true;
    }
}
