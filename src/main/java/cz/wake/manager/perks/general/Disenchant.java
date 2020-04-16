package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import io.github.jorelali.commandapi.api.CommandAPI;
import n3kas.ae.api.AEAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Disenchant {

    public static void registerCommand() {

        CommandAPI.getInstance().register("disenchant", new String[]{}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("craftmanager.vip.disenchant")) {

                    if (Main.getServerType() == ServerType.VANILLA || Main.getServerType() == ServerType.SKYCLOUD) {
                        player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                        return;
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
                                return;
                            }
                        }

                        // Ziskani enchantu
                        if (Main.getInstance().isCustomDisenchantEnabled()) {
                            customEnchants = AEAPI.getEnchantmentsOnItem(itemInHand);
                            finalPriceLvls += customEnchants.values().size() * 10;
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

                            if (Main.getInstance().isCustomDisenchantEnabled()) {
                                for (Map.Entry<String, Integer> enchEntry : customEnchants.entrySet()) {

                                    // Sance
                                    int sance = randRange(50, 80);
                                    int failSance = 100 - sance;

                                    // Name, level, success rate, fail rate
                                    ItemStack customEnchantedBook = AEAPI.createEnchantmentBook(enchEntry.getKey(), enchEntry.getValue(), sance, failSance);
                                    player.getInventory().addItem(customEnchantedBook);
                                }
                            }

                            player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                            player.sendMessage("");
                            player.sendMessage("§aPredmet byl disenchantovan za §6" + finalPriceLvls + " LVL");
                            if (Main.getInstance().isCustomDisenchantEnabled()) {
                                player.sendMessage("§7Standartni enchant - 5 LVL, Custom enchant - 10 LVL");
                            } else {
                                player.sendMessage("§7Standartni enchant - 5 LVL");
                            }
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
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }

    private static ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level) {
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
