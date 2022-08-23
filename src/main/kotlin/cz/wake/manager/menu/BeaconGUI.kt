package cz.wake.manager.menu

import cz.craftmania.craftcore.builders.items.ItemBuilder
import cz.craftmania.craftcore.inventory.builder.ClickableItem
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider
import cz.craftmania.craftlibs.utils.ChatInfo
import cz.craftmania.craftpack.api.Buttons
import cz.wake.manager.Main
import cz.wake.manager.servers.vanilla.DragonSlayerListener
import cz.wake.manager.utils.ServerType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class BeaconGUI : InventoryProvider {

    override fun init(p: Player, content: InventoryContents) {
        content.set(0, 2, ClickableItem.of(ItemBuilder(Material.FEATHER).setName("§f§lSpeed").build()) {
            activateEffect(p, PotionEffectType.SPEED, 2, "Speed")
        })
        content.set(0, 3, ClickableItem.of(ItemBuilder(Material.GOLDEN_PICKAXE).setName("§e§lHaste").build()) {
            activateEffect(p, PotionEffectType.FAST_DIGGING, 0, "Haste")
        })
        content.set(0, 4, ClickableItem.of(ItemBuilder(Material.IRON_BOOTS).setName("§a§lJump Boost").build()) {
            activateEffect(p, PotionEffectType.JUMP, 1, "Jump Boost")
        })
        content.set(0, 5, ClickableItem.of(ItemBuilder(Material.BLAZE_POWDER).setName("§6§lFire Resistance").build()) {
            activateEffect(p, PotionEffectType.FIRE_RESISTANCE, 1, "Fire Resistance")
        })
        content.set(0, 6, ClickableItem.of(ItemBuilder(Material.ENDER_EYE).setName("§9§lNight Vision").build()) {
            activateEffect(p, PotionEffectType.NIGHT_VISION, 5, "Night Vision")
        })
        content.set(1, 2, ClickableItem.of(ItemBuilder(Material.PRISMARINE_CRYSTALS).setName("§3§lWater Breathing").build()) {
            activateEffect(p, PotionEffectType.WATER_BREATHING, 1, "Water Breathing")
        })

        if (Main.getInstance().serverType === ServerType.CREATIVE) {
            content.set(1, 3, ClickableItem.of(ItemBuilder(Material.YELLOW_STAINED_GLASS).setName("§e§lNeviditelnost s glowingem").build()) {
                p.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }.forEach(p::removePotionEffect)
                p.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 12000000, 0, true, false))
                p.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 12000000, 0, true, false))
                ChatInfo.SUCCESS.send(p, "Aktivoval jsi permanentní §bNeviditelnost s glowingem!");
                p.closeInventory()
            })
        } else {

        }
        content.set(1, 8, ClickableItem.of(ItemBuilder(Material.BARRIER).setName("§c§lZrušit").build()) {
            deactivateEffect(p)
        })
    }

    private fun deactivateEffect(p: Player) {
        p.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }.forEach(p::removePotionEffect)
        ChatInfo.SUCCESS.send(p, "Odebral jsi všechny aktivní efekty!")
        p.closeInventory()
    }

    private fun activateEffect(p: Player, effect: PotionEffectType, amlifier: Int, displayEffectName: String) {
        p.activePotionEffects.stream().map{ obj: PotionEffect -> obj.type }.forEach(p::removePotionEffect)
        p.addPotionEffect(PotionEffect(effect, 12000000, amlifier, true, false))
        ChatInfo.SUCCESS.send(p, "Aktivoval jsi permanentní §b${displayEffectName}!")
        p.closeInventory()
    }
}