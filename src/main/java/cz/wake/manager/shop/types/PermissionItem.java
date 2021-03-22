package cz.wake.manager.shop.types;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class PermissionItem {

    private String name = "§cError";
    private ArrayList<String> lore = new ArrayList<>();
    private int price = 0;
    private int requiredLevel = 0;
    private String permision = null;
    private ItemStack itemStack = new ItemBuilder(Material.MUSHROOM_STEM).setName("§cERROR").hideAllFlags().build();

    public PermissionItem() {}

    public String getName() {
        return name;
    }

    public PermissionItem setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public PermissionItem setLore(String... lore) {
        Collections.addAll(this.lore, lore);
        return this;
    }

    public int getPrice() {
        return price;
    }

    public PermissionItem setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getPermision() {
        return permision;
    }

    public PermissionItem setPermision(String permision) {
        this.permision = permision;
        return this;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public PermissionItem setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public PermissionItem setItemStack(Material material) {
        this.itemStack = new ItemBuilder(material).build();
        return this;
    }

    public PermissionItem setItemStack(ItemStack itemStack) {
        this.itemStack = new ItemBuilder(itemStack).build();
        return this;
    }
}
