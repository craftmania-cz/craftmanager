package cz.wake.manager.utils;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.wake.manager.Main;
import cz.wake.manager.servers.skycloud.CraftPotion;
import cz.wake.manager.servers.skycloud.VillagerManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class CustomCrafting {

    public static NamespacedKey KEY_IS_INVISIBLE = new NamespacedKey(Main.getInstance(), "invisible");

    /**
     * Přidá recept na vycraftění písku z mořských bloků.
     */
    public static Recipe getSandCrafting() {
        ItemStack sand = new ItemStack(Material.SAND, 2);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_sand"), sand);
        recipe.shape("dbd", "aca", "aaa");
        recipe.setIngredient('d', Material.KELP);
        recipe.setIngredient('b', Material.SEAGRASS);
        recipe.setIngredient('a', Material.GUNPOWDER);
        recipe.setIngredient('c', Material.COBBLESTONE);
        return recipe;
    }

    /**
     * Přidá recept na vypečení 1x Brick za 1x Nether Brick az dobu 10s.
     */
    public static Recipe getNetherBrickRecipe() {
        ItemStack netherBrick = new ItemStack(Material.NETHER_BRICK, 1);
        return new FurnaceRecipe(new NamespacedKey(Main.getInstance(), "furnace_nether_brick"), netherBrick, Material.BRICK, 0.0F, 400);
    }

    public static Recipe getGravelCutterRecipe() {
        ItemStack gravel = new ItemStack(Material.GRAVEL, 1);
        return new StonecuttingRecipe(new NamespacedKey(Main.getInstance(), "stone_cutter_gravel"), gravel, Material.COBBLESTONE);
    }

    public static Recipe getShulkerShellRecipe() {
        ItemStack shell = new ItemStack(Material.SHULKER_SHELL, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_shulker_shell"), shell);
        recipe.shape("aaa", "aba", "aaa");
        recipe.setIngredient('a', Material.POPPED_CHORUS_FRUIT);
        recipe.setIngredient('b', Material.NAUTILUS_SHELL);
        return recipe;
    }

    public static Recipe getDiamondRecipe() {
        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_diamond"), diamond);
        recipe.shape("aaa","aaa","aaa");
        recipe.setIngredient('a', Material.EMERALD_BLOCK);
        return recipe;
    }

    public static Recipe getClayBlockRecipe() {
        ItemStack block = new ItemStack(Material.CLAY, 4);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_clay_block"), block);
        recipe.shape("aba", "bcb", "bdb");
        recipe.setIngredient('a', Material.GUNPOWDER);
        recipe.setIngredient('b', new RecipeChoice.MaterialChoice(Material.WHITE_DYE, Material.GRAVEL));
        recipe.setIngredient('c', Material.SAND);
        recipe.setIngredient('d', Material.WATER_BUCKET);
        return recipe;
    }

    public static Recipe getRedSandRecipe() {
        ItemStack block = new ItemStack(Material.RED_SAND, 4);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_red_sand"), block);
        recipe.shape("aba", "bab", "aba");
        recipe.setIngredient('a', Material.SAND);
        recipe.setIngredient('b', Material.BRICK);
        return recipe;
    }

    public static Recipe getSaddleRecipe() {
        ItemStack block = new ItemStack(Material.SADDLE, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_saddle"), block);
        recipe.shape("xax", "axa", "bxb");
        recipe.setIngredient('a', Material.LEATHER);
        recipe.setIngredient('b', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('x', Material.AIR);
        return recipe;
    }

    public static Recipe getCoarseDirtRecipe() {
        ItemStack block = new ItemStack(Material.COARSE_DIRT, 2);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_coarse_dir"), block);
        recipe.shape("abx", "bax", "xxx");
        recipe.setIngredient('a', Material.GRAVEL);
        recipe.setIngredient('b', Material.DIRT);
        recipe.setIngredient('x', Material.AIR);
        return recipe;
    }

    public static Recipe getEndCrystalRecipe() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_end_crystal"), item);
        recipe.shape("aba", "cdc", "cec");
        recipe.setIngredient('a', Material.GLASS);
        recipe.setIngredient('b', Material.ENDER_EYE);
        recipe.setIngredient('c', Material.AMETHYST_SHARD);
        recipe.setIngredient('d', Material.DIAMOND);
        recipe.setIngredient('e', Material.CRYING_OBSIDIAN);
        return recipe;
    }

    public static Recipe getInvisibleItemFrame() {
        ItemStack item = new ItemStack(Material.ITEM_FRAME);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§eNeviditelný Item Frame");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Tento item frame neukazuje");
        lore.add("§7okraje při položení.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(KEY_IS_INVISIBLE, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        item.setAmount(8);

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "invisible_item_frame");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("aaa", "aba", "aaa");
        recipe.setIngredient('a', Material.ITEM_FRAME);
        recipe.setIngredient('b', new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.INVISIBILITY, false, false).getItemStack()).build().getType());
        return recipe;
    }


}
