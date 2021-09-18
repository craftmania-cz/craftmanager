package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

class CosmeticItem {

    private String name = "§cError";
    private ArrayList<String> lore = new ArrayList<>();
    private ItemStack itemStack = new ItemBuilder(Material.MUSHROOM_STEM).setName("§cERROR").hideAllFlags().build();
    private String commandToExecute = null;
    private String requiredPermission = null;
    private DisguiseType disguiseType = null;
    private boolean babyType = true; // true = false WTF
    private DyeColor sheepColor = DyeColor.WHITE;
    private Cat.Type catType = Cat.Type.BLACK;
    private boolean isPowered = false;

    public CosmeticItem() {};

    public String getName() {
        return name;
    }

    public CosmeticItem setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public CosmeticItem setLore(String... lore) {
        Collections.addAll(this.lore, lore);
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public CosmeticItem setItemStack(Material material) {
        this.itemStack = new ItemBuilder(material).build();
        return this;
    }

    public CosmeticItem setItemStack(Material material, int contentId) {
        this.itemStack = new ItemBuilder(material).setCustomModelData(contentId).build();
        return this;
    }

    public CosmeticItem setItemStack(ItemStack itemStack) {
        this.itemStack = new ItemBuilder(itemStack).build();
        return this;
    }

    public String getCommandToExecute() {
        return commandToExecute;
    }

    public CosmeticItem setCommandToExecute(String commandToExecute) {
        this.commandToExecute = commandToExecute;
        return this;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }

    public CosmeticItem setRequiredPermission(String requiredPermission) {
        this.requiredPermission = requiredPermission;
        return this;
    }

    public DisguiseType getDisguiseType() {
        return disguiseType;
    }

    public CosmeticItem setDisguiseType(DisguiseType disguiseType) {
        this.disguiseType = disguiseType;
        return this;
    }

    public CosmeticItem setBabyType() {
        this.babyType = false;
        return this;
    }

    public boolean isBabyType() {
        return babyType;
    }

    public CosmeticItem setSheepColor(DyeColor color) {
        this.sheepColor = color;
        return this;
    }

    public DyeColor getSheepColor() {
        return sheepColor;
    }

    public CosmeticItem setCatType(Cat.Type type) {
        this.catType = type;
        return this;
    }

    public Cat.Type getCatType() {
        return catType;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public CosmeticItem setPowered(boolean powered) {
        isPowered = powered;
        return this;
    }
}
