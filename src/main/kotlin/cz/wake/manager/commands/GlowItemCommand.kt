package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftcore.builders.items.ItemBuilder
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.wake.manager.Main
import cz.wake.manager.utils.ServerType
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@CommandAlias("glowitem|gi")
@Description("Aplikuje ti glow efekt na item")
class GlowItemCommand : BaseCommand() {

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lGlowitem commands:")
        help.showHelp()
    }

    @Default
    fun makeGlow(sender: CommandSender) {
        if (sender is Player) {
            if (!sender.hasPermission("craftmanager.vip.glowingitems")) {
                ChatInfo.ERROR.send(sender, "Nemáš dostatek práv, na toto musíš mít VIP. §f/vip")
                return
            }
            if (Main.getInstance().serverType == ServerType.ANARCHY) {
                ChatInfo.ERROR.send(sender, "Na tomto serveru tato výhoda neplati!")
                return
            }
            val item = sender.inventory.itemInMainHand
            if (item == null) {
                ChatInfo.ERROR.send(sender, "Musíš držet item, na který chceš dat glowing efekt.")
                return
            }
            if (item.isSimilar(ItemStack(Material.GOLDEN_APPLE, 1, 1.toShort()))) {
                ChatInfo.ERROR.send(sender, "Na tento item nelze použít příkaz /gi")
                return
            }
            if (item.hasItemMeta()) {
                if (item.itemMeta.hasLore()) {
                    ChatInfo.ERROR.send(sender, "Na tento item nelze použít příkaz /gi")
                    return
                }
            }
            if (item.amount > 1) {
                ChatInfo.ERROR.send(sender, "GlowItem lze použít pouze na jeden item!")
                return
            }
            if (!item.enchantments.isEmpty()) {
                ChatInfo.ERROR.send(sender, "Nelze použít GlowItem na item, který již má enchant!")
                return
            }
            if (item.itemMeta != null && item.itemMeta.hasCustomModelData()) {
                ChatInfo.ERROR.send(sender, "Nelze aplikovat glowing efekt na kosmetický item.")
                return
            }
            val itemBuilder = ItemBuilder(item)
            sender.inventory.setItemInMainHand(null)
            itemBuilder.setGlowing() // TODO: Od 1.21 nefunguje DURABILITY enchant
            itemBuilder.setAmount(1)
            sender.inventory.setItemInMainHand(itemBuilder.build())
            ChatInfo.SUCCESS.send(sender, "Item byl změněn na Glowing!")
        }
    }
}