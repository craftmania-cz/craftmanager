package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import cz.craftmania.crafteconomy.utils.Logger;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftlibs.utils.ExperienceUtil;
import cz.wake.manager.utils.experience.EXPBottlesUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("getxp|getexp|xpbottle|getlvl|getlevel|exp")
@Description("Umožní ti ukládat si XP do EXP bottlů")
public class GetXP_command extends BaseCommand {

    @Default
    public void showPlayersEXP(CommandSender sender) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }
        ChatInfo.INFO.send((Player) sender, "Momentálně máš §f" + ExperienceUtil.getExp((Player) sender) + "EXP");
    }

    @Default
    @Syntax("[exp]")
    public void getPlayersEXP(CommandSender sender, long exp) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }
        Player player = (Player) sender;

        if (exp == 0) {
            ChatInfo.DANGER.send(player, "Číslo musí být větší než 0!");
            return;
        }
        if (ExperienceUtil.getExp(player) < exp) {
            ChatInfo.DANGER.send(player,"Máš nedostatek EXP!");
            return;
        }
        if (giveEXPBottleToPlayer(player, exp))
            ExperienceUtil.changeExp(player, -exp);
    }

    @Subcommand("level|lvl")
    @Syntax("level|lvl [exp]")
    public void getPlayersEXPByLevel(CommandSender sender, int level) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }
        Player player = (Player) sender;
        long exp = ExperienceUtil.getExpFromLevel(level);

        if (exp == 0) {
            ChatInfo.DANGER.send(player, "Číslo musí být větší než 0!");
            return;
        }
        if (ExperienceUtil.getExp(player) < exp) {
            ChatInfo.DANGER.send(player, "Máš nedostatek levelů!");
            return;
        }
        if (giveEXPBottleToPlayer(player, exp))
            ExperienceUtil.changeExp(player, -exp);
    }

    @Subcommand("max")
    public void getPlayersEXPMax(CommandSender sender) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }

        Player player = (Player) sender;

        if (ExperienceUtil.getExp(player) == 0) {
            ChatInfo.DANGER.send(player, "Musíš mít více jak 1LVL!");
            return;
        }

        if (giveEXPBottleToPlayer(player, ExperienceUtil.getExp(player))) {
            player.setLevel(0);
            player.setExp(0);
        }
    }

    @Subcommand("help")
    public void getHelp(CommandSender sender) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }
        Player player = (Player) sender;
        ChatInfo.INFO.send(player, "§6Pro vytvoření EXP Bottle použij §e/exp [EXP]§6 a nebo §e/exp lvl [LEVEL]§6.");
    }

    // Utils
    public boolean giveEXPBottleToPlayer(Player player, long exp) {
        if (player.getInventory().firstEmpty() == -1) {
            ChatInfo.DANGER.send(player, "Máš plný inventář!");
            return false;
        }
        player.getInventory().addItem(EXPBottlesUtil.makeCustomExpBottle(exp));
        ChatInfo.INFO.send(player, "Vytvořil jsi EXP Bottle s §f" + exp + "EXP");
        return true;
    }
}
