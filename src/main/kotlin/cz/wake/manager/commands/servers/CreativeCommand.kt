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

@CommandAlias("creative")
@Description("Připojí tě na Creative")
class CreativeCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lCreative commands:")
        help.showHelp()
    }

    @Default
    fun connectToCreative(sender: CommandSender?) {
        if (sender is Player) {
            try {
                ChatInfo.INFO.send(sender, "Teleportuji na server §fCreative")
                Main.getInstance().sendToServer(sender, "creative")
            } catch (exception: Exception) {
                exception.printStackTrace()
                sender.sendMessage("§cTeleport na server §fCreative §cse nezdařil!")
                ChatInfo.DANGER.send(sender, "Teleport se nezdařil: §fCreative")
                Main.getInstance().sendSentryException(exception)
            }
        }
    }
}