package cz.wake.manager.perks.general;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import io.github.jorelali.commandapi.api.CommandAPI;
import n3kas.ae.api.AEAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GlowItemCommand {

    public static void registerCommand() {

        CommandAPI.getInstance().register("glowitem", new String[]{"gi"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!player.hasPermission("craftmanager.vip.glowingitems")) {
                    player.sendMessage("§c§l[!] §cNedostatecna prava, na toto musis mit VIP. §f/vip");
                    return;
                }
                if (Main.getServerType() == ServerType.VANILLA) {
                    player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                    return;
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                if (item == null) {
                    player.sendMessage("§c§l[!] §cMusis drzet item, na ktery chces dat glowing efekt.");
                    return;
                }
                //Přidat blacklist všech itemů, na které jde dávat ve Vanilla MC enchanty. Viz: https://youtrack.waked.cz/issue/CMD-845
                if (item.isSimilar(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1))) {
                    player.sendMessage("§c§l[!] §cNa tento item nelze pouzit prikaz /gi");
                    return;
                }

                if (item.hasItemMeta()) {
                    if (item.getItemMeta().hasLore()) {
                        player.sendMessage("§c§l[!] §cNa tento item nelze pouzit prikaz /gi");
                        return;
                    }
                }

                if (item.getAmount() > 1) {
                    player.sendMessage("§c§l[!] §cGlowItem lze pouzit pouze na jeden item!");
                    return;
                }

                if (!item.getEnchantments().isEmpty()) {
                    player.sendMessage("§c§l[!] §cNelze pouzit GlowItem na item, ktery jiz ma enchant!");
                    return;
                }

                if (Main.getInstance().isCustomDisenchantEnabled()) {
                    if (!AEAPI.getEnchantmentsOnItem(item).isEmpty()) {
                        player.sendMessage("§c§l[!] §cNelze pouzit GlowItem na item, ktery jiz ma enchant!");
                        return;
                    }
                }

                ItemBuilder itemBuilder = new ItemBuilder(item);
                player.getInventory().setItemInMainHand(null);
                itemBuilder.setGlowing();
                itemBuilder.setAmount(1);
                player.getInventory().setItemInMainHand(itemBuilder.build());
                player.sendMessage("§e§l[*] §eItem byl zmenen na Glowing!");
            } else {
                sender.sendMessage("§cTento příkaz je jen pro hráče!");
            }
        });
    }
}
