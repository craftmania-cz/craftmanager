package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("votes|hlasy")
@Description("Otevře ti menu s hlasama")
public class Votes_command extends BaseCommand implements Listener {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVotes commands:");
        help.showHelp();
    }

    @Default
    public void openMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            openVotesMenu(player);
        }
    }

    public void openVotesMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "TOP Hlasovani");

        SkullMeta headItemMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        headItemMeta.setOwner(player.getName());
        headItemMeta.setDisplayName("§c§lTvoje statistiky");
        ItemStack headItem = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        ArrayList<String> headLore = new ArrayList<String>();
        headLore.add("");
        headLore.add("§7Tydenni hlasy: §f" + Main.getInstance().getMySQL().getPlayerProfileDataInt(player, "week_votes"));
        headLore.add("§7Mesicni hlasy: §f" + Main.getInstance().getMySQL().getPlayerProfileDataInt(player, "month_votes"));
        headLore.add("§7Celkem hlasu: §f" + Main.getInstance().getMySQL().getPlayerProfileDataInt(player, "total_votes"));
        headLore.add("");
        headLore.add("§7Moznost dalsiho hlasu: " + resolveTime(player));
        headLore.add("");
        headLore.add("§eKliknutim zobrazis odkaz na hlasovani!");
        headItemMeta.setLore(headLore);
        headItem.setItemMeta(headItemMeta);

        ItemStack filler = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§f").hideAllFlags().build();

        // Get Stats
        List<String> namesWeek = Main.getInstance().getMySQL().getTopVotersWeek();
        List<String> votesWeek = Main.getInstance().getMySQL().getTopVotersVotesWeek();

        List<String> namesMonth = Main.getInstance().getMySQL().getTopVotersMonth();
        List<String> votesMonth = Main.getInstance().getMySQL().getTopVotersVotesMonth();

        List<String> namesAll = Main.getInstance().getMySQL().getTopVotersAll();
        List<String> votesAll = Main.getInstance().getMySQL().getTopVotersVotesAll();

        // Top Week Item
        ItemStack topWeek = ItemFactory.createHead("topWeek", "eb1cc0ad-72fb-41b6-b725-5e603c002d00", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM3Y2U2ZjdhNzk3YTcxYTkxMWRiYzhlNjI2NzAyYjk3MzViN2QzYzJlOWZjYjI2YjgyY2FjZmM2Y2UwMWYxYSJ9fX0=");
        SkullMeta topWeekMeta = (SkullMeta) topWeek.getItemMeta();
        topWeekMeta.setDisplayName("§e§lTOP hraci (tento tyden)");
        ArrayList<String> topLore = new ArrayList<>();
        for (int i = 0; i < namesWeek.size(); i++) {
            topLore.add("§6" + (i + 1) + ". §7" + namesWeek.get(i) + " §8(" + votesWeek.get(i) + " hlasu)");
        }
        topWeekMeta.setLore(topLore);
        topWeek.setItemMeta(topWeekMeta);

        ItemStack topMonth = ItemFactory.createHead("topMonth", "241ffb69-235e-4dee-b781-d7542c8f2213", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFlN2Y3YTMwY2EyMzI0ZTMzNzk4Y2VmMWZmYTRlZTFmMGVhOWZkMzM4NWI1NGUwNWMyZmMyNzM0MzFjMTk2MiJ9fX0=");
        SkullMeta topMonthMeta = (SkullMeta) topMonth.getItemMeta();
        topMonthMeta.setDisplayName("§b§lTOP hraci (tento mesic)");
        ArrayList<String> topMonthLore = new ArrayList<>();
        for (int i = 0; i < namesMonth.size(); i++) {
            topMonthLore.add("§6" + (i + 1) + ". §7" + namesMonth.get(i) + " §8(" + votesMonth.get(i) + " hlasu)");
        }
        topMonthMeta.setLore(topMonthLore);
        topMonth.setItemMeta(topMonthMeta);

        ItemStack topAll = ItemFactory.createHead("topMonth", "f1ea6e8a-11c5-4a5b-b087-bbe145b1c5e7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q1MWM4M2NjMWViY2E1YTFiNmU2Nzk0N2UyMGI0YTJhNmM5ZWZlYTBjZjQ2OTI5NDQ4ZTBlMzc0MTZkNTgzMyJ9fX0=");
        SkullMeta topAllMeta = (SkullMeta) topAll.getItemMeta();
        topAllMeta.setDisplayName("§a§lTOP hraci (celkove)");
        ArrayList<String> topAllLore = new ArrayList<>();
        for (int i = 0; i < namesAll.size(); i++) {
            topAllLore.add("§6" + (i + 1) + ". §7" + namesAll.get(i) + " §8(" + votesAll.get(i) + " hlasu)");
        }
        topAllMeta.setLore(topAllLore);
        topAll.setItemMeta(topAllMeta);

        ItemStack hlasy = ItemFactory.create(Material.GOLD_INGOT, "§e§lOdmeny za hlasovani",
                "",
                "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                "§f25% sance: §625 CC",
                "§f5% sance: §650 CC",
                "§f1% sance: §6100 CC",
                "",
                "§bKazdy mesic muzes ziskat tyto bonusy!",
                "§f20 hlasu: §6200 CC",
                "§f40 hlasu: §6300 CC",
                "§f60 hlasu: §6500 CC");

        ItemStack odmeny = ItemFactory.create(Material.DIAMOND, "§b§lOdmeny pro TOP 5 hrace",
                "",
                "§7Kazdy mesic odmenujeme TOP 5",
                "§7hracu v hlasovani kupony na Store!",
                "",
                "§e1. §f15e",
                "§72. §f10e",
                "§63. §f7e",
                "§a4. §f5e",
                "§a5. §f5e",
                "",
                "§cKupony jsou zasilany do zprav na webu!");


        inventory.setItem(0, filler);
        inventory.setItem(1, filler);
        inventory.setItem(2, odmeny);
        inventory.setItem(3, filler);
        inventory.setItem(4, headItem);
        inventory.setItem(5, filler);
        inventory.setItem(6, hlasy);
        inventory.setItem(7, filler);
        inventory.setItem(8, filler);

        inventory.setItem(20, topWeek);
        inventory.setItem(22, topMonth);
        inventory.setItem(24, topAll);

        inventory.setItem(44, filler);
        inventory.setItem(43, filler);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(40, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, filler);
        inventory.setItem(36, filler);

        player.openInventory(inventory);

    }

    private String resolveTime(final Player player) {
        long time = Main.getInstance().getMySQL().getLastVote(player);

        if (time + 7200000 < System.currentTimeMillis()) {
            return "§aNyni";
        } else {
            long calculateMeWaka = (7200000 + 900000) - (System.currentTimeMillis() - time);
            return "§c" + TimeUtils.formatTime("%hh %mm", calculateMeWaka/1000/60, false);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("TOP Hlasovani")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 4) {
                Vote_command.sendVoteLink(player);
                player.closeInventory();
            }
        }
    }
}
