package cz.wake.manager.menu

import cz.craftmania.craftcore.builders.items.ItemBuilder
import cz.craftmania.craftcore.inventory.builder.ClickableItem
import cz.craftmania.craftcore.inventory.builder.SmartInventory
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider
import cz.craftmania.crafteconomy.api.EconomyAPI
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

        // Money
        val money200 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.chatColor.toString() + "Obnovit: +1000")
                .setLore("§eCena: §6$§f200", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
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
        val money500 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.chatColor.toString() + "Obnovit: +2500")
                .setLore("§eCena: §6$§f500", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
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
        val money1000 = ItemBuilder(Material.FEATHER).setName(ServerColors.ROLE_DIAMOND.chatColor.toString() + "Obnovit: +5300")
                .setLore("§eCena: §6$§f1,000", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
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

        // Event Points
        val eventPoints1 = ItemBuilder(Material.GOLD_NUGGET).setName(ServerColors.SERVER_SKYBLOCK.chatColor.toString() + "Obnovit: +1000")
            .setLore("§eCena: §f7 EventPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[1, 4] = ClickableItem.of(eventPoints1, Consumer {
            if (EconomyAPI.EVENT_POINTS.get(player) < 7) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Event Points §f7")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 1000".replace("%nick%", player.name)
            )
            EconomyAPI.EVENT_POINTS.take(player, 7)
            openRechargerGui(player)
        })
        val eventPoints2 = ItemBuilder(Material.GOLD_NUGGET).setName(ServerColors.SERVER_SKYBLOCK.chatColor.toString() + "Obnovit: +2500")
            .setLore("§eCena: §f16 EventPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[2, 4] = ClickableItem.of(eventPoints2, Consumer {
            if (EconomyAPI.EVENT_POINTS.get(player) < 16) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Event Points §f16")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 2500".replace("%nick%", player.name)
            )
            EconomyAPI.EVENT_POINTS.take(player, 16)
            openRechargerGui(player)
        })
        val eventPoints3 = ItemBuilder(Material.GOLD_NUGGET).setName(ServerColors.SERVER_SKYBLOCK.chatColor.toString() + "Obnovit: +5300")
            .setLore("§eCena: §f36 EventPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[3, 4] = ClickableItem.of(eventPoints3, Consumer {
            if (EconomyAPI.EVENT_POINTS.get(player) < 36) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Event Points §f36")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 5300".replace("%nick%", player.name)
            )
            EconomyAPI.EVENT_POINTS.take(player, 36)
            openRechargerGui(player)
        })

        // Quest Points
        val questPoints1 = ItemBuilder(Material.IRON_NUGGET).setName(ServerColors.SERVER_VANILLA_ANARCHY.chatColor.toString() + "Obnovit: +1000")
            .setLore("§eCena: §f22 QuestPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[1, 6] = ClickableItem.of(questPoints1, Consumer {
            if (EconomyAPI.QUEST_POINTS.get(player) < 22) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Quest Points §f22")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 1000".replace("%nick%", player.name)
            )
            EconomyAPI.QUEST_POINTS.take(player, 22)
            openRechargerGui(player)
        })
        val questPoints2 = ItemBuilder(Material.IRON_NUGGET).setName(ServerColors.SERVER_VANILLA_ANARCHY.chatColor.toString() + "Obnovit: +2500")
            .setLore("§eCena: §f48 QuestPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[2, 6] = ClickableItem.of(questPoints2, Consumer {
            if (EconomyAPI.QUEST_POINTS.get(player) < 48) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Quest Points §f48")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 2500".replace("%nick%", player.name)
            )
            EconomyAPI.QUEST_POINTS.take(player, 48)
            openRechargerGui(player)
        })
        val questPoints3 = ItemBuilder(Material.IRON_NUGGET).setName(ServerColors.SERVER_VANILLA_ANARCHY.chatColor.toString() + "Obnovit: +5300")
            .setLore("§eCena: §f100 QuestPoints", "", ServerColors.DARK_GRAY.chatColor.toString() + "Kliknutím si dobiješ fly")
            .build()
        content[3, 6] = ClickableItem.of(questPoints3, Consumer {
            if (EconomyAPI.QUEST_POINTS.get(player) < 100) {
                ChatInfo.DANGER.send(player, "Nelze si zakoupit recharge, nemáš dostatek Quest Points §f100")
                return@Consumer
            }
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "cmi flightcharge add %nick% 5300".replace("%nick%", player.name)
            )
            EconomyAPI.QUEST_POINTS.take(player, 100)
            openRechargerGui(player)
        })
    }

    override fun update(player: Player, contents: InventoryContents) {}

    private fun openRechargerGui(player: Player) {
        SmartInventory.builder().provider(RechargeGUI()).title(":offset_-18::recharge_menu:").size(5, 9).build().open(player)
    }
}