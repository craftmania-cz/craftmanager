package cz.wake.manager.menu

import cz.craftmania.craftcore.builders.items.ItemBuilder
import cz.craftmania.craftcore.inventory.builder.ClickableItem
import cz.craftmania.craftcore.inventory.builder.SmartInventory
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider
import cz.craftmania.crafteconomy.utils.VaultUtils
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.craftmania.craftlibs.utils.ServerColors
import cz.craftmania.craftpack.api.Buttons
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.function.Consumer

class RechargeGUI : InventoryProvider {

    private val vaultUtils = VaultUtils()

    override fun init(player: Player, content: InventoryContents) {
        val money200 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get().toString() + "Obnovit: +1000")
                .setLore("§eCena: §6$§f200", "", ServerColors.DARK_GRAY.get().toString() + "Kliknutím si dobiješ fly")
                .build()
        content[1, 2] = ClickableItem.of(money200, Consumer {
            if (vaultUtils.getBalance(player) < 200) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$200")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 1000".replace("%nick%", player.name)
            )
            vaultUtils.withdrawPlayer(player, 200.0)
            openRechargerGui(player)
        })
        val money500 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get().toString() + "Obnovit: +2500")
                .setLore("§eCena: §6$§f500", "", ServerColors.DARK_GRAY.get().toString() + "Kliknutím si dobiješ fly")
                .build()
        content[2, 2] = ClickableItem.of(money500, Consumer {
            if (vaultUtils.getBalance(player) < 500) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$500")
                return@Consumer
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi flightcharge add %nick% 2500".replace("%nick%", player.name))
            vaultUtils.withdrawPlayer(player, 500.0)
            openRechargerGui(player)
        })
        val money1000 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.get().toString() + "Obnovit: +5300")
                .setLore("§eCena: §6$§f1,000", "", ServerColors.DARK_GRAY.get().toString() + "Kliknutím si dobiješ fly")
                .build()
        content[3, 2] = ClickableItem.of(money1000, Consumer { inventoryClickEvent: InventoryClickEvent? ->
            if (vaultUtils.getBalance(player) < 1000) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek peněz §f$1000")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 5300".replace("%nick%", player.name)
            )
            vaultUtils.withdrawPlayer(player, 1000.0)
            openRechargerGui(player)
        })
        content[1, 4] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
        content[2, 4] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
        content[3, 4] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
        content[1, 6] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
        content[2, 6] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
        content[3, 6] = ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§6Nedostupné").build())
    }

    override fun update(player: Player, contents: InventoryContents) {}

    private fun openRechargerGui(player: Player) {
        SmartInventory.builder().provider(RechargeGUI()).title(":offset_-18::recharge_menu:").size(5, 9).build().open(player)
    }
}