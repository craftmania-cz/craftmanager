package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftcore.inventory.builder.SmartInventory
import cz.wake.manager.menu.RechargeGUI
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("recharge")
@Description("Doplnění fly díky kterému můžeš létat na serveru.")
class RechargeCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lRecharge commands:")
        help.showHelp()
    }

    @Default
    fun openMenu(sender: CommandSender) {
        if (sender is Player) {
            SmartInventory.builder().provider(RechargeGUI())
                .title(":offset_-18::recharge_menu:").size(5, 9).build().open(sender)
        }
    }

}