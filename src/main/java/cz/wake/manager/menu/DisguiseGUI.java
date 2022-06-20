package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.inventory.builder.content.SlotIterator;
import cz.craftmania.craftlibs.utils.ChatInfo;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.*;
import me.libraryaddict.disguise.disguisetypes.watchers.CatWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SheepWatcher;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisguiseGUI implements InventoryProvider {

    public List<CosmeticItem> prepareList() {
        List<CosmeticItem> list = new ArrayList<>();

        list.add(new CosmeticItem().setName("§e§lMinecart")
                .setItemStack(Material.MINECART)
                .setLore("§7Kliknutím se změníš na Minecart.")
                .setDisguiseType(DisguiseType.MINECART)
                .setRequiredPermission("craftmanager.disguise.minecart"));

        list.add(new CosmeticItem().setName("§6§lBlaze")
                .setItemStack(Material.BLAZE_POWDER)
                .setLore("§7Kliknutím se změníš na Blaze.")
                .setDisguiseType(DisguiseType.BLAZE)
                .setRequiredPermission("craftmanager.disguise.blaze"));

        list.add(new CosmeticItem().setName("§cSkeleton")
                .setItemStack(Material.BONE)
                .setLore("§7Kliknutím se změníš na Skeletona.")
                .setDisguiseType(DisguiseType.SKELETON)
                .setRequiredPermission("craftmanager.disguise.skeleton"));

        list.add(new CosmeticItem().setName("§eIron Golem")
                .setItemStack(Material.IRON_INGOT)
                .setLore("§7Kliknutím se změníš na Iron Golema.")
                .setDisguiseType(DisguiseType.IRON_GOLEM)
                .setRequiredPermission("craftmanager.disguise.iron_golem"));

        list.add(new CosmeticItem().setName("§6Husk")
                .setItemStack(Material.SAND)
                .setLore("§8Kliknutím se změníš na Zombie: Husk.")
                .setDisguiseType(DisguiseType.HUSK)
                .setRequiredPermission("craftmanager.disguise.husk"));

        list.add(new CosmeticItem().setName("§9Enderman")
                .setItemStack(Material.ENDER_PEARL)
                .setLore("§8Kliknutím se změníš na Endermana.")
                .setDisguiseType(DisguiseType.ENDERMAN)
                .setRequiredPermission("craftmanager.disguise.enderman"));

        list.add(new CosmeticItem().setName("§cSheep: Red")
                .setItemStack(Material.RED_WOOL)
                .setLore("§8Kliknutím se změníš na Sheep.")
                .setDisguiseType(DisguiseType.SHEEP)
                .setSheepColor(DyeColor.RED)
                .setRequiredPermission("craftmanager.disguise.sheep.red"));

        list.add(new CosmeticItem().setName("§cSheep: Red [Baby]")
                .setItemStack(Material.RED_WOOL)
                .setLore("§8Kliknutím se změníš na Sheep.")
                .setDisguiseType(DisguiseType.SHEEP)
                .setBabyType()
                .setSheepColor(DyeColor.RED)
                .setRequiredPermission("craftmanager.disguise.sheep.red"));

        list.add(new CosmeticItem().setName("§cCat: Black")
                .setItemStack(Material.BLACK_CARPET)
                .setLore("§8Kliknutím se změníš na Cat.")
                .setDisguiseType(DisguiseType.CAT)
                .setCatType(Cat.Type.BLACK)
                .setRequiredPermission("craftmanager.disguise.cat.black"));

        list.add(new CosmeticItem().setName("§cCat: Black [Baby]")
                .setItemStack(Material.BLACK_CARPET)
                .setLore("§8Kliknutím se změníš na Cat.")
                .setDisguiseType(DisguiseType.CAT)
                .setBabyType()
                .setCatType(Cat.Type.BLACK)
                .setRequiredPermission("craftmanager.disguise.cat.black"));

        list.add(new CosmeticItem().setName("§dStray")
                .setItemStack(Material.SPECTRAL_ARROW)
                .setLore("§8Kliknutím se změníš na Straye.")
                .setDisguiseType(DisguiseType.STRAY)
                .setRequiredPermission("craftmanager.disguise.stray"));

        list.add(new CosmeticItem().setName("§aCreeper")
                .setItemStack(Material.CREEPER_HEAD)
                .setLore("§8Kliknutím se změníš na Creepera.")
                .setDisguiseType(DisguiseType.CREEPER)
                .setRequiredPermission("craftmanager.disguise.creeper"));

        list.add(new CosmeticItem().setName("§aCreeper [Powered]")
                .setItemStack(Material.CREEPER_HEAD)
                .setLore("§8Kliknutím se změníš na Creepera.")
                .setDisguiseType(DisguiseType.CREEPER)
                .setPowered(true)
                .setRequiredPermission("craftmanager.disguise.creeper.powered"));

        list.add(new CosmeticItem().setName("§aZombie")
                .setItemStack(Material.ZOMBIE_HEAD)
                .setLore("§8Kliknutím se změníš na Zombie.")
                .setDisguiseType(DisguiseType.ZOMBIE)
                .setRequiredPermission("craftmanager.disguise.zombie"));

        list.add(new CosmeticItem().setName("§aZombie Horse")
                .setItemStack(Material.ZOMBIE_HEAD)
                .setLore("§8Kliknutím se změníš na Zombie Horse.")
                .setDisguiseType(DisguiseType.ZOMBIE_HORSE)
                .setRequiredPermission("craftmanager.disguise.zombie_horse"));

        list.add(new CosmeticItem().setName("§aZombie Villager")
                .setItemStack(Material.ZOMBIE_HEAD)
                .setLore("§8Kliknutím se změníš na Zombie Villager.")
                .setDisguiseType(DisguiseType.ZOMBIE_VILLAGER)
                .setRequiredPermission("craftmanager.disguise.zombie_villager"));

        list.add(new CosmeticItem().setName("§eGuardian")
                .setItemStack(Material.PRISMARINE_CRYSTALS)
                .setLore("§8Kliknutím se změníš na Guardiana.")
                .setDisguiseType(DisguiseType.GUARDIAN)
                .setRequiredPermission("craftmanager.disguise.guardian.normal"));

        list.add(new CosmeticItem().setName("§eGuardian [Elder]")
                .setItemStack(Material.PRISMARINE_CRYSTALS)
                .setLore("§8Kliknutím se změníš na Guardiana.")
                .setDisguiseType(DisguiseType.ELDER_GUARDIAN)
                .setRequiredPermission("craftmanager.disguise.guardian.elder"));

        list.add(new CosmeticItem().setName("§bSnowman")
                .setItemStack(Material.SNOW_BLOCK)
                .setLore("§8Kliknutím se změníš na Sněhuláka.")
                .setDisguiseType(DisguiseType.SNOWMAN)
                .setRequiredPermission("craftmanager.disguise.snowman"));

        list.add(new CosmeticItem().setName("§6Chicken")
                .setItemStack(Material.EGG)
                .setLore("§8Kliknutím se změníš na Chicken.")
                .setDisguiseType(DisguiseType.CHICKEN)
                .setRequiredPermission("craftmanager.disguise.chicken"));

        list.add(new CosmeticItem().setName("§cStrider")
                .setItemStack(Material.STRING)
                .setLore("§8Kliknutím se změníš na Strider.")
                .setDisguiseType(DisguiseType.STRIDER)
                .setRequiredPermission("craftmanager.disguise.strider"));

        list.add(new CosmeticItem().setName("§cFox")
                .setItemStack(Material.COOKED_CHICKEN)
                .setLore("§8Kliknutím se změníš na Fox.")
                .setDisguiseType(DisguiseType.FOX)
                .setRequiredPermission("craftmanager.disguise.fox"));

        list.add(new CosmeticItem().setName("§eCow")
                .setItemStack(Material.BEEF)
                .setLore("§8Kliknutím se změníš na Cow.")
                .setDisguiseType(DisguiseType.COW)
                .setRequiredPermission("craftmanager.disguise.cow"));

        list.add(new CosmeticItem().setName("§eGoat")
                .setItemStack(Material.HORN_CORAL)
                .setLore("§8Kliknutím se změníš na Goat.")
                .setDisguiseType(DisguiseType.GOAT)
                .setRequiredPermission("craftmanager.disguise.goat"));

        list.add(new CosmeticItem().setName("§cMagma Slime")
                .setItemStack(Material.LAVA_BUCKET)
                .setLore("§8Kliknutím se změníš na Magma Slime.")
                .setDisguiseType(DisguiseType.MAGMA_CUBE)
                .setRequiredPermission("craftmanager.disguise.magma_slime"));

        list.add(new CosmeticItem().setName("§e§lMinecart [Chest]")
                .setItemStack(Material.CHEST_MINECART)
                .setLore("§7Kliknutím se změníš na Minecart s truhlou.")
                .setDisguiseType(DisguiseType.MINECART_CHEST)
                .setRequiredPermission("craftmanager.disguise.minecart_chest"));

        list.add(new CosmeticItem().setName("§cSheep: Yellow")
                .setItemStack(Material.YELLOW_WOOL)
                .setLore("§8Kliknutím se změníš na Sheep.")
                .setDisguiseType(DisguiseType.SHEEP)
                .setSheepColor(DyeColor.YELLOW)
                .setRequiredPermission("craftmanager.disguise.sheep.yellow"));

        list.add(new CosmeticItem().setName("§cSheep: Red [Baby]")
                .setItemStack(Material.YELLOW_WOOL)
                .setLore("§8Kliknutím se změníš na Sheep.")
                .setDisguiseType(DisguiseType.SHEEP)
                .setBabyType()
                .setSheepColor(DyeColor.YELLOW)
                .setRequiredPermission("craftmanager.disguise.sheep.yellow"));

        list.add(new CosmeticItem().setName("§bDrowned")
                .setItemStack(Material.ZOMBIE_HEAD)
                .setLore("§8Kliknutím se změníš na Strider.")
                .setDisguiseType(DisguiseType.DROWNED)
                .setRequiredPermission("craftmanager.disguise.drowned"));

        return list;
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        List<CosmeticItem> disguises = prepareList();

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        disguises.forEach((cosmeticItem -> {

            if (!player.hasPermission(cosmeticItem.getRequiredPermission())) {
                return;
            }


            if (cosmeticItem.getDisguiseType() == DisguiseType.MINECART) {
                items.add(ClickableItem.of(new ItemBuilder(cosmeticItem.getItemStack()).setName("§b" + cosmeticItem.getName()).setLore(cosmeticItem.getLore()).hideAllFlags().build(), click -> {
                    MiscDisguise localDisguise = new MiscDisguise(cosmeticItem.getDisguiseType());
                    DisguiseAPI.undisguiseToAll(player);
                    FlagWatcher localLivingWatcher = localDisguise.getWatcher();
                    localLivingWatcher.setCustomName(player.getName());
                    localLivingWatcher.setCustomNameVisible(true);
                    player.getOpenInventory().close();
                    DisguiseAPI.disguiseToAll(player, localDisguise);
                    ChatInfo.INFO.send(player, "Přeměnil jsi se na: " + cosmeticItem.getName());
                }));
                return;
            }

            items.add(ClickableItem.of(new ItemBuilder(cosmeticItem.getItemStack()).setName("§b" + cosmeticItem.getName()).setLore(cosmeticItem.getLore()).hideAllFlags().build(), click -> {
                if (cosmeticItem.getDisguiseType() == DisguiseType.MINECART_CHEST) {
                    MiscDisguise miscDisguise = new MiscDisguise(cosmeticItem.getDisguiseType());
                    DisguiseAPI.undisguiseToAll(player);
                    FlagWatcher localLivingWatcher = miscDisguise.getWatcher();
                    localLivingWatcher.setCustomName(player.getName());
                    localLivingWatcher.setCustomNameVisible(true);
                    player.getOpenInventory().close();
                    DisguiseAPI.disguiseToAll(player, miscDisguise);
                    ChatInfo.INFO.send(player, "Přeměnil jsi se na: " + cosmeticItem.getName());
                    return;
                }
                MobDisguise localDisguise = new MobDisguise(cosmeticItem.getDisguiseType(), cosmeticItem.isBabyType());
                DisguiseAPI.undisguiseToAll(player);
                FlagWatcher localLivingWatcher = localDisguise.getWatcher();
                localLivingWatcher.setCustomName(player.getName());
                localLivingWatcher.setCustomNameVisible(true);
                if (cosmeticItem.getDisguiseType() == DisguiseType.SHEEP) {
                    SheepWatcher sheepWatcher = (SheepWatcher) localLivingWatcher;
                    sheepWatcher.setColor(cosmeticItem.getSheepColor());
                }
                if (cosmeticItem.getDisguiseType() == DisguiseType.CAT) {
                    assert localLivingWatcher instanceof CatWatcher;
                    CatWatcher catWatcher = (CatWatcher) localLivingWatcher;
                    catWatcher.setType(cosmeticItem.getCatType());
                }
                if (cosmeticItem.getDisguiseType() == DisguiseType.CREEPER) {
                    CreeperWatcher creeperWatcher = (CreeperWatcher) localLivingWatcher;
                    creeperWatcher.setPowered(cosmeticItem.isPowered());
                }
                player.getOpenInventory().close();
                DisguiseAPI.disguiseToAll(player, localDisguise);
                ChatInfo.INFO.send(player, "Přeměnil jsi se na: " + cosmeticItem.getName());
            }));

        }));

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(36);

        if (items.size() > 0 && !pagination.isLast()) {
            contents.set(5, 7, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lDalší stránka").build(), e -> {
                contents.inventory().open(player, pagination.next().getPage());
            }));
        }
        if (!pagination.isFirst()) {
            contents.set(5, 2, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lPředchozí stránka").build(), e -> {
                contents.inventory().open(player, pagination.previous().getPage());
            }));
        }

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§cDeaktivovat").build(), e -> {
            DisguiseAPI.undisguiseToAll(player);
            player.getOpenInventory().close();
            ChatInfo.SUCCESS.send(player, "Přeměna byla zrušena.");
        }));

        SlotIterator slotIterator = contents.newIterator("disguise-gui", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);

    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}

