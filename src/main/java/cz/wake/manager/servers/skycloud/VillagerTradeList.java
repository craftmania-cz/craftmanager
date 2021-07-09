package cz.wake.manager.servers.skycloud;

import com.cryptomorin.xseries.XEnchantment;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.builders.villager.VillagerTradeBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.Random;

public class VillagerTradeList {

    public VillagerTradeBuilder generateSellVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(new ItemStack(Material.OAK_SAPLING, 64), new ItemStack(Material.OAK_SAPLING, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.JUNGLE_SAPLING, 64), new ItemStack(Material.JUNGLE_SAPLING, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.BIRCH_SAPLING, 64), new ItemStack(Material.BIRCH_SAPLING, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.ACACIA_SAPLING, 64), new ItemStack(Material.ACACIA_SAPLING, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.DARK_OAK_SAPLING, 64), new ItemStack(Material.DARK_OAK_SAPLING, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.OAK_WOOD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.ACACIA_WOOD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.BIRCH_WOOD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.JUNGLE_WOOD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.PUMPKIN_PIE, 3), e(1));
        tradeList.addTrade(new ItemStack(Material.TURTLE_EGG, 16), e(3));
        tradeList.addTrade(new ItemStack(Material.ANVIL, 3), e(6));
        tradeList.addTrade(new ItemStack(Material.APPLE, 32), e(1));
        tradeList.addTrade(new ItemStack(Material.BONE, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.COOKED_BEEF, 32), e(1));
        tradeList.addTrade(new ItemStack(Material.HONEY_BLOCK, 16), e(1));
        tradeList.addTrade(new ItemStack(Material.DRIED_KELP_BLOCK, 24), e(1));
        tradeList.addTrade(new ItemStack(Material.CAKE, 1), e(1));
        tradeList.addTrade(new ItemStack(Material.CROSSBOW, 2), e(1));
        tradeList.addTrade(new ItemStack(Material.STONE, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.MAP, 42), e(1));
        tradeList.addTrade(new ItemStack(Material.BREAD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), e(5));
        tradeList.addTrade(new ItemStack(Material.RABBIT_STEW, 1), e(1));
        tradeList.addTrade(new ItemStack(Material.ENDER_EYE, 12), e(1));
        tradeList.addTrade(new ItemStack(Material.COOKED_PORKCHOP, 32), e(1));
        tradeList.addTrade(new ItemStack(Material.CAKE, 1), e(1));
        tradeList.addTrade(new ItemStack(Material.POISONOUS_POTATO, 16), e(1));
        tradeList.addTrade(new ItemStack(Material.COMPASS, 32), e(3));
        tradeList.addTrade(new ItemStack(Material.DARK_OAK_WOOD, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.PACKED_ICE, 16), e(2));
        tradeList.addTrade(new ItemStack(Material.BOOK, 32), e(1));
        tradeList.addTrade(new ItemStack(Material.BAKED_POTATO, 48), e(1));
        tradeList.addTrade(new ItemStack(Material.FIRE_CHARGE, 28), e(3));
        tradeList.addTrade(new ItemStack(Material.SPIDER_EYE, 64), e(1));
        tradeList.addTrade(new ItemStack(Material.GLISTERING_MELON_SLICE, 5), e(1));
        tradeList.addTrade(new ItemStack(Material.SLIME_BLOCK, 64), e(2));
        tradeList.addTrade(new ItemStack(Material.MYCELIUM, 1), e(1));
        tradeList.addTrade(new ItemStack(Material.BROWN_MUSHROOM_BLOCK, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.GOLDEN_CARROT, 24), e(1));
        tradeList.addTrade(new ItemStack(Material.CRACKED_STONE_BRICKS, 24), e(1));
        tradeList.addTrade(new ItemStack(Material.END_CRYSTAL, 1), e(4));
        tradeList.addTrade(new ItemStack(Material.BROWN_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.PINK_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.LIME_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.BLACK_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.RED_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 32), e(2));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SLOW_FALLING, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.JUMP, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.FIRE_RESISTANCE, false, true).getItemStack()).build(), e(2));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SPEED, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.JUMP, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.REGEN, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SPEED, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.INSTANT_DAMAGE, true, false).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SLOW_FALLING, false, true).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.TURTLE_MASTER, false, true).getItemStack()).build(), e(1));
        tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.INVISIBILITY, false, true).getItemStack()).build(), e(1));

        return tradeList;
    }

    public VillagerTradeBuilder generateSeaVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(e(13), new ItemStack(Material.TUBE_CORAL, 3));
        tradeList.addTrade(e(15), new ItemStack(Material.HORN_CORAL_BLOCK, 3));
        tradeList.addTrade(e(13), new ItemStack(Material.SEA_PICKLE, 6));
        tradeList.addTrade(e(18), new ItemStack(Material.GUARDIAN_SPAWN_EGG, 1));
        tradeList.addTrade(e(32), new ItemStack(Material.NAUTILUS_SHELL, 2));
        tradeList.addTrade(e(16), new ItemStack(Material.KELP, 2));
        tradeList.addTrade(e(10), new ItemStack(Material.FERN, 5));
        tradeList.addTrade(e(13), new ItemStack(Material.BRAIN_CORAL, 3));
        tradeList.addTrade(e(15), new ItemStack(Material.FIRE_CORAL_BLOCK, 3));
        tradeList.addTrade(e(17), new ItemStack(Material.SEAGRASS, 3));
        tradeList.addTrade(e(13), new ItemStack(Material.DROWNED_SPAWN_EGG, 1));
        tradeList.addTrade(e(8), new ItemStack(Material.PRISMARINE_CRYSTALS, 8));
        tradeList.addTrade(e(13), new ItemStack(Material.SQUID_SPAWN_EGG, 1));
        tradeList.addTrade(e(13), new ItemStack(Material.BUBBLE_CORAL, 3));
        tradeList.addTrade(e(15), new ItemStack(Material.BRAIN_CORAL_BLOCK, 3));
        tradeList.addTrade(d(64), new ItemStack(Material.TRIDENT, 1));
        tradeList.addTrade(e(8), new ItemStack(Material.PRISMARINE_SHARD, 32));
        tradeList.addTrade(e(13), new ItemStack(Material.FIRE_CORAL, 3));
        tradeList.addTrade(e(64), e(16), new ItemStack(Material.DOLPHIN_SPAWN_EGG, 1));
        tradeList.addTrade(e(15), new ItemStack(Material.TUBE_CORAL_BLOCK, 3));
        tradeList.addTrade(e(4), new ItemStack(Material.LILY_PAD, 10));
        tradeList.addTrade(e(10), new ItemStack(Material.PUFFERFISH_BUCKET));
        tradeList.addTrade(e(13), new ItemStack(Material.HORN_CORAL, 3));
        tradeList.addTrade(e(15), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 3));
        tradeList.addTrade(e(49), new ItemStack(Material.ELDER_GUARDIAN_SPAWN_EGG, 1));
        tradeList.addTrade(d(6), new ItemStack(Material.HEART_OF_THE_SEA, 1));
        tradeList.addTrade(e(10), new ItemStack(Material.TROPICAL_FISH_BUCKET));
        return tradeList;
    }

    public VillagerTradeBuilder generateEndVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(e(10), new ItemStack(Material.END_STONE, 8));
        tradeList.addTrade(e(32), new ItemStack(Material.END_ROD, 1));
        tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_BLOCK, 6));
        tradeList.addTrade(d(2), new ItemStack(Material.WHITE_SHULKER_BOX, 1));
        tradeList.addTrade(d(2), new ItemStack(Material.SHULKER_SPAWN_EGG, 1));
        tradeList.addTrade(e(10), new ItemStack(Material.END_STONE_BRICKS, 2));
        tradeList.addTrade(db(8), new ItemStack(Material.DRAGON_HEAD, 1));
        tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_PILLAR, 4));
        tradeList.addTrade(e(16), new ItemStack(Material.SHULKER_SHELL, 1));
        tradeList.addTrade(e(64), new ItemStack(Material.BLACK_SHULKER_BOX, 1));
        tradeList.addTrade(e(32), new ItemStack(Material.OBSIDIAN, 1));
        tradeList.addTrade(e(64), new ItemStack(Material.BLUE_SHULKER_BOX, 1));
        tradeList.addTrade(e(13), new ItemStack(Material.ENDERMITE_SPAWN_EGG, 1));
        tradeList.addTrade(e(48), new ItemStack(Material.DRAGON_BREATH, 2));
        tradeList.addTrade(e(64), new ItemStack(Material.ORANGE_SHULKER_BOX, 1));
        tradeList.addTrade(e(13), new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1));

        return tradeList;
    }

    public VillagerTradeBuilder generateNetherVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(e(32), new ItemStack(Material.NETHERITE_SCRAP, 1));
        tradeList.addTrade(eb(32), new ItemStack(Material.NETHERITE_INGOT, 1));

        tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
        tradeList.addTrade(e(8), new ItemStack(Material.MAGMA_BLOCK, 2));
        tradeList.addTrade(d(5), new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1));
        tradeList.addTrade(db(1), new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
        tradeList.addTrade(e(8), new ItemStack(Material.LAVA_BUCKET, 1));
        tradeList.addTrade(e(11), new ItemStack(Material.BLAZE_SPAWN_EGG, 1));
        tradeList.addTrade(e(12), new ItemStack(Material.GLOWSTONE_DUST, 4)); // Nebude lepsi prodavat glowstone?
        tradeList.addTrade(e(12), new ItemStack(Material.NETHER_QUARTZ_ORE, 5));
        tradeList.addTrade(e(10), new ItemStack(Material.SOUL_SAND, 2));
        tradeList.addTrade(e(7), new ItemStack(Material.GLOWSTONE, 2));
        tradeList.addTrade(e(4), new ItemStack(Material.BLAZE_ROD, 4));
        tradeList.addTrade(e(7), new ItemStack(Material.NETHER_BRICK, 24));

        tradeList.addTrade(e(10), new ItemStack(Material.CRIMSON_NYLIUM, 4));
        tradeList.addTrade(e(10), new ItemStack(Material.WARPED_NYLIUM, 4));
        tradeList.addTrade(e(3), new ItemStack(Material.CRIMSON_STEM, 1));
        tradeList.addTrade(e(3), new ItemStack(Material.WARPED_STEM, 1));
        tradeList.addTrade(e(2), new ItemStack(Material.CRIMSON_HYPHAE, 1));
        tradeList.addTrade(e(2), new ItemStack(Material.WARPED_HYPHAE, 1));
        tradeList.addTrade(e(1), new ItemStack(Material.BASALT, 2));
        tradeList.addTrade(e(10), new ItemStack(Material.SOUL_SOIL, 2));
        tradeList.addTrade(e(16), new ItemStack(Material.WARPED_WART_BLOCK, 1));
        tradeList.addTrade(d(1), new ItemStack(Material.CRYING_OBSIDIAN, 1));
        tradeList.addTrade(e(4), new ItemStack(Material.BLACKSTONE, 6));
        tradeList.addTrade(e(32), new ItemStack(Material.GILDED_BLACKSTONE, 1));

        return tradeList;
    }

    public VillagerTradeBuilder generateBuyVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(e(4), new ItemStack(Material.PORKCHOP, 12));
        tradeList.addTrade(e(15), new ItemStack(Material.GRASS_BLOCK, 1));
        tradeList.addTrade(e(12), new ItemStack(Material.DIRT));
        tradeList.addTrade(e(44), new ItemStack(Material.SAND, 10));
        tradeList.addTrade(e(8), new ItemStack(Material.NAME_TAG, 1));
        tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
        tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
        tradeList.addTrade(e(16), new ItemStack(Material.IRON_ORE, 3));
        tradeList.addTrade(e(20), new ItemStack(Material.BLUE_ICE, 4));
        tradeList.addTrade(e(32), new ItemStack(Material.GLASS, 8));
        tradeList.addTrade(e(3), new ItemStack(Material.WATER_BUCKET));
        tradeList.addTrade(e(3), new ItemStack(Material.SADDLE, 1));
        tradeList.addTrade(e(6), new ItemStack(Material.SWEET_BERRIES, 1));
        tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
        tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44));
        tradeList.addTrade(e(24), new ItemStack(Material.GOLD_ORE, 3));
        tradeList.addTrade(e(32), new ItemStack(Material.OBSIDIAN, 1));
        tradeList.addTrade(e(44), new ItemStack(Material.RED_SAND, 10));
        tradeList.addTrade(e(10), new ItemStack(Material.SNOW_BLOCK, 10));
        tradeList.addTrade(e(12), new ItemStack(Material.LAPIS_ORE, 8));
        tradeList.addTrade(e(10), new ItemStack(Material.CHISELED_STONE_BRICKS, 18));
        tradeList.addTrade(e(20), new ItemStack(Material.COARSE_DIRT, 8));
        tradeList.addTrade(e(32), XEnchantment.DURABILITY.getBook(1));
        tradeList.addTrade(e(17), XEnchantment.VANISHING_CURSE.getBook(1));
        tradeList.addTrade(e(33), new ItemStack(Material.ZOMBIE_HORSE_SPAWN_EGG, 1));
        tradeList.addTrade(e(66), new ItemStack(Material.RAVAGER_SPAWN_EGG, 1));
        tradeList.addTrade(e(33), new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG, 1));
        tradeList.addTrade(e(24), new ItemStack(Material.SLIME_SPAWN_EGG, 1));
        tradeList.addTrade(e(64), e(2), new ItemStack(Material.PILLAGER_SPAWN_EGG, 1));
        tradeList.addTrade(e(7), new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.BLACK_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.WHITE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.ORANGE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.CYAN_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.RED_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.GREEN_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.PURPLE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.BLUE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.PURPLE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.PURPLE_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.BROWN_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.GREEN_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.YELLOW_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.LIME_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.PINK_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.GRAY_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.MAGENTA_CONCRETE_POWDER, 32));
        tradeList.addTrade(e(7), new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER, 32));

        return tradeList;
    }

    public VillagerTradeBuilder generateRareVillagerShop() {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();

        tradeList.addTrade(e(64), db(64), new ItemStack(Material.ELYTRA, 1));
        tradeList.addTrade(eb(32), new ItemStack(Material.TOTEM_OF_UNDYING, 1));
        tradeList.addTrade(eb(12), new ItemStack(Material.CONDUIT, 1));
        tradeList.addTrade(e(32), new ItemStack(Material.SPECTRAL_ARROW, 12));
        tradeList.addTrade(e(10), new ItemStack(Material.FIREWORK_ROCKET, 32));
        tradeList.addTrade(eb(32), XEnchantment.MENDING.getBook(1));
        tradeList.addTrade(d(2), XEnchantment.DIG_SPEED.getBook(1));
        tradeList.addTrade(eb(3), new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).build());
        tradeList.addTrade(db(4), new ItemStack(Material.END_PORTAL_FRAME));
        tradeList.addTrade(e(32), new ItemStack(Material.WOLF_SPAWN_EGG));
        tradeList.addTrade(d(1), new ItemStack(Material.LECTERN));
        tradeList.addTrade(e(18), new ItemStack(Material.BELL, 1));
        tradeList.addTrade(eb(64), new ItemStack(Material.VILLAGER_SPAWN_EGG));
        tradeList.addTrade(e(42), new ItemStack(Material.EXPERIENCE_BOTTLE, 16));
        tradeList.addTrade(eb(32), new ItemStack(Material.WITHER_ROSE));
        tradeList.addTrade(eb(12), XEnchantment.FROST_WALKER.getBook(1));
        tradeList.addTrade(d(3), XEnchantment.DAMAGE_ALL.getBook(1));
        tradeList.addTrade(e(64), new ItemStack(Material.FOX_SPAWN_EGG));
        tradeList.addTrade(db(3), new ItemStack(Material.ENCHANTING_TABLE, 1));
        tradeList.addTrade(db(1), new ItemStack(Material.DRAGON_BREATH, 4));
        tradeList.addTrade(d(10), XEnchantment.LOOT_BONUS_BLOCKS.getBook(1));
        tradeList.addTrade(e(32), new ItemStack(Material.PANDA_SPAWN_EGG, 1));
        tradeList.addTrade(eb(3), new ItemBuilder(Material.IRON_AXE).addEnchant(Enchantment.DAMAGE_ALL, 2).build());
        tradeList.addTrade(eb(4), new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        tradeList.addTrade(e(32), new ItemStack(Material.PARROT_SPAWN_EGG));
        tradeList.addTrade(e(32), new ItemStack(Material.CAT_SPAWN_EGG));

        return tradeList;
    }

    private ItemStack e(int amount) {
        return new ItemStack(Material.EMERALD, amount);
    }

    private ItemStack eb(int amount) {
        return new ItemStack(Material.EMERALD_BLOCK, amount);
    }

    private ItemStack db(int amount) { return new ItemStack(Material.DIAMOND_BLOCK, amount); }

    private ItemStack d(int amount) {
        return new ItemStack(Material.DIAMOND, amount);
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
