package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@CommandAlias("beacon")
@Description("Otevře ti menu pro beacon")
public class BeaconCommand extends BaseCommand implements Listener {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lBeacon commands:");
        help.showHelp();
    }

    @Default
    public void onCommand(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (player.hasPermission("craftmanager.vip.beacon")) {

                if (Main.getInstance().getServerType() == ServerType.VANILLA || Main.getInstance().getServerType() == ServerType.ANARCHY) {
                    ChatInfo.DANGER.send(player, "Na tomto serveru tato výhoda neplatí.");
                    return;
                }

                Inventory inv = Bukkit.createInventory(null, InventoryType.DISPENSER, "Vyber si potion efekt");

                inv.setItem(0, new ItemBuilder(Material.FEATHER).setName("§f§lSpeed").build());
                inv.setItem(1, new ItemBuilder(Material.GOLDEN_PICKAXE).setName("§e§lHaste").build());
                inv.setItem(2, new ItemBuilder(Material.IRON_BOOTS).setName("§a§lJump Boost").build());
                inv.setItem(3, new ItemBuilder(Material.BLAZE_POWDER).setName("§6§lFire Resistance").build());
                inv.setItem(4, new ItemBuilder(Material.ENDER_EYE).setName("§9§lNight Vision").build());
                inv.setItem(5, new ItemBuilder(Material.PRISMARINE_CRYSTALS).setName("§3§lWater Breathing").build());

                inv.setItem(7, new ItemBuilder(Material.BARRIER).setName("§c§lZrušit").setLore("§7Kliknutím efekt deaktivuješ").build());

                player.openInventory(inv);
            } else {
                ChatInfo.DANGER.send(player, "K získání přístupu potřebuješ mít minimálně Gold VIP.");
            }
        }
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Vyber si potion efekt")) {
            if(e.getSlot() == 0){
                activateEffect(player, PotionEffectType.SPEED, 2);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bSpeed!");
            }
            if(e.getSlot() == 1){
                activateEffect(player, PotionEffectType.FAST_DIGGING, 2);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bHaste!");
            }
            if(e.getSlot() == 2){
                activateEffect(player, PotionEffectType.JUMP, 3);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bJump Boost!");
            }
            if(e.getSlot() == 3){
                activateEffect(player, PotionEffectType.FIRE_RESISTANCE, 3);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bFire Resistance!");
            }
            if(e.getSlot() == 4){
                activateEffect(player, PotionEffectType.NIGHT_VISION, 5);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bNight Vision!");
            }
            if(e.getSlot() == 5){
                activateEffect(player, PotionEffectType.WATER_BREATHING, 3);
                ChatInfo.SUCCESS.send(player, "Aktivoval jsi permanentní §bWater Breathing!");
            }
            if(e.getSlot() == 7){
                player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);
                ChatInfo.SUCCESS.send(player, "Odebral jsi všechny aktivní efekty!");
                player.closeInventory();
            }
        }
    }

    private void activateEffect(Player p, PotionEffectType effect, int amlifier){
        p.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(p::removePotionEffect);
        p.addPotionEffect(new PotionEffect(effect, 12000000, amlifier));
        p.closeInventory();
    }
}
