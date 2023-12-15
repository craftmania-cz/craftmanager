package cz.wake.manager.commands.servers

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.wake.manager.Main
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("skyblock")
@Description("Připojí tě na Skyblock")
class SkyblockCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lSkyblock commands:")
        help.showHelp()
    }

    @Default
    fun connectToSkyblock(sender: CommandSender?) {
        if (sender is Player) {
            try {
                ChatInfo.INFO.send(sender, "Teleportuji tě na server §fOneblock")
                Main.getInstance().sendToServer(sender, "skyblock")
            } catch (e: Exception) {
                e.printStackTrace()
                ChatInfo.DANGER.send(sender, "Teleport na se nezdařil: §fOneblock")
            }
        }
    }
}