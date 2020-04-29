package cz.wake.manager.commads;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.ServerType;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Help_command implements CommandExecutor {

    public static void registerCommand() {

        CommandAPI.getInstance().register("help", new String[]{"pomoc"}, null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (Main.getServerType() == ServerType.PRISON) {
                    player.performCommand("tutorial");
                } else {
                    openHelpMenu(player);
                }
            }
        });
    }


    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("help"))
                    && (ArrayOfString.length == 0)) {
                if (Main.getServerType() == ServerType.PRISON) {
                    player.performCommand("tutorial");
                } else {
                    openHelpMenu(player);
                }
            }
        }
        return true;
    }

    private static void openHelpMenu(Player p) {
        if (Main.getServerType() == ServerType.SKYBLOCK) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Skyblock");

            ItemStack is = ItemFactory.create(Material.GRASS, (byte) 0, "§a§lVytvoreni a nastaveni ostrova", "",
                    "§e/is §f- §7Vytvori novy ostrov nebo teleportuje",
                    "§7na jiz vytvoreny tvuj ostrov.",
                    "§e/is sethome §f- §7Nastavi teleport pri pouziti prikazu /is",
                    "§e/is level §f- §7Zobrazi level tveho ostrova a pocet smrti",
                    "§e/is top §f- §7Zobrazi TOP 10 hracu s nejvetsimy ostrovy",
                    "§e/is lock §f- §7Uzamkne ostrov a nikdo krome AT,",
                    "§7se k tobe nedostane.",
                    "§7pri pouziti Warp prikazu",
                    "§e/is restart §f- §7Restartuje ostrov. §c§lSMAZE VSE!",
                    "§e/is leave §f- §7Opustis ostrov... :(",
                    "§eK nastaveni Warpu poloz cedulku a na prvni radek",
                    "§enapis: §f[welcome]");

            ItemStack isTeam = ItemFactory.create(Material.MAGMA_CREAM, (byte) 0, "§a§lPridani a odebrani hracu na ostrove", "",
                    "§e/is invite §6" + p.getName() + " §f- §7Prida hrace na tvuj ostrov",
                    "§e/is accept §f- §7Potvrdi zadost o pridani od jineho hrace",
                    "§e/is reject §f- §7Zrusi zadost od jineho hrace",
                    "§e/is ban §6" + p.getName() + " §f- §7Zabanuje hrace na tvem ostrove,",
                    "§7jiz nebude moc vstoupit",
                    "§e/is makeleader §6" + p.getName() + " §f- §7Nastavi noveho majitele",
                    "§7tveho ostrova");

            ItemStack oIs = ItemFactory.create(Material.MELON, (byte) 0, "§a§lOstatni prikazy k ostrovum", "",
                    "§e/is biomes §f- §7Nastaveni biomu na ostrove",
                    "§e/is settings §f §7Nastaveni ostrova pro hrace,",
                    "§7co nemas v Teamu.",
                    "§e/is warp §6" + p.getName() + " §f- §7Teleport na ostrov",
                    "§7jineho hrace.",
                    "§e/challenge §f- §7Seznam tvych challengu",
                    "", "§cWarp na ostrove vytvorit pomoci cedulky:",
                    "§71.radek = [WELCOME]");

            ItemStack jobs = ItemFactory.create(Material.IRON_PICKAXE, (byte) 0, "§a§lJobs", "",
                    "§cKazdy hrac smi mit maximalne 3 aktivni prace!",
                    "§e/jobs §f- §7Zakladni prikaz k ziskani napovedy",
                    "§e/jobs browse §f- §7Seznam vsech dostupnych praci",
                    "§e/jobs join §6nazevPrace §f- §7Pripojeni do prace",
                    "§e/jobs leave §6nazevPrace §f §7Odstoupeni z prace",
                    "§7(neztratis uroven prace)",
                    "§e/jobs stats §6" + p.getName() + " §f- §7Informace o hraci/sobe",
                    "§e/jobs gtop §f- §7Zobrazi TOP hrace v Jobs na serveru");

            ItemStack vip = ItemFactory.create(Material.EMERALD, (byte) 0, "§a§lNakup VIP", "",
                    "§7Pokud si chces zakoupit VIP,", "§7tak kliknutim zde se ti zobrazi prehled",
                    "§7vsech dostupnych VIP na tomto serveru.", "", "§bKlikni pro zobrazeni");

            ItemStack aukce = ItemFactory.create(Material.CAKE, (byte) 0, "§a§lAukce", "",
                    "§7Pomoci aukci muzes prodavat hracum", "§7nepotrebne itemy/bloky.", "",
                    "§cVytvoreni aukce:", "§7K vytvoreni aukce uchop item co chces", "§7prodat a napis tento prikaz:",
                    "§e/au start §61 1000 100 1d",
                    "§8Vytvori aukci na 1 den",
                    "", "§cPopis prikazu:", "§e/au start [pocetItemu] [zaklCena] [min.nabHrace] [cas]",
                    "§6pocetItemu §f- §7Je pocet prodavanych itemu",
                    "§7nebo bloku pr. 1/2/64",
                    "§6zaklCena §f- §7Je cena, se kterou aukce zacina",
                    "§6min.nabHrace §f- §7Je minimalni castka, ",
                    "§7kterou hrac muze pridat do aukce",
                    "§6cas §f- §7Urcuje konec aukce pr. 1m/1h/1d ",
                    "§7(lze i kombinovat 1d12h)");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, (byte) 0, "§a§lHlasovani", "",
                    "§cHlasovat muze kazdy 1x za 2 hodiny!",
                    "",
                    "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                    "§f25% sance: §625 CC",
                    "§f5% sance: §650 CC",
                    "§f1% sance: §6100 CC",
                    "",
                    "§bKazdy mesic muzes ziskat tyto bonusy!",
                    "§f20 hlasu: §6200 CC",
                    "§f40 hlasu: §6300 CC",
                    "§f60 hlasu: §6500 CC",
                    "",
                    "§eKliknutim zobrazis odkaz na hlasovani!");

            ItemStack guides = ItemFactory.create(Material.BOOK, (byte) 0, "§a§lNavody", "",
                    "§7Prehled vsech dostupnych navodu",
                    "§7na nasi hlavni WIKI!",
                    "",
                    "§eKliknutim zobrazis prehled");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, (byte) 0, "§a§lPrikazy a teleportace na serveru",
                    "", "§e/shop §f- §7Otevre menu s Shopem",
                    "§e/cshop §f- §7Otevre Coinshop",
                    "§e/cc §f- §7Stav CraftCoins",
                    "§e/trade §6" + p.getName() + " §f- §7Obchodovani s hraci",
                    "§e/fr §f- §7Friends a psani si s kamarady pres cely server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, (byte) 0, "§a§lOdkazy na nase dalsi servery", "",
                    "§eWeb: §7https://craftmania.cz",
                    "§eDiscord: §7https://discord.gg/craftmania",
                    "§eStatus page: §7https://status.craftmania.cz", "",
                    "§bKliknutim ti zobrazime klikaci odkazy v chatu!");

            inv.setItem(12, is);
            inv.setItem(13, isTeam);
            inv.setItem(14, oIs);

            inv.setItem(20, jobs);
            inv.setItem(21, aukce);
            inv.setItem(23, guides);
            inv.setItem(24, hlasovani);

            inv.setItem(30, ser);
            inv.setItem(31, conn);
            inv.setItem(32, vip);

            p.openInventory(inv);
        }
        if (Main.getServerType() == ServerType.CREATIVE) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Creative");

            ItemStack poz = ItemFactory.create(Material.DIRT, (byte) 2, "§a§lZakladni sprava pozemku", "",
                    "§e/p auto §f- §7Automaticky zabere nejblizsi pozemek",
                    "§e/p claim §f- §7Zabere pozemek na kterem stojis",
                    "§7(pokud neni zabrany)",
                    "§e/p home §f- §7Teleport na tvuj 1. pozemek",
                    "§e/p home:2 §f- §7Teleport na tvuj 2. pozemek",
                    "§e/p delete §f- §7Smaze pozemek kompletne!",
                    "§e/p clear §f- §7Vycisti pozemek od staveb",
                    "§e/p sethome §f- §7Nastavi na polohu kde jsi home",
                    "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack pozH = ItemFactory.create(Material.NETHERRACK, (byte) 0, "§a§lSprava hracu na pozemku", "",
                    "§e/p trust §6" + p.getName() + " §f- §7Prida hrace na pozemek",
                    "§e/p untrust §6" + p.getName() + " §f- §7Odebere hrace z pozemku",
                    "§e/p deny §6" + p.getName() + " §f- §7Zamezi pristup hraci na poz.",
                    "§e/p visit §6" + p.getName() + " §f- §7Navstivis pozemek jineho hrace",
                    "§e/p setowner §6" + p.getName() + " §f- §7Nastavi noveho majitele pozemku",
                    "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack pozD = ItemFactory.create(Material.OBSIDIAN, (byte) 0, "§a§lDalsi prikazy na pozemky", "",
                    "§e/p music §f- §7Zobrazi jukebox k prehravani hudby",
                    "§e/p kick " + p.getName() + " §f- §7Vykopne hrace z pozemku",
                    "§e/p merge auto §f- §7Spoji pozemky do jednoho",
                    "§cPodminkou je mit pozemky vedle sebe!",
                    "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack vip = ItemFactory.create(Material.EMERALD, (byte) 0, "§a§lNakup VIP", "",
                    "§7Pokud si chces zakoupit VIP,", "§7tak kliknutim zde se ti zobrazi prehled",
                    "§7vsech dostupnych VIP na tomto serveru.", "", "§bKlikni pro zobrazeni");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, (byte) 0, "§a§lHlasovani", "",
                    "§cHlasovat muze kazdy 1x za 2 hodiny!",
                    "",
                    "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                    "§f25% sance: §625 CC",
                    "§f5% sance: §650 CC",
                    "§f1% sance: §6100 CC",
                    "",
                    "§bKazdy mesic muzes ziskat tyto bonusy!",
                    "§f20 hlasu: §6200 CC",
                    "§f40 hlasu: §6300 CC",
                    "§f60 hlasu: §6500 CC",
                    "",
                    "§eKliknutim zobrazis odkaz na hlasovani!");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, (byte) 0, "§a§lPrikazy a teleportace na serveru",
                    "§e/cshop §f- §7Otevre Coinshop",
                    "§e/cc §f- §7Stav CraftCoins",
                    "§e/fr §f- §7Friends a psani si s kamarady pres cely server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, (byte) 0, "§a§lOdkazy na nase dalsi servery", "",
                    "§eWeb: §7https://craftmania.cz",
                    "§eDiscord: §7https://discord.gg/craftmania",
                    "§eStatus page: §7https://status.craftmania.cz", "",
                    "§bKliknutim ti zobrazime klikaci odkazy v chatu!");

            ItemStack guides = ItemFactory.create(Material.BOOK, (byte) 0, "§a§lNavody", "",
                    "§7Prehled vsech dostupnych navodu",
                    "§7na nasi hlavni WIKI!",
                    "",
                    "§eKliknutim zobrazis prehled");

            inv.setItem(12, poz);
            inv.setItem(13, pozH);
            inv.setItem(14, pozD);

            inv.setItem(21, vip);
            inv.setItem(22, hlasovani);
            inv.setItem(23, ser);

            inv.setItem(30, guides);
            inv.setItem(31, conn);

            p.openInventory(inv);
        }
        if (Main.getServerType() == ServerType.SURVIVAL) {
            Inventory inv = Bukkit.createInventory(null, 45, "Help pro Survival");

            ItemStack res = ItemFactory.create(Material.OAK_LOG, (byte) 2, "§a§lVytvoreni a prvni kroky s Residenci", "",
                    "§6K vytvoreni residence budes potrebovat",
                    "§6drevenou motyku, najdes ji v kitu §c/kit starter",
                    "§6Jako dalsi krok oznac motykou dva body - pravym/levym.",
                    "§6Napis §e/res select vert §f- §7Vybere Y od 0 do 256",
                    "§6Dale pouzij prikaz §e/res create nazevResidence",
                    "",
                    "§e/res create nazevResidence §f- §7Vytvoreni residence",
                    "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack resV = ItemFactory.create(Material.SANDSTONE, (byte) 2, "§a§lVlajky a nastaveni v Residenci", "",
                    "§cZakladni prikaz:",
                    "§e/res set §6nazevResidence nazevVlajky hodnota",
                    "",
                    "§cNejpouzivanejsi vlajky:",
                    "§emove §f- §7Pohyb v residenci",
                    "§ebuild §f- §7Staveni v residenci",
                    "§epvp §f- §7PVP v residenci",
                    "§euse §f- §7Pouzivani tlacitek, pacek atd.",
                    "§emonsters §f- §7Spawn monster",
                    "§eanimals §f- §7Spawn zvirat",
                    "§eignite §f- §7Pouzivani zapalovace",
                    "§efirespread §f- §7Horeni bloku",
                    "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack resF = ItemFactory.create(Material.PRISMARINE, (byte) 2, "§a§lPridani kamaradu a dalsi prikazy", "",
                    "§e/res padd §6" + p.getName() + " §f- §7Prida hrace do residence",
                    "§e/res pdel §6" + p.getName() + " §f- §7Odebere hrace z residence",
                    "§e/res list §f- §7Seznam vsech tvych residenci",
                    "§e/res limits §f- §7Zobrazi limity pro zabrani uzemi",
                    "§e/res tp §6nazev §f- §7Teleport na residenci",
                    "§e/res tpset §f- §7Nastaveni teleportu",
                    "§e/res give §6nazev nick §f- §7Daruje residenci vybranemu hraci", "",
                    "§bKliknutim zobrazis odkaz na cely navod!");

            ItemStack vip = ItemFactory.create(Material.EMERALD, (byte) 0, "§a§lNakup VIP", "",
                    "§7Pokud si chces zakoupit VIP,", "§7tak kliknutim zde se ti zobrazi prehled",
                    "§7vsech dostupnych VIP na tomto serveru.", "", "§bKlikni pro zobrazeni");

            ItemStack aukce = ItemFactory.create(Material.CAKE, (byte) 0, "§a§lAukce", "",
                    "§7Pomoci aukci muzes prodavat hracum", "§7nepotrebne itemy/bloky.", "",
                    "§cVytvoreni aukce:", "§7K vytvoreni aukce uchop item co chces", "§7prodat a napis tento prikaz:",
                    "§e/au start §61 1000 100 1d",
                    "§8Vytvori aukci na 1 den",
                    "", "§cPopis prikazu:", "§e/au start [pocetItemu] [zaklCena] [min.nabHrace] [cas]",
                    "§6pocetItemu §f- §7Je pocet prodavanych itemu",
                    "§7nebo bloku pr. 1/2/64",
                    "§6zaklCena §f- §7Je cena, se kterou aukce zacina",
                    "§6min.nabHrace §f- §7Je minimalni castka, ",
                    "§7kterou hrac muze pridat do aukce",
                    "§6cas §f- §7Urcuje konec aukce pr. 1m/1h/1d ",
                    "§7(lze i kombinovat 1d12h)");

            ItemStack levels = ItemFactory.create(Material.NETHER_STAR, (byte) 0, "§a§lAchievements & Rewards", "",
                    "§7Chceš dosáhnout novýc cílů a mít tak vyšší level?",
                    "§7Server level ti totiž odemyká mnoho bonusových",
                    "§7práv a možností, co dělat na serveru.",
                    "",
                    "§e/level §f- §7Zobrazení globální levelu (součet všech)",
                    "§e/level survival §f- §7Zobrazení levelu pro Survival",
                    "§e/rewards §f- §7Zobrazení odměn za levely",
                    "",
                    "§eKliknutím zobrazíš rewards odměny");

            ItemStack hlasovani = ItemFactory.create(Material.PAPER, (byte) 0, "§a§lHlasovani", "",
                    "§cHlasovat muze kazdy 1x za 2 hodiny!",
                    "",
                    "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                    "§f25% sance: §625 CC",
                    "§f5% sance: §650 CC",
                    "§f1% sance: §6100 CC",
                    "",
                    "§bKazdy mesic muzes ziskat tyto bonusy!",
                    "§f20 hlasu: §6200 CC",
                    "§f40 hlasu: §6300 CC",
                    "§f60 hlasu: §6500 CC",
                    "",
                    "§eKliknutim zobrazis odkaz na hlasovani!");

            ItemStack guides = ItemFactory.create(Material.BOOK, (byte) 0, "§a§lNavody", "",
                    "§7Prehled vsech dostupnych navodu",
                    "§7na nasi hlavni WIKI!",
                    "",
                    "§eKliknutim zobrazis prehled");

            ItemStack ser = ItemFactory.create(Material.DIAMOND, (byte) 0, "§a§lPrikazy a teleportace na serveru",
                    "", "§e/shop §f- §7Otevre menu s Shopem",
                    "§e/cshop §f- §7Otevre Coinshop",
                    "§e/cc §f- §7Stav CraftCoins",
                    "§e/trade §6" + p.getName() + " §f- §7Obchodovani s hraci",
                    "§e/fr §f- §7Friends a psani si s kamarady pres cely server",
                    "§e/lobby §7nebo §e/hub §f- §7Teleport na lobby");

            ItemStack conn = ItemFactory.create(Material.MAP, (byte) 0, "§a§lOdkazy na nase dalsi servery", "",
                    "§eNovinky: §7https://news.craftmania.cz/",
                    "§eForum: §7https://craftmania.cz/",
                    "§eDiscord: §7https://discord.gg/craftmania/",
                    "§eStatus page: §7https://status.craftmania.cz/",
                    "§eStatistiky: §7https://stats.craftmania.cz/",
                    "",
                    "§bKliknutim ti zobrazime klikaci odkazy v chatu!");

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
