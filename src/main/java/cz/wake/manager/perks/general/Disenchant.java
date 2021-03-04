package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CommandAlias("disenchant")
@Description("Otevře ti tvůj profil")
public class Disenchant extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lDisenchant commands:");
        help.showHelp();
    }

    @Default
    public boolean disenchant(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (player.hasPermission("craftmanager.vip.disenchant")) {
                if (Main.getServerType() == ServerType.VANILLA || Main.getServerType() == ServerType.ANARCHY || Main.getServerType() == ServerType.HARDCORE_VANILLA) {
                    player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                    return true;
                }
                ItemStack itemInHand = player.getItemInHand();
                short durability = itemInHand.getDurability();
                if ((!itemInHand.getEnchantments().isEmpty()) && (itemInHand.getType() != Material.BOOK)
                        && (itemInHand.getType() != Material.TRIPWIRE_HOOK)) {

                    Map<Enchantment, Integer> enchantments = itemInHand.getEnchantments();
                    HashMap<String, Integer> customEnchants = new HashMap<>(); // Only fix

                    // Kalkulace ceny
                    int finalPriceLvls = 0;
                    finalPriceLvls += enchantments.values().size() * 5;

                    // Kontrola Glowing items
                    if (itemInHand.getEnchantments().containsKey(Enchantment.DURABILITY)) {
                        if (itemInHand.getEnchantments().get(Enchantment.DURABILITY) == 0) {
                            player.sendMessage("§c§l[!] §cNelze pouzit Disenchant na item, ktery ma na sobe Glowing.");
                            return true;
                        }
                    }

                    if (player.getLevel() > finalPriceLvls) {
                        ItemStack withoutEnchant = new ItemStack(player.getItemInHand().getType(), 1);
                        player.getInventory().removeItem(itemInHand);

                        player.setLevel(player.getLevel() - finalPriceLvls);
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
                            Enchantment enchant = entry.getKey();
                            int level = (entry.getValue());
                            player.getInventory().addItem(addBookEnchantment(enchantBook, enchant, level));
                        }

                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                        player.sendMessage("");
                        player.sendMessage("§aPredmet byl disenchantovan za §6" + finalPriceLvls + " LVL");
                        player.sendMessage("§7Standartni enchant - 5 LVL");
                        player.sendMessage("");
                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                        withoutEnchant.setDurability(durability);
                        player.getInventory().addItem(withoutEnchant);
                    } else {
                        player.sendMessage("§c§l[!] §cMusis mit minimalne " + finalPriceLvls + " levlu na disenchant tohoto itemu!");
                    }
                } else {
                    player.sendMessage("§c§l[!] §cNa pozadovany item nelze pouzit Disenchant!");
                }
            } else {
                player.sendMessage("§c§l[!] §cK pouziti tohoto prikazu musis mit zakoupene VIP!");
            }
        }
        return false;
    }

    private ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
