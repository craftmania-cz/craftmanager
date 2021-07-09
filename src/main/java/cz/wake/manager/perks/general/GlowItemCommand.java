package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("glowitem|gi")
@Description("Aplikuje ti glow efekt na item")
public class GlowItemCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lGlowitem commands:");
        help.showHelp();
    }

    @Default
    public void makeGlow(CommandSender sender) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (!player.hasPermission("craftmanager.vip.glowingitems")) {
                ChatInfo.DANGER.send(player, "Nemáš dostatek práv, na toto musíš mít VIP. §f/vip");
                return;
            }
            if (Main.getServerType() == ServerType.ANARCHY) {
                ChatInfo.DANGER.send(player, "Na tomto serveru tato výhoda neplati!");
                return;
            }
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null) {
                ChatInfo.DANGER.send(player, "Musíš držet item, na který chceš dat glowing efekt.");
                return;
            }
            //Přidat blacklist všech itemů, na které jde dávat ve Vanilla MC enchanty. Viz: https://youtrack.waked.cz/issue/CMD-845
            if (item.isSimilar(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1))) {
                ChatInfo.DANGER.send(player, "Na tento item nelze použít příkaz /gi");
                return;
            }

            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasLore()) {
                    ChatInfo.DANGER.send(player, "Na tento item nelze použít příkaz /gi");
                    return;
                }
            }

            if (item.getAmount() > 1) {
                ChatInfo.DANGER.send(player, "GlowItem lze použít pouze na jeden item!");
                return;
            }

            if (!item.getEnchantments().isEmpty()) {
                ChatInfo.DANGER.send(player, "Nelze použít GlowItem na item, který již má enchant!");
                return;
            }

            if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                ChatInfo.DANGER.send(player, "Nelze aplikovat glowing efekt na kosmetický item.");
                return;
            }

            ItemBuilder itemBuilder = new ItemBuilder(item);
            player.getInventory().setItemInMainHand(null);
            itemBuilder.setGlowing();
            itemBuilder.setAmount(1);
            player.getInventory().setItemInMainHand(itemBuilder.build());
            ChatInfo.SUCCESS.send(player, "Item byl změněn na Glowing!");
        }
    }
}
