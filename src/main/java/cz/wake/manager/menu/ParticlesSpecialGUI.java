package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.particles.special.FireWalk;
import cz.wake.manager.perks.particles.special.SantaHat;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ParticlesSpecialGUI implements InventoryProvider {

    private final SantaHat santaHat = new SantaHat();
    private final FireWalk fireFly = new FireWalk();
    private final ParticlesAPI particlesAPI = new ParticlesAPI();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.PINK_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.PINK_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));

        if (player.hasPermission("craftmanager.particles.special.christmashat")) {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.SNOWBALL).setName("§c§lChristmas Hat").setLore("§7Speciální vánoční čepice!", "", "§aKlikni k aktivaci!").build(), e -> {
                santaHat.activateHat(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lChristmas Hat").setLore("§7Lze získat pomocí Vánoční Bundle 2019").build(), e -> { }));
        }

        if (player.hasPermission("craftmanager.particles.special.fire_walk")) {
            contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.ORANGE_TERRACOTTA).setName("§6§lFireWalk").setLore("§7Zapálené nohy od hořícího písku!", "", "§aKlikni k aktivaci!").build(), e -> {
                fireFly.activate(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lFireWalk").setLore("§7Lze získat zakoupením SummerSplash klíče!").build(), e -> { }));
        }

        contents.set(5, 1,ClickableItem.of(new ItemBuilder(Material.SPECTRAL_ARROW).setName("§eZpět do menu").hideAllFlags().build(), item -> {
            SmartInventory.builder().size(6, 9).title("Cosmetics Menu").provider(new CosmeticMainGUI()).build().open(player);
        }));

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lDeaktivovat").build(), e -> {
            particlesAPI.deactivateParticles(player);
            particlesAPI.deaktivateCapes(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
