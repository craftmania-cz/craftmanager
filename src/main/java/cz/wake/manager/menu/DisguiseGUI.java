package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.spigot.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.spigot.inventory.builder.content.SlotIterator;
import cz.craftmania.craftlibs.utils.ChatInfo;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
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
                MobDisguise localDisguise = new MobDisguise(cosmeticItem.getDisguiseType());
                DisguiseAPI.undisguiseToAll(player);
                FlagWatcher localLivingWatcher = localDisguise.getWatcher();
                localLivingWatcher.setCustomName(player.getName());
                localLivingWatcher.setCustomNameVisible(true);
                player.getOpenInventory().close();
                DisguiseAPI.disguiseToAll(player, localDisguise);
                ChatInfo.INFO.send(player, "Přeměnil jsi se na: " + cosmeticItem.getName());
            }));

        }));

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(18);

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

        contents.set(5, 1,ClickableItem.of(new ItemBuilder(Material.SPECTRAL_ARROW).setName("§eZpět do menu").hideAllFlags().build(), item -> {
            SmartInventory.builder().size(6, 9).title("Cosmetics Menu").provider(new CosmeticMainGUI()).build().open(player);
        }));

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

