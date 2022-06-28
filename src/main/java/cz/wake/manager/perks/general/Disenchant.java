package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@CommandAlias("disenchant")
@Description("Získání zpět enchantů na nástroji")
public class Disenchant extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lDisenchant commands:");
        help.showHelp();
    }

    @Default
    public void disenchant(CommandSender Sender) {
        if (Sender instanceof Player player) {
            if (player.hasPermission("craftmanager.vip.disenchant")) {
                if (Main.getInstance().getServerType() == ServerType.ANARCHY) {
                    ChatInfo.DANGER.send(player, "Na tomto serveru tato výhoda neplatí.");
                    return;
                }
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                if (itemInHand.getItemMeta() == null) {
                    ChatInfo.DANGER.send(player, "Tento item není poničen nebo provést disenchant.");
                    return;
                }
                if (itemInHand.getItemMeta().hasCustomModelData()) {
                    ChatInfo.DANGER.send(player, "Nelze disenchantovat item, který má nastavený styl.");
                    return;
                }
                short durability = itemInHand.getDurability();
                if ((!itemInHand.getEnchantments().isEmpty()) && (itemInHand.getType() != Material.BOOK)
                        && (itemInHand.getType() != Material.TRIPWIRE_HOOK) && (itemInHand.getType() != Material.KNOWLEDGE_BOOK)) {

                    Map<Enchantment, Integer> enchantments = itemInHand.getEnchantments();
                    HashMap<String, Integer> customEnchants = new HashMap<>(); // Only fix

                    // Kalkulace ceny
                    AtomicInteger finalPriceLvls = new AtomicInteger();
                    finalPriceLvls.addAndGet(enchantments.values().size() * 5);
                    enchantments.forEach((enchantment, integer) -> {
                        if (integer > 1) finalPriceLvls.addAndGet(integer);
                    });

                    // Kontrola Glowing items
                    if (itemInHand.getEnchantments().containsKey(Enchantment.DURABILITY)) {
                        if (itemInHand.getEnchantments().get(Enchantment.DURABILITY) == 0) {
                            ChatInfo.DANGER.send(player, "Nelze použít Disenchant na item, který má na sobě Glowing efekt.");
                            return;
                        }
                    }

                    if (player.getLevel() > finalPriceLvls.get()) {
                        ItemStack withoutEnchant = new ItemStack(player.getItemInHand().getType(), 1);
                        player.getInventory().removeItem(itemInHand);

                        player.setLevel(player.getLevel() - finalPriceLvls.get());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
                            Enchantment enchant = entry.getKey();
                            int level = (entry.getValue());
                            player.getInventory().addItem(addBookEnchantment(enchantBook, enchant, level));
                        }

                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                        player.sendMessage("");
                        player.sendMessage("§aPředmět byl disenchantován za §6" + finalPriceLvls + " LVL");
                        player.sendMessage("§7Enchant = 5 LVL");
                        player.sendMessage("");
                        player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                        withoutEnchant.setDurability(durability);
                        player.getInventory().addItem(withoutEnchant);
                    } else {
                        ChatInfo.INFO.send(player, "Musíš mít minimálně " + finalPriceLvls + " levelů na disenchant totoho itemu!");
                    }
                } else {
                    ChatInfo.DANGER.send(player, "Na požadovaný item nelze použít Disenchant!");
                }
            } else {
                ChatInfo.DANGER.send(player, "K použití tohoto příkazu musíš mít zakoupené VIP!");
            }
        }
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
