package cz.wake.manager.utils.configs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import cz.craftmania.craftlibs.utils.Log;
import cz.wake.manager.Main;
import cz.wake.manager.objects.AdminTeamMember;
import cz.wake.manager.utils.Request;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class CrazyCratesConfig {
    public static void generateHeadCrate() {
        JsonObject ATSData = (JsonObject) new JsonParser().parse(Request.get("https://api.craftmania.cz/admin/ats"));
        if (ATSData.get("status").getAsInt() != 200) {
            Log.error("CM API vrátilo status code " + ATSData.get("status").getAsInt() + " config headcreatky nebude obnoven.");
            return;
        }
        File headConfigFile = new File(Main.getInstance().getServer().getWorldContainer().getAbsolutePath() + "/plugins/CrazyCrates/Crates/Head.yml");
        if (!headConfigFile.exists()) {
            try {
                headConfigFile.createNewFile();
                importData(ATSData, headConfigFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            headConfigFile.delete();
            try {
                headConfigFile.createNewFile();
                importData(ATSData, headConfigFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void importData(JsonObject ATSData, File headConfigFile) {
        Type adminTeamListType = new TypeToken<ArrayList<AdminTeamMember>>() {
        }.getType();
        ArrayList<AdminTeamMember> adminTeamList = new Gson().fromJson(ATSData.getAsJsonArray("data"), adminTeamListType);

        Collections.sort(adminTeamList, Comparator.comparing(AdminTeamMember::getRank).thenComparing(AdminTeamMember::getNick, String.CASE_INSENSITIVE_ORDER));
        Collections.reverse(adminTeamList);

        Predicate<AdminTeamMember> rank0 = member -> member.getRank() == 0;

        adminTeamList.removeIf(rank0);

        FileConfiguration headConfig = YamlConfiguration.loadConfiguration(headConfigFile);

        // Zápis pevně stanovených hodnot
        headConfig.set("Crate.CrateType", "CSGO");
        headConfig.set("Crate.CrateName", "&c&lHead Crate");
        headConfig.set("Crate.Preview-Name", "Head Crate náhled");
        headConfig.set("Crate.StartingKeys", 0);
        headConfig.set("Crate.InGUI", true);
        headConfig.set("Crate.Slot", 21);
        headConfig.set("Crate.OpeningBroadCast", false);
        headConfig.set("Crate.BroadCast", "%Prefix%&6&l%Player% &7is opening a &7&lBasic Chest&7.");
        headConfig.set("Crate.Item", "CHEST");
        headConfig.set("Crate.Glowing", false);
        headConfig.set("Crate.Name", "&c&lHead Crate");
        final List<String> crateLoreList = new ArrayList<String>();
        crateLoreList.add("&7Tato truhla obsahuje základní itemy a možná i něco víc!");
        crateLoreList.add("&7Máš &6%Keys% klíčů &7od této truhly.");
        crateLoreList.add("&c[!] Klikni pravým k zobrazení náhledu.");
        headConfig.set("Crate.Lore", crateLoreList);
        headConfig.set("Crate.Preview.Toggle", true);
        headConfig.set("Crate.Preview.ChestLines", 6);
        headConfig.set("Crate.Preview.Glass.Toggle", true);
        headConfig.set("Crate.Preview.Glass.Item", "GRAY_STAINED_GLASS_PANE");
        headConfig.set("Crate.PhysicalKey.Name", "&c&lHead Crate Key");
        final List<String> cratePhysicalKeyLoreList = new ArrayList<String>();
        cratePhysicalKeyLoreList.add("&7Klíč od Head Crate truhly.");
        cratePhysicalKeyLoreList.add("&9[Original]");
        headConfig.set("Crate.PhysicalKey.Lore", cratePhysicalKeyLoreList);
        headConfig.set("Crate.PhysicalKey.Item", "TRIPWIRE_HOOK");
        headConfig.set("Crate.PhysicalKey.Glowing", true);
        headConfig.set("Crate.Hologram.Toggle", true);
        headConfig.set("Crate.Hologram.Height", 2);
        final List<String> crateHologramMessageList = new ArrayList<String>();
        crateHologramMessageList.add("&c&lHead Crate");
        crateHologramMessageList.add("&7Hlavy všech členů AT!");
        headConfig.set("Crate.Hologram.Message", crateHologramMessageList);

        // Přidávání jednotlivých členů AT
        int i = 1;
        for (AdminTeamMember member : adminTeamList) {
            headConfig.set("Crate.Prizes." + i + ".DisplayName", "&e&l" + member.getNick());
            headConfig.set("Crate.Prizes." + i + ".DisplayItem", "PLAYER_HEAD");
            headConfig.set("Crate.Prizes." + i + ".DisplayAmount", 1);
            final List<String> prizeLoreList = new ArrayList<String>();
            headConfig.set("Crate.Prizes." + i + ".Lore", prizeLoreList);
            headConfig.set("Crate.Prizes." + i + ".MaxRange", 100);
            headConfig.set("Crate.Prizes." + i + ".Chance", getChance(member.getRank()));
            final List<String> prizeCommandsList = new ArrayList<String>();
            prizeCommandsList.add("minecraft:give %Player% player_head{SkullOwner:" + member.getNick() + ",display:{Name:'[{\"text\":\"" + member.getNick() + "\",\"italic\":false,\"color\":\"aqua\",\"bold\":true}]',Lore:['[{\"text\":\"Získáno z \",\"italic\":false,\"color\":\"gray\"},{\"text\":\"HeadCrate\",\"color\":\"yellow\"}]']}} 1");
            headConfig.set("Crate.Prizes." + i + ".Commands", prizeCommandsList);
            final List<String> prizeMessagesList = new ArrayList<String>();
            prizeMessagesList.add("&7Vyhrál jsi &a" + member.getNick() + " Head&7.");
            headConfig.set("Crate.Prizes." + i + ".Messages", prizeMessagesList);
            i++;
        }

        // Ukládání configu
        try {
            headConfig.save(headConfigFile);
            Log.info("HeadCrate config pro CrazyCrate byl úspěšně uložen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Nastavení šance na drop podle ranku
    private static int getChance(int rank) {
        switch(rank) {
            case 12:
                return 1;
            default:
                return 10;
        }
    }
}
