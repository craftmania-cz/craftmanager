package cz.wake.manager.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuManager {

    public static void openNavody(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, "Seznam dostupnych navodu");

        ItemStack pravidla = ItemFactory.create(Material.ENCHANTED_BOOK, "§e§lPravidla serveru", "§7Pravidla serveru jsou", "§7zaklad spravneho chovani!", "", "§fVhodne pro: §cVsude");
        ItemStack mody = ItemFactory.create(Material.ENCHANTED_BOOK, "§e§lPovolene/zakazane mody", "§7Nevis jestli je tvuj mod", "§7povoleny nebo zakazany?", "", "§fVhodne pro: §cVsude");

        ItemStack residence = ItemFactory.create(Material.PAPER, "§e§lResidence", "§7Navod pro vsechny", "§7co chceji plnohodnotne", "§7ovladnout residence","","§fVhodne pro: §bSurvival");
        ItemStack jobs = ItemFactory.create(Material.PAPER, "§e§lJobs", "§7Jak si vydelavat penize", "§7na nasich serverech?", "", "§fVhodne pro: §bSkyblock");
        ItemStack shop = ItemFactory.create(Material.PAPER, "§e§lChestShop", "§7Jak vytvorit shop", "§7a jak vykupovat itemy.", "", "§fVhodnoce pro: §bSurv, Sky");
        ItemStack pozemky = ItemFactory.create(Material.PAPER, "§e§lPozemky", "§7Navod jak ovladnout", "§7tvuj pozemek naplno!", "", "§fVhodne pro: §bCreative");
        ItemStack lands = ItemFactory.create(Material.PAPER, "§e§lLands", "§7Vytvareni Lands, sprava lands", "§7a jine tipy a triky!", "", "§fVhodne pro: §bVanilla");
        ItemStack repl = ItemFactory.create(Material.PAPER, "§e§lReplacementy", "§7Jak na Emoji v chatu", "", "§fVhodne pro: §bSurv, Sky, Crea");

        ItemStack zpet = ItemFactory.create(Material.ARROW, "§cZpet");

        inv.setItem(0, pravidla);
        inv.setItem(1, mody);
        inv.setItem(2, residence);
        inv.setItem(3, jobs);
        inv.setItem(4, shop);
        inv.setItem(5, pozemky);
        inv.setItem(6, lands);
        inv.setItem(7, repl);
        inv.setItem(31, zpet);

        p.openInventory(inv);
    }

    public static void prepareNavodLink(Player p, String name, String url){
        p.sendMessage("");
        p.sendMessage("§a§l" + name);
        p.sendMessage("§e" + url);
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.0f);
        p.closeInventory();
    }
}
