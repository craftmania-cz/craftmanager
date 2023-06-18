package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.wake.manager.Main;
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
        ArrayList<String> topLore = new ArrayList<>();
        for (int i = 0; i < namesWeek.size(); i++) {
            topLore.add("§6" + (i + 1) + ". §7" + namesWeek.get(i) + " §8(" + votesWeek.get(i) + " hlasu)");
        }
        ItemStack topWeek = new ItemBuilder(Material.PAPER).setName("§e§lTOP hraci (tento tyden)").setLore(topLore).build();


        ArrayList<String> topMonthLore = new ArrayList<>();
        for (int i = 0; i < namesMonth.size(); i++) {
            topMonthLore.add("§6" + (i + 1) + ". §7" + namesMonth.get(i) + " §8(" + votesMonth.get(i) + " hlasu)");
        }
        ItemStack topMonth = new ItemBuilder(Material.PAPER).setName("§b§lTOP hraci (tento mesic)").setLore(topMonthLore).build();


        ArrayList<String> topAllLore = new ArrayList<>();
        for (int i = 0; i < namesAll.size(); i++) {
            topAllLore.add("§6" + (i + 1) + ". §7" + namesAll.get(i) + " §8(" + votesAll.get(i) + " hlasu)");
        }
        ItemStack topAll = new ItemBuilder(Material.PAPER).setName("§a§lTOP hraci (celkove)").setLore(topAllLore).build();

        ItemStack hlasy = new ItemBuilder(Material.GOLD_INGOT).setName("§e§lOdmeny za hlasovani").setLore(
                "",
                "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                "§f25% sance: §625 CC",
                "§f5% sance: §650 CC",
                "§f1% sance: §6100 CC",
                "",
                "§bKazdy mesic muzes ziskat tyto bonusy!",
                "§f20 hlasu: §6200 CC",
                "§f40 hlasu: §6300 CC",
                "§f60 hlasu: §6500 CC").build();

        ItemStack odmeny = new ItemBuilder(Material.DIAMOND).setName("§b§lOdmeny pro TOP 5 hrace")
                /*.setLore(
                "",
                "§7Kazdy mesic odmenujeme TOP 5",
                "§7hracu v hlasovani kupony na Store!",
                "",
                "§e1. §f250kč / 10 euro",
                "§72. §f180kč / 7 euro",
                "§63. §f120kč / 5 euro",
                "§a4. §f120kč / 5 euro",
                "§a5. §f50kč / 2 euro",
                "",
                "§cKupony jsou zasilany do zprav na Discord!")*/
                .setLore("§7Vyplácení odměn bylo zrušeno kvůli nízkému počtu hráčů.")
                .build();


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
            long calculateMeWaka = 7200000 - (System.currentTimeMillis() - time);
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
