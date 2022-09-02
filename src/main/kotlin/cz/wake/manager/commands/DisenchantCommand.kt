package cz.wake.manager.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.wake.manager.Main
import cz.wake.manager.utils.InventoryUtils
import cz.wake.manager.utils.ServerType
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.concurrent.atomic.AtomicInteger

@CommandAlias("disenchant")
@Description("Získání zpět enchantů na nástroji nebo knihy.")
class DisenchantCommand : BaseCommand() {

    private val inventoryUtils = InventoryUtils()

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lDisenchant commands:")
        help.showHelp()
    }

    @Default
    fun disenchantCommand(sender: CommandSender) {
        if (sender is Player) {
            val player: Player = sender
            if (player.hasPermission("craftmanager.vip.disenchant")) {
                if (Main.getInstance().serverType == ServerType.ANARCHY) {
                    ChatInfo.DANGER.send(player, "Na tomto serveru tato výhoda neplatí.")
                    return
                }
                val itemInHand = player.inventory.itemInMainHand
                if (itemInHand.itemMeta == null) {
                    ChatInfo.DANGER.send(player, "Tento item není poničen nebo provést disenchant.")
                    return
                }
                val durability = itemInHand.durability
                if (itemInHand.enchantments.isNotEmpty()
                    && itemInHand.type != Material.TRIPWIRE_HOOK
                    && itemInHand.type != Material.KNOWLEDGE_BOOK
                ) {
                    val enchantments = itemInHand.enchantments

                    // Kalkulace ceny
                    val finalPriceLvls = AtomicInteger()
                    finalPriceLvls.addAndGet(enchantments.values.size * 5)
                    enchantments.forEach { (enchantment: Enchantment, integer: Int) ->
                        if (integer > 1) finalPriceLvls.addAndGet(integer)
                    }

                    // Kontrola Glowing items
                    if (itemInHand.enchantments.containsKey(Enchantment.DURABILITY)) {
                        if (itemInHand.enchantments[Enchantment.DURABILITY] == 0) {
                            ChatInfo.DANGER.send(player, "Nelze použít Disenchant na item, který má na sobě Glowing efekt.")
                            return
                        }
                    }
                    if (player.level >= finalPriceLvls.get()) {
                        val withoutEnchant = ItemStack(itemInHand)
                        withoutEnchant.enchantments.forEach { (enchant: Enchantment?, level: Int?) ->
                            withoutEnchant.removeEnchantment(
                                enchant!!
                            )
                        }
                        player.inventory.removeItem(player.inventory.itemInMainHand)
                        player.level = player.level - finalPriceLvls.get()

                        enchantments.forEach(action = {
                            val enchantBook = ItemStack(Material.ENCHANTED_BOOK, 1)
                            val enchantment: Enchantment = it.key
                            val level: Int = it.value
                            inventoryUtils.givePlayerItemOrDrop(player, addBookEnchantment(enchantBook, enchantment, level))
                        })

                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac")
                        player.sendMessage("")
                        player.sendMessage("§aPředmět byl disenchantován za §6$finalPriceLvls LVL")
                        player.sendMessage("§7Enchant = 5 LVL")
                        player.sendMessage("")
                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac")

                        withoutEnchant.setDurability(durability)
                        inventoryUtils.givePlayerItemOrDrop(player, withoutEnchant)
                    } else {
                        ChatInfo.INFO.send(player, "Musíš mít minimálně $finalPriceLvls levelů na disenchant totoho itemu!")
                    }
                } else {
                    ChatInfo.DANGER.send(player, "Na požadovaný item nelze použít Disenchant!")
                }
            } else {
                ChatInfo.DANGER.send(player, "K použití tohoto příkazu musíš mít zakoupené VIP!")
            }
        }
    }

    private fun addBookEnchantment(item: ItemStack, enchantment: Enchantment, level: Int): ItemStack? {
        val meta = item.itemMeta as EnchantmentStorageMeta
        meta.addStoredEnchant(enchantment, level, true)
        item.itemMeta = meta
        return item
    }




}