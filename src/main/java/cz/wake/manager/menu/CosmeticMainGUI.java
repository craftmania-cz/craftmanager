package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CosmeticMainGUI implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        if (Main.isCraftPackEnabled()) {
            contents.set(2, 1,
                    ClickableItem.of(new ItemBuilder(Material.DIAMOND_HELMET).setName("§bHats").setLore("§7Seznam všech čepic nebo klobouků,", "§7které vlasníš a můžeš si nasadit", "§7na hlavu a mít tak flex!", "", "§eKlikni pro zobrazení").hideAllFlags().build(), click -> {
                        SmartInventory.builder().provider(new HatsGUI()).title("Hats").size(6, 9).build().open(player);
                    }));
        } else {
            contents.set(2, 1, ClickableItem.empty(new ItemBuilder(Material.BARRIER).setName("§cHats").setLore("§7Deaktivováno na tomto serveru.").build()));
        }

        if (Main.isLibsDisguiseEnabled()) {
            contents.set(2, 3,
                    ClickableItem.of(new ItemBuilder(Material.CREEPER_HEAD).setName("§aMorphy").setLore("§7Proměň se na monstrum nebo zvíře", "§7a nikdo tě nepozná!", "", "§fMorphy jsou limitované a dají se získat", "§fpouze při speciálních událostech", "", "§eKlikni pro zobrazení").build(), click -> {
                        SmartInventory.builder().provider(new DisguiseGUI()).title("Morphy").size(6, 9).build().open(player);
                    }));
        } else {
            contents.set(2, 3, ClickableItem.empty(new ItemBuilder(Material.BARRIER).setName("§cMorphy").setLore("§7Deaktivováno na tomto serveru.").build()));
        }

        contents.set(2, 5,
                ClickableItem.of(new ItemBuilder(Material.HONEYCOMB).setName("§6Particles (VIP)").setLore("§7Efekty na postavu,", "§7které se dají odemknout pouze", "§7nákupem VIP na serveru.", "", "§eKlikni pro zobrazení").build(), item -> {
                    Main.getInstance().getParticlesAPI().openParticlesMenu(player); //TODO: Předělat na inventory
                }));

        contents.set(2, 7,
                ClickableItem.of(new ItemBuilder(Material.WHEAT).setName("§dParticles (Special)").setLore("§7Efekty na posavu,", "§7které jsou limitované", "§7a dají se získat pouze při", "§7speciálních eventech a událostech.", "", "§eKlikni pro zobrazení").build(), item -> {
                    SmartInventory.builder().size(6, 9).title("Particles (Special)").provider(new ParticlesSpecialGUI()).build().open(player);
                }));

        contents.set(3, 6,
                ClickableItem.of(new ItemBuilder(Material.BLUE_DYE).setName("§9Particles (Cape)").setLore("§7Speciální capes z particles", "§7přímo ušité na tvojí", "§7postavu... zkus nějaké!", "", "§eKlikni pro zobrazení").build(), item -> {
                    SmartInventory.builder().size(6, 9).title("Particles (Cape)").provider(new ParticlesCapesGUI()).build().open(player);
                }));

        contents.set(3, 2,
                ClickableItem.empty(new ItemBuilder(Material.BARRIER).setName("§cWings").setLore("§7Připravujeme...").build()));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
