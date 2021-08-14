package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.particles.capes.ChristmasCape;
import cz.wake.manager.perks.particles.capes.SpookyCape;
import cz.wake.manager.perks.particles.capes.SummerSplash;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ParticlesCapesGUI implements InventoryProvider {

    ChristmasCape christmasCape = new ChristmasCape();
    ParticlesAPI particlesAPI = new ParticlesAPI();
    SummerSplash summerSplash = new SummerSplash();
    SpookyCape spookyCape = new SpookyCape();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));

        if (player.hasPermission("craftmanager.particles.cape.christmas")) {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.REDSTONE_BLOCK).setName("§c§lChristmas Capes").setLore("§7Speciální vánoční cape!", "", "§aKlikni k aktivaci!").build(), e -> {
                particlesAPI.deaktivateCapes(player);
                christmasCape.activate(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§4§lChristmas Cape").setLore("§7Lze získat pomocí Vánoční Bundle 2019").build(), e -> { }));
        }

        if (player.hasPermission("craftmanager.particles.cape.summer_splash")) {
            contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.NETHER_STAR).setName("§6§lSummer§e§lSplash §fCape").setLore("§7Speciální letní Cape!", "", "§aKlikni k aktivaci!").build(), e -> {
                particlesAPI.deaktivateCapes(player);
                summerSplash.activate(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lSummerSplash Cape").setLore("§7Lze získat pouze pomocí", "§7nákupu SummerSplash 2020 klíče.").build(), e -> { }));
        }

        if (player.hasPermission("craftmanager.particles.cape.spooky_cape")) {
            contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.BONE).setName("§8§lSpooky §f§lCape").setLore("§7Speciální Halloween Cape", "", "§aKlikni k aktivaci!").build(), e -> {
                particlesAPI.deaktivateCapes(player);
                spookyCape.activate(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lSpooky Cape").setLore("§7Lze získat pouze pomocí", "§7nákupu Halloween 2020 klíče.").build(), e -> { }));
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
