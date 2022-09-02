package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.wake.manager.Main
import cz.wake.manager.utils.ServerType
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffectType

@CommandAlias("glow")
@Description("Umožní ti vypnutí/zapnutí efektu glow")
class GlowCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lGlow commands:")
        help.showHelp()
    }

    @Default
    fun changeGlowEffect(sender: CommandSender?) {
        if (sender is Player) {
            if (Main.getInstance().serverType == ServerType.ANARCHY) {
                ChatInfo.DANGER.send(sender, "Na tomto serveru tato výhoda neplatí!")
                return
            }
            if (sender.hasPermission("craftmanager.glow")) {
                if (!sender.isGlowing) {
                    sender.isGlowing = true
                    ChatInfo.SUCCESS.send(sender, "Aktivoval jsi efekt §5Glowing!")
                } else {
                    sender.isGlowing = false
                    if (sender.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        sender.removePotionEffect(PotionEffectType.INVISIBILITY)
                    }
                    ChatInfo.SUCCESS.send(sender, "Deaktivoval jsi efekt §5Glowing!")
                }
            } else {
                ChatInfo.DANGER.send(sender, "K použití této schopnosti musíš vlastnit VIP!")
            }
        }
    }
}