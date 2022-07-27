package cz.wake.manager.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Help pro Survival")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if ((e.getSlot() == 12) || (e.getSlot() == 13) || (e.getSlot() == 14)) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na plny navod Residence:");
                p.sendMessage("§7https://wiki.craftmania.cz/residence/");
                p.sendMessage("");
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 22) {
                p.performCommand("rewards");
            }
            if (e.getSlot() == 24) {
                p.performCommand("vote");
                p.closeInventory();
            }
            if (e.getSlot() == 32) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eNovinky: §7https://news.craftmania.cz/");
                p.sendMessage("§eForum: §7https://craftmania.cz/");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania/");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz/");
                p.sendMessage("§eStatistiky: §7https://stats.craftmania.cz/");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 20) {
                p.performCommand("vip");
            }
        }
        if (e.getView().getTitle().equals("Help pro Creative")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if ((e.getSlot() == 12) || (e.getSlot() == 13) || (e.getSlot() == 14)) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na plny navod Pozemky:");
                p.sendMessage("§7https://wiki.craftmania.cz/navody/pozemky.html");
                p.sendMessage("");
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 21) {
                p.performCommand("vip");
            }
            if (e.getSlot() == 22) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 31) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eWeb: §7https://craftmania.cz");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
        }
        if (e.getView().getTitle().equals("Help pro Skyblock")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 32) {
                p.performCommand("vip");
            }
            if (e.getSlot() == 24) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 31) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eWeb: §7https://craftmania.cz");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
        }
    }
}
