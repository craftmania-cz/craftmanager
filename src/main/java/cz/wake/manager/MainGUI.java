package cz.wake.manager;

import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.CraftTokensAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MainGUI implements Listener {

    public void openMainMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§0Menu");

        SkullMeta headMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName("§9§l" + p.getName());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add("§7");
        headLore.add("§6CraftCoins: §f" + CraftCoinsAPI.getCoins(p));
        headLore.add("§aCraftTokens: §f" + CraftTokensAPI.getTokens(p));
        headLore.add("§bVoteTokens: §f" + VoteTokensAPI.getVoteTokens(p));
        headMeta.setLore(headLore);
        head.setItemMeta(headMeta);
        inv.setItem(13, head);

        ItemStack vip = ItemFactory.create(Material.EMERALD, (byte) 0, "§a§lVIP", "§7Prehled vyhod a SMS k ", "§7nakupu VIP na serveru!", "", "§eKlikni pro zobrazeni!");

        ItemStack shop = ItemFactory.create(Material.NETHER_STAR, (byte) 0, "§a§lCoinShop", "", "§7Zde najdes seznam prikazu,", "§7ruznych boosteru a efekty,", "§7ktere si muzes zakoupit za CraftCoiny!", "", "§eKlikni pro zobrazeni!");

        ItemStack odkaz = ItemFactory.create(Material.PAPER, (byte) 0, "§c§lOdkaz na hlasovani", "", "§fKliknutim zobrazis odkaz,", "§fktery te rovnou presmeruje", "§fna stranku s hlasovanim.", "", "§eKlikni pro zobrazeni!");

        ItemStack particles = ItemFactory.create(Material.DIAMOND, (byte) 0, "§b§lParticles", "", "§7Prehled vsech efektu,", "§7ktere vlastnis nebo", "§7nebo si muzes zakoupit.", "", "§eKlikni pro zobrazeni");

        ItemStack guides = ItemFactory.create(Material.BOOK, (byte)0, "§a§lNavody", "", "§7Seznam navodu, sepsanych", "§7primo pro nas server.", "", "§eKliknutim zobrazis navody");

        inv.setItem(30, particles);
        inv.setItem(21, odkaz);
        inv.setItem(23, shop);
        inv.setItem(24, vip);
        inv.setItem(32, guides);

        p.openInventory(inv);

    }
}
