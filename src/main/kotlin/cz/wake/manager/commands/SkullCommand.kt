package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftlibs.utils.ChatInfo
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("skull|hlava")
@Description("Dá ti tvojí hlavu")
class SkullCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lSkull commands:")
        help.showHelp()
    }

    @Default
    fun giveSkull(sender: CommandSender) {
        if (sender is Player) {
            if (sender.hasPermission("craftmanager.vip.skull")) {
                if (!sender.hasPermission("craftmanager.cooldown.skull-command")) {
                    giveHead(sender)
                } else {
                    ChatInfo.INFO.send(sender, "Tento příkaz lze použít pouze 1x za 10 minut.")
                }
            }
        }
    }

    private fun giveHead(player: Player) {
        try {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give %creator% minecraft:player_head{\"SkullOwner\":\"%creator%\"}".replace("%creator%".toRegex(), player.name))
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user %creator% permission settemp craftmanager.cooldown.skull-command true 10m".replace("%creator%".toRegex(), player.name))
        } catch (e: Exception) {
            ChatInfo.DANGER.send(player, "Chyba v API Mojangu! Zkus to znova zachvilku! :)")
        }
    }
}