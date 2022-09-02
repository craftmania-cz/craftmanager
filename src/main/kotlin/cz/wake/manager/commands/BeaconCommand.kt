package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftcore.inventory.builder.SmartInventory
import cz.wake.manager.menu.BeaconGUI
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("beacon")
@Description("Otevře ti menu pro beacon")
class BeaconCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lBeacon commands:")
        help.showHelp()
    }

    @Default
    fun openMenu(sender: CommandSender) {
        if (sender is Player) {
            SmartInventory.builder().provider(BeaconGUI()).title(":offset_-18::beacon_menu:")
                .size(3, 9).build().open(sender)
        }
    }

}