package cz.wake.manager.menu

import cz.craftmania.craftcore.builders.items.ItemBuilder
import cz.craftmania.craftcore.inventory.builder.ClickableItem
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.craftmania.craftpack.api.Buttons
import cz.wake.manager.Main
import cz.wake.manager.utils.ServerType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class BeaconGUI : InventoryProvider {

    override fun init(player: Player, content: InventoryContents) {
        content.set(0, 2, ClickableItem.of(ItemBuilder(Material.FEATHER).setName("§f§lSpeed").build()) {
            activateEffect(player, PotionEffectType.SPEED, 2, "Speed")
        })
        content.set(0, 3, ClickableItem.of(ItemBuilder(Material.GOLDEN_PICKAXE).setName("§e§lHaste").build()) {
            activateEffect(player, PotionEffectType.HASTE, 0, "Haste")
        })
        content.set(0, 4, ClickableItem.of(ItemBuilder(Material.IRON_BOOTS).setName("§a§lJump Boost").build()) {
            activateEffect(player, PotionEffectType.JUMP_BOOST, 1, "Jump Boost")
        })
        content.set(0, 5, ClickableItem.of(ItemBuilder(Material.BLAZE_POWDER).setName("§6§lFire Resistance").build()) {
            activateEffect(player, PotionEffectType.FIRE_RESISTANCE, 1, "Fire Resistance")
        })
        content.set(0, 6, ClickableItem.of(ItemBuilder(Material.ENDER_EYE).setName("§9§lNight Vision").build()) {
            activateEffect(player, PotionEffectType.NIGHT_VISION, 5, "Night Vision")
        })
        content.set(1, 2, ClickableItem.of(ItemBuilder(Material.PRISMARINE_CRYSTALS).setName("§3§lWater Breathing").build()) {
            activateEffect(player, PotionEffectType.WATER_BREATHING, 1, "Water Breathing")
        })

        if (Main.getInstance().serverType === ServerType.CREATIVE) {
            content.set(1, 3, ClickableItem.of(ItemBuilder(Material.YELLOW_STAINED_GLASS).setName("§e§lNeviditelnost s glowingem").build()) {
                player.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }.forEach(player::removePotionEffect)
                player.isGlowing = true
                player.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 12000000, 0, true, false))
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bNeviditelnost s glowingem!");
                player.closeInventory()
            })
        } else {
            content.set(1, 3, ClickableItem.empty(ItemBuilder(Buttons.LOCKED.getPureItemStack()).setName("§e§lNeviditelnost s glowingem").setLore("§7Nelze použít na tomto serveru.").build()))
        }
        content.set(2, 4, ClickableItem.of(ItemBuilder(Material.BARRIER).setName("§c§lDeaktivovat").build()) {
            deactivateEffect(player)
        })
    }

    private fun deactivateEffect(player: Player) {
        if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            player.isGlowing = false
        }
        player.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }.forEach(player::removePotionEffect)
        ChatInfo.SUCCESS.send(player, "Odebral jsi všechny aktivní efekty!")
        player.closeInventory()
    }

    private fun activateEffect(player: Player, effect: PotionEffectType, amlifier: Int, displayEffectName: String) {
        player.activePotionEffects.stream().map{ obj: PotionEffect -> obj.type }.forEach(player::removePotionEffect)
        player.addPotionEffect(PotionEffect(effect, 12000000, amlifier, true, false))
        ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §b${displayEffectName}!")
        player.closeInventory()
    }
}