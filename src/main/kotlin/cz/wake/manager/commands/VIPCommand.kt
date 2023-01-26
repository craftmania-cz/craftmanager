package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.craftmania.craftlibs.utils.ServerColors
import cz.wake.manager.Main
import cz.wake.manager.utils.ServerType
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.util.RGBLike
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("vip|vipmenu")
@Description("Otevře ti menu pro koupi VIP")
class VIPCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lVIP commands:")
        help.showHelp()
    }

    @Default
    fun openMenu(sender: CommandSender) {
        if (sender is Player) {
            sender.sendMessage("")
            sender.sendMessage(Component.text("Aktuální výhody a ceny našeho VIP najdeš na:").color(ServerColors.ROLE_DEVELOPER.textColor))
            sender.sendMessage(Component.text("https://craftmania.cz/vip").color(ServerColors.CRAFTMANIA_BLUE.textColor)
                .clickEvent(ClickEvent.openUrl("https://craftmania.cz/vip"))
                .hoverEvent(HoverEvent.showText(Component.text("Kliknutím budeš přesměrován na náš web").color(NamedTextColor.GRAY)))
            )
            sender.sendMessage("")
        } else {
            sender.sendMessage("Z konzole VIP nefunguje!")
        }
    }
}