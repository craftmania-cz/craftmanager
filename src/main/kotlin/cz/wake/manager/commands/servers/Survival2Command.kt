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

@CommandAlias("survival2")
@Description("Připojí tě na Survival")
class Survival2Command : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lSurvival commands:")
        help.showHelp()
    }

    @Default
    fun connectToSurvival(sender: CommandSender) {
        if (sender is Player) {
            try {
                ChatInfo.INFO.send(sender, "Teleportuji tě na server §fSurvival: Classic")
                Main.getInstance().sendToServer(sender, "survival2")
            } catch (exception: Exception) {
                exception.printStackTrace()
                ChatInfo.DANGER.send(sender, "Teleport na se nezdařil: §fSurvival: Classic")
                Main.getInstance().sendSentryException(exception)
            }
        }
    }
}