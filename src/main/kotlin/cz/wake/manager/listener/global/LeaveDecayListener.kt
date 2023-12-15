package cz.wake.manager.listener.global

import cz.craftmania.craftlibs.utils.UtilMath
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.LeavesDecayEvent
import org.bukkit.inventory.ItemStack

/**
 * Tento listener upravuje na Skyblock serverech dropy z stromů.
 * Garantuje tzv. rotaci věci saplingů v MC bez využití shopu.
 */
class LeaveDecayListener : Listener {
    
    @EventHandler
    fun onLeafDecay(event: LeavesDecayEvent) {
        val block = event.block
        if (block.type == Material.OAK_LEAVES) { // Oak -> Spruce 5%
            val chance = UtilMath.random(1, 100)
            if (chance <= 5) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.SPRUCE_SAPLING))
            }
        }
        if (block.type == Material.SPRUCE_LEAVES) { // Spruce -> Acacia 3%
            val chance = UtilMath.random(1, 100)
            if (chance <= 3) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.ACACIA_SAPLING))
            }
        }
        if (block.type == Material.ACACIA_LEAVES) { // Acacia -> Birch 5%
            val chance = UtilMath.random(1, 100)
            if (chance <= 5) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.BIRCH_SAPLING))
            }
        }
        if (block.type == Material.BIRCH_LEAVES) { // Birch -> Jungle 2%
            val chance = UtilMath.random(1, 100)
            if (chance <= 2) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.JUNGLE_SAPLING))
            }
        }
        if (block.type == Material.JUNGLE_LEAVES) { // Jungle -> Dark Oak 3%
            val chance = UtilMath.random(1, 100)
            if (chance <= 3) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.DARK_OAK_SAPLING))
            }
        }
        if (block.type == Material.DARK_OAK_LEAVES) { // Dark Oak -> Mangrove Propagule 3%
            val chance = UtilMath.random(1, 100)
            if (chance <= 3) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.MANGROVE_PROPAGULE))
            }
        }
        if (block.type == Material.MANGROVE_PROPAGULE) { // Mangrove Propagule -> Cherry 3%
            val chance = UtilMath.random(1, 100)
            if (chance <= 3) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.CHERRY_SAPLING))
            }
        }
        if (block.type == Material.CHERRY_LEAVES) { // Cherry -> Oak 3%
            val chance = UtilMath.random(1, 100)
            if (chance <= 3) {
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.OAK_LEAVES))
            }
        }
    }
}