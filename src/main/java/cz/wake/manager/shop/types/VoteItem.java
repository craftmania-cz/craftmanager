package cz.wake.manager.shop.types;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class VoteItem {

    private String name = "§cError";
    private ArrayList<String> lore = new ArrayList<>();
    private int price = 0;
    private ArrayList<String> permisions = new ArrayList<>();
    private ItemStack itemStack = new ItemBuilder(Material.MUSHROOM_STEM).setName("§cERROR").hideAllFlags().build();
    private RewardType rewardType = RewardType.CRAFTCOINS;
    private String commandToExecute = null;
    private int economyValue = 0; // CraftCoins, CraftTokens co hráč obdrží
    private int requiredLevel = 1;
    private boolean hideWhenBuy = false;
    private String hideWhenBuyPermission = "";
    private int timed = 0;

    public VoteItem() {
    }

    public String getName() {
        return name;
    }

    public VoteItem setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public VoteItem setLore(String... lore) {
        Collections.addAll(this.lore, lore);
        return this;
    }

    public int getPrice() {
        return price;
    }

    public VoteItem setPrice(int price) {
        this.price = price;
        return this;
    }

    public ArrayList<String> getPermisions() {
        return permisions;
    }

    public VoteItem setPermisions(String... permisions) {
        Collections.addAll(this.permisions, permisions);
        this.rewardType = RewardType.PERMISSION;
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public VoteItem setItemStack(Material material) {
        this.itemStack = new ItemBuilder(material).build();
        return this;
    }

    public VoteItem setItemStack(ItemStack itemStack) {
        this.itemStack = new ItemBuilder(itemStack).build();
        return this;
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public VoteItem setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
        return this;
    }

    public String getCommandToExecute() {
        return commandToExecute;
    }

    public VoteItem setCommandToExecute(String commandToExecute) {
        this.commandToExecute = commandToExecute;
        this.rewardType = RewardType.COMMAND;
        return this;
    }

    public int getEconomyReward() {
        return economyValue;
    }

    public VoteItem setEconomyReward(int economyValue, RewardType rewardType) {
        this.economyValue = economyValue;
        this.rewardType = rewardType;
        return this;
    }

    public int getTimed() {
        return timed;
    }

    public VoteItem setTimed(int timed) {
        this.timed = timed;
        return this;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public VoteItem setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
        return this;
    }

    public VoteItem setHideWhenBuy(String hideWhenBuyPermission) {
        this.hideWhenBuyPermission = hideWhenBuyPermission;
        this.hideWhenBuy = true;
        return this;
    }

    public boolean getHideWhenBuy() {
        return this.hideWhenBuy;
    }

    public String getHideWhenBuyPermission() {
        return hideWhenBuyPermission;
    }
}
