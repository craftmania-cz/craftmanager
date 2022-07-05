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
