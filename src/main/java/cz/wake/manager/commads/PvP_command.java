package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftlibs.utils.TextComponentBuilder;
import cz.craftmania.craftlibs.utils.actions.ConfirmAction;
import cz.wake.manager.Main;
import cz.wake.manager.managers.PvPManager;
import cz.wake.manager.objects.pvp.PvPArena;
import cz.wake.manager.utils.Log;
import lombok.SneakyThrows;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

@CommandAlias("pvp|pvp-arena")
public class PvP_command extends BaseCommand {

    @SneakyThrows
    @Default
    public void joinPvPArena(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je jen pro hráče!");
            return;
        }

        player.sendMessage("");
        player.sendMessage("§e§lPvP Aréna");
        player.sendMessage("§7Zvol si prosím mód. Najetím na arény zjistíš, jestli tam dropují věci.");
        player.sendMessage("");

        List<PvPArena> pvpArenas = Main.getInstance().getPvpManager().getPvpArenas();

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        for (PvPArena pvpArena : pvpArenas) {
            new ConfirmAction.Builder()
                    .setPlayer(player)
                    .generateIdentifier()
                    .setDelay(10L)
                    .addComponent(a -> new TextComponentBuilder(" " + pvpArena.getName())
                            .setPerformedCommand(a.getConfirmationCommand())
                            .setTooltip(pvpArena.getName() + "\n§7Item drop: " + (pvpArena.isDropsEnabled() ? "§cANO" : "§aNE"))
                            .getComponent())
                    .setRunnable(actionPlayer -> {
                        if (atomicBoolean.get()) {
                            ChatInfo.DANGER.send(actionPlayer, "Už ses teleportoval do PvP Arény!");
                            return;
                        }

                        pvpArena.teleport(actionPlayer);
                        atomicBoolean.set(true);
                    }).build().sendTextComponents();
        }

        player.sendMessage("");
    }

    @Default
    @Syntax("[arena_id]")
    @CommandCompletion("@pvp_arena_ids")
    public void joinPvPArena(CommandSender sender, String id) {
        if (!(sender instanceof Player player)) {
            Log.withPrefix("Tento příkaz je jen pro hráče!");
            return;
        }

        PvPArena pvpArena = Main.getInstance().getPvpManager().getArenaById(id);

        if (pvpArena == null) {
            ChatInfo.DANGER.send(player, "Tato PvP Aréna neexistuje!");
            return;
        }

        pvpArena.teleport(player);
    }
}
