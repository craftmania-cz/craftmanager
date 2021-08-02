package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@CommandAlias("help|pomoc")
@Description("Otevře ti menu s pomocí")
public class Help_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lHelp commands:");
        help.showHelp();
    }

    @Default
    public boolean openHelpMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Main.getServerType() == ServerType.PRISON) {
                player.performCommand("tutorial");
            } else {
                openMenu(player);
            }
        }
        return true;
    }

    private void openMenu(Player p) {
        if (Main.getServerType() == ServerType.SKYBLOCK) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Skyblock");

            ItemStack is = ItemFactory.create(Material.GRASS, "§aVytvoření a nastavení ostrova",
                    "§e/is §f- §7Vytvoří nový ostrov nebo teleportuje",
                    "§7na již vytvořený ostrov, nebo kde jsi přidaný.",
                    "§e/is sethome §f- §7Nastaví teleport při používání příkazu §b/is",
                    "§e/is level §f- §7Zobrazí level ostrova a počet bloků na něm",
                    "§e/is top §f- §7Zobrazí TOP 10 největších ostrovů (nefunguje dočasně)",
                    "§e/is lock §f- §7Uzamkne ostrov a nikdo kromě AT,",
                    "§7se na tvůj ostrov nebude moct teleportovat.",
                    "§e/is restart §f- §7Restartuje ostrov. §c§lSMAŽE VŠE!",
                    "§e/is team leave §f- §7Opustíš ostrov... :(",
                    "§e/is border §f- §7Přepnutí barev v borderu",
                    "§e/is setname název §fNastaví název ostrova");

            ItemStack isTeam = ItemFactory.create(Material.MAGMA_CREAM, "§aPřidání a odebrání hráčů na ostrově",
                    "§e/is team invite §6" + p.getName() + " §f- §7Přidá hráče na tvůj ostrov",
                    "§e/is team accept §f- §7Potvrzení žádosti o přidání na ostrov od jiného hráče",
                    "§e/is team reject §f- §7Zruší žádost o přidání na jiný ostrov",
                    "§e/is ban §6" + p.getName() + " §f- §7Zabanuje hráč pro tvůj ostrov,",
                    "§7nebude se poté moct na něj jakkoliv dostat.",
                    "§e/is team setowner §6" + p.getName() + " §f- §7Nastaví nového majitele ostrova");

            ItemStack oIs = ItemFactory.create(Material.MELON, "§aOstatní příkazy k ostrovům",
                    "§e/is biomes §f- §7Nastavení biomu na ostrově (dočasně off)",
                    "§e/is settings §f §7Kompletní nastavení ostrova");

            /*ItemStack jobs = ItemFactory.create(Material.IRON_PICKAXE, "§a§lJobs", "",
                    "§cKazdy hrac smi mit maximalne 3 aktivni prace!",
                    "§e/jobs §f- §7Zakladni prikaz k ziskani napovedy",
                    "§e/jobs browse §f- §7Seznam vsech dostupnych praci",
                    "§e/jobs join §6nazevPrace §f- §7Pripojeni do prace",
                    "§e/jobs leave §6nazevPrace §f §7Odstoupeni z prace",
                    "§7(neztratis uroven prace)",
                    "§e/jobs stats §6" + p.getName() + " §f- §7Informace o hraci/sobe",
                    "§e/jobs gtop §f- §7Zobrazi TOP hrace v Jobs na serveru");*/

            ItemStack vip = ItemFactory.create(Material.EMERALD, "§aNákup VIP",
                    "§7Pokud si chceš zakoupit VIP,", "§7tak kliknutím zde se ti zobrazí přehled",
                    "§7všech dostupných VIP na tomto serveru.", "", "§bKlikni pro zobrazení VIP");

            ItemStack aukce = ItemFactory.create(Material.CAKE, "§aAukce §c§l(dočasně off)",
                    "§7Pomocí aukcí můžeš hráčům prodávat", "§7různé itemy nebo bloky.", "",
                    "§cVytvoření aukce:", "§7K vytvoření aukce, drž item, co chceš", "§7prodat a napiš tento příkaz:",
                    "§e/au start §61 1000 100 1d §8- §7Vytvoří aukci na 1 den",
                    "",
                    "§cPopis příkazu:", "§e/au start [pocetItemu] [zaklCena] [min.nabHrace] [cas]",
                    "§8- §6pocetItemu §f- §7Je počet prodávaných itemu",
                    "§7nebo bloku př. 1/2/64",
                    "§8- §6zaklCena §f- §7Je cena, se kterou aukce začíná",
                    "§8- §6min.nabHrace §f- §7Je minimální částka, ",
                    "§7kterou hráč může přidat do aukce",
                    "§8- §6cas §f- §7Určuje konec aukce př. 1m/1h/1d ",
                    "§7(lze i kombinovat 1d12h)");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, "§aHlasování",
                    "§7Hlasovat můžeš každý 1x za 2 hodiny na CzechCraftu,",
                    "§7a 1x za 24h na CraftListu.",
                    "",
                    "§fKaždý hlas: §610 CC §f+ §aVoteToken",
                    "§f25% šance: §625 CC",
                    "§f5% šance: §650 CC",
                    "§f1% šance: §6100 CC",
                    "",
                    "§bKaždý měsíc můžeš získat tyto bonusy:",
                    "§f20 hlasů: §6200 CC",
                    "§f40 hlasů: §6300 CC",
                    "§f60 hlasů: §6500 CC",
                    "",
                    "§6Hlasovat lze i offline!",
                    "",
                    "§eKliknutím zobrazíš odkazy na hlasování!");

            ItemStack guides = ItemFactory.create(Material.BOOK, "§aNávody",
                    "§7Přehled všech dostupných návodů",
                    "§7na naší hlavní WIKI!",
                    "",
                    "§eKliknutím zde zobrazíš přehled");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, "§aOstatní příkazy a teleporty po serveru",
                    "", "§e/shop §f- §7Otevře menu s server shopem",
                    "§e/cshop §f- §7Otevře CoinShop s CM měnou",
                    "§e/cc§7, §e/ct§7, §e/vt- §7Stav CraftCoins, CraftTokens, VoteTokens",
                    "§e/trade §6" + p.getName() + " §f- §7Obchodování s hráčem",
                    "§e/fr §f- §7Friends a psaní si s kamarády přes celý server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, "§aOdkazy na naše stránky", "",
                    "§eNovinky: §7https://news.craftmania.cz/",
                    "§eFórum: §7https://craftmania.cz/",
                    "§eDiscord: §7https://discord.gg/craftmania/",
                    "§eStatus page: §7https://status.craftmania.cz/",
                    "§eStatistiky: §7https://stats.craftmania.cz/",
                    "",
                    "§bKliknutím zde, se ti zobrazí klikací odkazy v chatu");

            inv.setItem(12, is);
            inv.setItem(13, isTeam);
            inv.setItem(14, oIs);

            //inv.setItem(20, jobs);
            inv.setItem(20, vip);
            inv.setItem(21, aukce);
            inv.setItem(22, guides);
            inv.setItem(23, hlasovani);
            inv.setItem(24, ser);

            inv.setItem(31, conn);

            p.openInventory(inv);
        }
        if (Main.getServerType() == ServerType.CREATIVE) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Creative");

            ItemStack poz = ItemFactory.create(Material.DIRT, "§aZákladní správa pozemku",
                    "§e/p auto §f- §7Automaticky zabere nejbližší volný pozemek",
                    "§e/p claim §f- §7Zabere pozemek na kterém stojíš (pokud není zabraný",
                    "§e/p home §f- §7Teleport na tůj 1. pozemek",
                    "§e/p home:2 §f- §7Teleport na tůj 2. pozemek",
                    "§e/p delete §f- §7Smaže pozemek kompletně!",
                    "§e/p clear §f- §7Vyčistí pozemek od staveb",
                    "§e/p sethome §f- §7Nastaví na polohu kde jsi /p home",
                    "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack pozH = ItemFactory.create(Material.NETHERRACK, "§aSpráva hráčů na pozemku",
                    "§e/p trust §6" + p.getName() + " §f- §7Přidá hráče na pozemek",
                    "§e/p untrust §6" + p.getName() + " §f- §7Odebere hráče z pozemku",
                    "§e/p deny §6" + p.getName() + " §f- §7Zamezí vstup hráči na pozemek",
                    "§e/p visit §6" + p.getName() + " §f- §7Teleport na cizí pozemek",
                    "§e/p setowner §6" + p.getName() + " §f- §7Nastaví nového majitele pozemku",
                    "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack pozD = ItemFactory.create(Material.OBSIDIAN, "§aDalší příkazy na pozemky",
                    "§e/p music §f- §7Zobrazí jukebox k přehrávání hudby",
                    "§e/p kick " + p.getName() + " §f- §7Vykopne hráče z pozemku",
                    "§e/p merge auto §f- §7Spojí pozemky do jednoho velkého",
                    "§cPodmínkou je mít pozemky vedle sebe!",
                    "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack vip = ItemFactory.create(Material.EMERALD, "§aNákup VIP",
                    "§7Pokud si chceš zakoupit VIP,", "§7tak kliknutím zde se ti zobrazí přehled",
                    "§7všech dostupných VIP na tomto serveru.", "", "§bKlikni pro zobrazení VIP");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, "§aHlasování",
                    "§7Hlasovat můžeš každý 1x za 2 hodiny na CzechCraftu,",
                    "§7a 1x za 24h na CraftListu.",
                    "",
                    "§fKaždý hlas: §610 CC §f+ §aVoteToken",
                    "§f25% šance: §625 CC",
                    "§f5% šance: §650 CC",
                    "§f1% šance: §6100 CC",
                    "",
                    "§bKaždý měsíc můžeš získat tyto bonusy:",
                    "§f20 hlasů: §6200 CC",
                    "§f40 hlasů: §6300 CC",
                    "§f60 hlasů: §6500 CC",
                    "",
                    "§6Hlasovat lze i offline!",
                    "",
                    "§eKliknutím zobrazíš odkazy na hlasování!");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, "§aOstatní příkazy a teleporty po serveru",
                    "§e/shop §f- §7Otevře menu s server shopem",
                    "§e/cshop §f- §7Otevře CoinShop s CM měnou",
                    "§e/cc§7, §e/ct§7, §e/vt- §7Stav CraftCoins, CraftTokens, VoteTokens",
                    "§e/trade §6" + p.getName() + " §f- §7Obchodování s hráčem",
                    "§e/fr §f- §7Friends a psaní si s kamarády přes celý server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, "§aOdkazy na naše stránky",
                    "§eNovinky: §7https://news.craftmania.cz/",
                    "§eFórum: §7https://craftmania.cz/",
                    "§eDiscord: §7https://discord.gg/craftmania/",
                    "§eStatus page: §7https://status.craftmania.cz/",
                    "§eStatistiky: §7https://stats.craftmania.cz/",
                    "",
                    "§bKliknutím zde, se ti zobrazí klikací odkazy v chatu");

            ItemStack guides = ItemFactory.create(Material.BOOK, "§aNávody",
                    "§7Přehled všech dostupných návodů",
                    "§7na naší hlavní WIKI!",
                    "",
                    "§eKliknutím zde zobrazíš přehled");

            ItemStack levels = ItemFactory.create(Material.NETHER_STAR, "§aAchievements & Rewards",
                    "§7Chceš dosáhnout novýc cílů a mít tak vyšší level?",
                    "§7Server level ti totiž odemyká mnoho bonusových",
                    "§7práv a možností, co dělat na serveru.",
                    "",
                    "§e/level §f- §7Zobrazení globální levelu (součet všech)",
                    "§e/level survival §f- §7Zobrazení levelu pro Survival",
                    "§e/rewards §f- §7Zobrazení odměn za levely",
                    "",
                    "§eKliknutím zobrazíš rewards odměny");

            inv.setItem(12, poz);
            inv.setItem(13, pozH);
            inv.setItem(14, pozD);

            inv.setItem(21, vip);
            inv.setItem(22, hlasovani);
            inv.setItem(23, ser);

            inv.setItem(30, guides);
            inv.setItem(31, conn);
            inv.setItem(32, levels);

            p.openInventory(inv);
        }
        if (Main.getServerType() == ServerType.SURVIVAL) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Survival");

            ItemStack res = ItemFactory.create(Material.WOODEN_HOE, "§aVytvoření a první kroky s Residencí", "",
                    "§7K vytvoření residence budeš potřebovat",
                    "§7dřevěnou motyku, najdeš ji v kitu §b/kit starter",
                    "§7Jako další krok označ motykou dva body - pravým/levým tlačítkem.",
                    "§7Poté napiš §e/res select vert §f- §7Vybereš výšku Y od 0 do 256",
                    "§7Dále použij příkaz §e/res create nazevResidence",
                    "",
                    "§e/res create nazevResidence §f- §fVytvoření residence",
                    "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack resV = ItemFactory.create(Material.IRON_HOE, "§aVlajky a nastavení v Residenci", "",
                    "§cZákladní příkaz:",
                    "§e/res set §6nazevResidence nazevVlajky hodnota",
                    "",
                    "§cNejpoužívanější vlajky:",
                    "§emove §f- §7Pohyb v residenci",
                    "§ebuild §f- §7Stavění v residenci",
                    "§epvp §f- §7PVP v residenci",
                    "§euse §f- §7Používání tlačítek, páček atd.",
                    "§emonsters §f- §7Spawn monster",
                    "§eanimals §f- §7Spawn zvířat",
                    "§eignite §f- §7Používání zapalovače",
                    "§efirespread §f- §7Hoření a rozšiřování ohně",
                    "",
                    "§cPříklady:",
                    "§8- §f/res set mojeres build false",
                    "§8- §f/res set mojeres monters true",
                    "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack resF = ItemFactory.create(Material.DIAMOND_HOE, "§aPřidání kamarádů a další příkazy", "",
                    "§e/res padd §6" + p.getName() + " §f- §7Přidání hráče do residence",
                    "§e/res pdel §6" + p.getName() + " §f- §7Odebrání z residence",
                    "§e/res list §f- §7Seznam všech tvých residencí",
                    "§e/res limits §f- §7Zobrazí limity pro zabrání území",
                    "§e/res tp §6nazev §f- §7Teleport na residenci",
                    "§e/res tpset §f- §7Nastavení teleportu",
                    "§e/res give §6název nick §f- §7Přepíšeš residenci vybranému hráči", "",
                    "§bKliknutím zobrazíš odkaz na celý návod na Wiki");

            ItemStack vip = ItemFactory.create(Material.EMERALD, "§aNákup VIP",
                    "§7Pokud si chceš zakoupit VIP,", "§7tak kliknutím zde se ti zobrazí přehled",
                    "§7všech dostupných VIP na tomto serveru.", "", "§bKlikni pro zobrazení VIP");

            ItemStack aukce = ItemFactory.create(Material.CAKE, "§aAukce §c§l(dočasně off)",
                    "§7Pomocí aukcí můžeš hráčům prodávat", "§7různé itemy nebo bloky.", "",
                    "§cVytvoření aukce:", "§7K vytvoření aukce, drž item, co chceš", "§7prodat a napiš tento příkaz:",
                    "§e/au start §61 1000 100 1d §8- §7Vytvoří aukci na 1 den",
                    "",
                    "§cPopis příkazu:", "§e/au start [pocetItemu] [zaklCena] [min.nabHrace] [cas]",
                    "§8- §6pocetItemu §f- §7Je počet prodávaných itemu",
                    "§7nebo bloku př. 1/2/64",
                    "§8- §6zaklCena §f- §7Je cena, se kterou aukce začíná",
                    "§8- §6min.nabHrace §f- §7Je minimální částka, ",
                    "§7kterou hráč může přidat do aukce",
                    "§8- §6cas §f- §7Určuje konec aukce př. 1m/1h/1d ",
                    "§7(lze i kombinovat 1d12h)");

            ItemStack levels = ItemFactory.create(Material.NETHER_STAR, "§aAchievements & Rewards",
                    "§7Chceš dosáhnout novýc cílů a mít tak vyšší level?",
                    "§7Server level ti totiž odemyká mnoho bonusových",
                    "§7práv a možností, co dělat na serveru.",
                    "",
                    "§e/level §f- §7Zobrazení globální levelu (součet všech)",
                    "§e/level survival §f- §7Zobrazení levelu pro Survival",
                    "§e/rewards §f- §7Zobrazení odměn za levely",
                    "",
                    "§eKliknutím zobrazíš rewards odměny");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, "§aHlasování",
                    "§7Hlasovat můžeš každý 1x za 2 hodiny na CzechCraftu,",
                    "§7a 1x za 24h na CraftListu.",
                    "",
                    "§fKaždý hlas: §610 CC §f+ §aVoteToken",
                    "§f25% šance: §625 CC",
                    "§f5% šance: §650 CC",
                    "§f1% šance: §6100 CC",
                    "",
                    "§bKaždý měsíc můžeš získat tyto bonusy:",
                    "§f20 hlasů: §6200 CC",
                    "§f40 hlasů: §6300 CC",
                    "§f60 hlasů: §6500 CC",
                    "",
                    "§6Hlasovat lze i offline!",
                    "",
                    "§eKliknutím zobrazíš odkazy na hlasování!");

            ItemStack guides = ItemFactory.create(Material.BOOK, "§aNávody",
                    "§7Přehled všech dostupných návodů",
                    "§7na naší hlavní WIKI!",
                    "",
                    "§eKliknutím zde zobrazíš přehled");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, "§aOstatní příkazy a teleporty po serveru",
                    "", "§e/shop §f- §7Otevře menu s server shopem",
                    "§e/cshop §f- §7Otevře CoinShop s CM měnou",
                    "§e/cc§7, §e/ct§7, §e/vt- §7Stav CraftCoins, CraftTokens, VoteTokens",
                    "§e/trade §6" + p.getName() + " §f- §7Obchodování s hráčem",
                    "§e/fr §f- §7Friends a psaní si s kamarády přes celý server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, "§aOdkazy na naše stránky", "",
                    "§eNovinky: §7https://news.craftmania.cz/",
                    "§eFórum: §7https://craftmania.cz/",
                    "§eDiscord: §7https://discord.gg/craftmania/",
                    "§eStatus page: §7https://status.craftmania.cz/",
                    "§eStatistiky: §7https://stats.craftmania.cz/",
                    "",
                    "§bKliknutím zde, se ti zobrazí klikací odkazy v chatu");

            inv.setItem(12, res);
            inv.setItem(13, resV);
            inv.setItem(14, resF);

            inv.setItem(20, vip);
            inv.setItem(21, aukce);
            inv.setItem(22, levels);
            inv.setItem(23, guides);
            inv.setItem(24, hlasovani);

            inv.setItem(30, ser);
            inv.setItem(32, conn);

            p.openInventory(inv);
        }
    }
}
