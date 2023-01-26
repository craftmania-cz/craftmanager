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

@CommandAlias("vanilla")
@Description("Teleportuje tě na Vanilla: Lands")
class VanillaCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lVanilla: Lands commands:")
        help.showHelp()
    }

    @Default
    fun connectToVanilla(sender: CommandSender?) {
        if (sender is Player) {
            try {
                ChatInfo.INFO.send(sender, "Teleportuji na server §fVanilla: Lands")
                Main.getInstance().sendToServer(sender, "vanilla")
            } catch (exception: Exception) {
                exception.printStackTrace()
                ChatInfo.DANGER.send(sender, "Teleport na se nezdařil: §fVanilla: Lands")
                Main.getInstance().sendSentryException(exception)
            }
        }
    }
}