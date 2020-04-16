package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.ServerType;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BeaconCommand implements Listener {

    public static void registerCommand() {

        CommandAPI.getInstance().register("beacon", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("craftmanager.vip.beacon")) {

                    if (Main.getServerType() == ServerType.VANILLA) {
                        player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                        return;
                    }

                    Inventory inv = Bukkit.createInventory(null, InventoryType.DISPENSER, "Zvol si efekt");

                    inv.setItem(0, ItemFactory.create(Material.FEATHER, (byte)0, "§f§lSpeed"));
                    inv.setItem(1, ItemFactory.create(Material.GOLDEN_PICKAXE, (byte)0, "§e§lHaste"));
                    inv.setItem(2, ItemFactory.create(Material.IRON_BOOTS, (byte)0, "§a§lJump Boost"));
                    inv.setItem(3, ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§6§lFire Resistance"));
                    inv.setItem(4, ItemFactory.create(Material.ENDER_EYE, (byte)0, "§9§lNight Vision"));
                    inv.setItem(5, ItemFactory.create(Material.PRISMARINE_CRYSTALS, (byte)0, "§3§lWater Breathing"));

                    inv.setItem(7, ItemFactory.create(Material.BARRIER, (byte)0, "§c§lZrusit","§7Kliknutim deaktivujes."));

                    player.openInventory(inv);
                } else {
                    player.sendMessage("§c§l[!] §cK ziskani pristupu potrebujes mit aktivni minimalne Gold VIP.");
                }
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Zvol si efekt")) {
            if(e.getSlot() == 0){
                activateEffect(p, PotionEffectType.SPEED, 2);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bSpeed!");
            }
            if(e.getSlot() == 1){
                activateEffect(p, PotionEffectType.FAST_DIGGING, 2);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bHaste!");
            }
            if(e.getSlot() == 2){
                activateEffect(p, PotionEffectType.JUMP, 3);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bJump Boost!");
            }
            if(e.getSlot() == 3){
                activateEffect(p, PotionEffectType.FIRE_RESISTANCE, 3);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bFire Resistance!");
            }
            if(e.getSlot() == 4){
                activateEffect(p, PotionEffectType.NIGHT_VISION, 5);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bNight Vision!");
            }
            if(e.getSlot() == 5){
                activateEffect(p, PotionEffectType.WATER_BREATHING, 3);
                p.sendMessage("§e§l[*] §eAktivoval jsi permanentni §bWater Breathing!");
            }
            if(e.getSlot() == 7){
                p.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(p::removePotionEffect);
                p.sendMessage("§c§l[!] §cOdebral jsi vsechny aktivni efekty!");
                p.closeInventory();
            }
        }
    }

    private void activateEffect(Player p, PotionEffectType effect, int amlifier){
        p.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(p::removePotionEffect);
        p.addPotionEffect(new PotionEffect(effect, 12000000, amlifier));
        p.closeInventory();
    }
}
