package cz.wake.manager.utils.inventories;

import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class VIPMenu_inv implements InventoryProvider {

    String server = "2beChecked";
    //Data hlav
    String goldBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRiZjg5M2ZjNmRlZmFkMjE4Zjc4MzZlZmVmYmU2MzZmMWMyY2MxYmI2NTBjODJmY2NkOTlmMmMxZWU2In19fQ==";
    String diamondBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMTU5N2RjZTRlNDA1MWU4ZDVhNTQzNjQxOTY2YWI1NGZiZjI1YTBlZDYwNDdmMTFlNjE0MGQ4OGJmNDhmIn19fQ==";
    String emeraldBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5MDZkNjg4ZTY1ODAyNTY5ZDk3MDViNTc5YmNlNTZlZGM4NmVhNWMzNmJkZDZkNmZjMzU1MTZhNzdkNCJ9fX0=";
    String obsidianBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MGI4N2Q1MjI3MWQyYTc1NWRlZGM4Mjg3N2UwZWQzZGY2N2RjYzQyZWE0NzllYzE0NjE3NmIwMjc3OWE1In19fQ==";
    String serverBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NhNDVlZjU4MjFhOGIxMDdjYmZiYTdkNjZlOTk3ZmI2YWJlNTUyMWMxNTVjZWUyZjI0YjM0YjNkOTFhNSJ9fX0=";
    String globeBlockData = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=";

    @Override
    public void init(Player player, InventoryContents contents) {
        server = getCurrentServer();

        Pagination pagination = contents.pagination();
        ClickableItem[] items = new ClickableItem[5];
        pagination.setItems(items);
        pagination.setItemsPerPage(1);

        switch (pagination.getPage()) {
            case 0: { //VIP Menu
                MainMenu(contents, player, pagination);
                break;
            }
            case 1: { //GOLDEN VIP
                GVIPMenu(contents, player, pagination);
                break;
            }
            case 2: { //DIAMOND VIP
                DVIPMenu(contents, player, pagination);
                break;
            }
            case 3: { //EMERALD VIP
                EVIPMenu(contents, player, pagination);
                break;
            }
            case 4: { //OBSIDIAN VIP
                OVIPMenu(contents, player, pagination);
                break;
            }
        }


    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }

    //VIP MENUS
    private void MainMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        contents.set(0, 1, ClickableItem.empty(getGlobalVipHead())); //Global VIP head
        contents.set(0, 2, ClickableItem.empty(getServerVipHead())); //Server VIP head
        contents.set(0, 7, ClickableItem.empty(getCurrentPlayerHead(player))); //Tvoje VIP head
        contents.set(2, 1, ClickableItem.of(getGVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(1).getPage()))); //Golden VIP sender head
        contents.set(2, 3, ClickableItem.of(getDVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(2).getPage()))); //Diamond VIP head sender
        contents.set(2, 5, ClickableItem.of(getEVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(3).getPage()))); //Emerald VIP head sender
        contents.set(2, 7, ClickableItem.of(getOVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(4).getPage())));//Obsidian VIP head sender
    }
    private void GVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.ORANGE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        contents.set(0, 7, ClickableItem.empty(getGVIPvyhodyGlobalItem()));
        contents.set(0, 1, ClickableItem.empty(getGVIPvyhodyServerItem()));
        contents.set(2, 1, ClickableItem.of(getGVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getGVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.AQUA + "Diamond VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void DVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        contents.set(0, 1, ClickableItem.empty(getDVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getDVIPvyhodyGlobalItem()));
        contents.set(2, 1, ClickableItem.of(getDVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getDVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.GREEN + "Emerald VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void EVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        contents.set(0, 1, ClickableItem.empty(getEVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getEVIPvyhodyGlobalItem()));
        contents.set(2, 1, ClickableItem.of(getEVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getEVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.BLUE + "Obsidian VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void OVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        contents.set(0, 1, ClickableItem.empty(getOVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getOVIPvyhodyGlobalItem()));
        contents.set(2, 1, ClickableItem.of(getOVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getOVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
    }
    //VIP MENUS

    //VIP MENU HEADS
    private ItemStack getGlobalVipHead() {
        List<String> lore = new ArrayList<String>();
        lore.add("§7VIP, které platí po celém serveru!");
        lore.add("");
        lore.add("§7Zahrnuje: ");
        lore.add("§e*§f Survival");
        lore.add("§e*§f Skyblock");
        lore.add("§e*§f Creative");
        lore.add("§e*§f Vanilla");
        lore.add("§e*§f Prison");
        return createHeadFromData(globeBlockData, ChatColor.AQUA + "Global VIP", lore);
    }
    private ItemStack getServerVipHead() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "VIP, které platí pouze na tomto serveru!");
        return createHeadFromData(serverBlockData, ChatColor.GOLD + "Server VIP", lore);
    }
    private ItemStack getCurrentPlayerHead(Player player) {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "SoonTM");
        return createHead(player.getName(), ChatColor.RED + "Tvoje VIP", lore);
    }
    //VIP MENU HEADS

    //VIP data (zde se mění výhody)
    List <String> getGVIPvyhodyServerList() {
        List <String> GVIPvyhody = new ArrayList<>();
        GVIPvyhody.add("§7Toto VIP zahrnuje:");
        GVIPvyhody.add("§e· §fPrefix v chatu a tablistu");
        GVIPvyhody.add("§e· §fPřipojení na plné servery");
        GVIPvyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        GVIPvyhody.add("§e· §fGlowing postavy §a/glow");
        GVIPvyhody.add("§e· §fZískání vlastní hlavy §a/skull");
        GVIPvyhody.add("§e· §fNasazení bloku na hlavu §a/hat");
        GVIPvyhody.add("§e· §fEmotes §a/emote");
        GVIPvyhody.add("§e· §fBarevné psaní do chatu, kovadliny a cedulky");
        GVIPvyhody.add("§e· §fNastavení barvy psaní §a/chatcolor");
        GVIPvyhody.add("§e· §fArmorStandEditor - úprava armorstandů");
        GVIPvyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        GVIPvyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        GVIPvyhody.add("§e· §fMožnost zobrazit držený item v ruce §a:item:");
        GVIPvyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        GVIPvyhody.add("§e· §fOchrana proti dropům v normálním světě. Neplatí pro PVP!");
        GVIPvyhody.add("§e· §fPřístup k particles v §a/cosmetics");
        GVIPvyhody.add("§e· §fVirtuální Beacon §a/beacon");
        GVIPvyhody.add("§e· §fLimit nastavení home - 2x");
        GVIPvyhody.add("§e· §fLimit vytvoření aukcí - 7x");
        GVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (1x)");
        GVIPvyhody.add("§e· §fSkyblock Minioni - Limit 7x");
        switch (getCurrentServer()) {
            case "Creative": {
                GVIPvyhody.add("§e· §fMaximální počet pozemků až 50 (normal 10)");
                break;
            }
        }
        GVIPvyhody.add("");
        GVIPvyhody.add("§cToto VIP platí pouze na " + getCurrentServer() + " serveru!");
        return GVIPvyhody;
    }

    List <String> getGVIPvyhodyGlobalList() {
        List <String> GVIPvyhody = new ArrayList<>();
        GVIPvyhody.add("§7Toto VIP zahrnuje:");
        GVIPvyhody.add("§e· §fPrefix v chatu a tablistu");
        GVIPvyhody.add("§e· §fPřipojení na plné servery");
        GVIPvyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        GVIPvyhody.add("§e· §fGlowing postavy §a/glow");
        GVIPvyhody.add("§e· §fZískání vlastní hlavy §a/skull");
        GVIPvyhody.add("§e· §fNasazení bloku na hlavu §a/hat");
        GVIPvyhody.add("§e· §fEmotes §a/emote");
        GVIPvyhody.add("§e· §fBarevné psaní do chatu, kovadliny a cedulky");
        GVIPvyhody.add("§e· §fNastavení barvy psaní §a/chatcolor");
        GVIPvyhody.add("§e· §fArmorStandEditor - úprava armorstandů");
        GVIPvyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        GVIPvyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        GVIPvyhody.add("§e· §fMožnost zobrazit držený item v ruce §a:item:");
        GVIPvyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        GVIPvyhody.add("§e· §fOchrana proti dropům itemů. Neplatí pro PVP!");
        GVIPvyhody.add("§e· §fPřístup k particles v §a/cosmetics");
        GVIPvyhody.add("§e· §fVirtuální Beacon §a/beacon");
        GVIPvyhody.add("§e· §fLimit nastavení home - 2x");
        GVIPvyhody.add("§e· §fLimit vytvoření aukcí - 7x");
        GVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (1x)");
        GVIPvyhody.add("§e· §fSkyblock Minioni - Limit 7x");
        GVIPvyhody.add("");
        GVIPvyhody.add("§7Lobby extra server výhody:");
        GVIPvyhody.add("§e· §fFly libovolně na lobby");
        GVIPvyhody.add("§e· §fJoin zpráva o připojení na lobby");
        GVIPvyhody.add("§e· §fMěsíční bonus §b500 CraftCoins");
        GVIPvyhody.add("");
        GVIPvyhody.add("§7Creative extra server výhody:");
        GVIPvyhody.add("§e· §fMaximální počet pozemků (50)");
        return GVIPvyhody;
    }
    List <String> getGVIPnakupServerList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "50kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "2€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }
    List <String> getGVIPnakupGlobalList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "75kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "3€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }
    List <String> getDVIPvyhodyServerList() {
        List <String> DVIPvyhody = new ArrayList<>();
        DVIPvyhody.add("§7Toto VIP zahrnuje:");
        DVIPvyhody.add("§e· §6Všechny výhody z Golden " + getCurrentServer() + " VIP");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Oproti Golden VIP získáš navíc:");
        DVIPvyhody.add("§e· §fNEO - Zobrazení spawnratu mobu §a/ll");
        DVIPvyhody.add("§e· §fNastavení vlastního času §a/ptime");
        DVIPvyhody.add("§e· §fInspekce zníčených bloků §a/co inspect");
        DVIPvyhody.add("§e· §fLimit nastavení home - 3x");
        DVIPvyhody.add("§e· §fLimit vytvoření aukcí - 10x");
        DVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (2x)");
        //DVIPvyhody.add("§e· §fMožnost vypnout si zobrazování veřejného chatu");
        DVIPvyhody.add("§e· §fOchrana proti dropům expů (neplatí pro PvP)");
        switch (getCurrentServer()) {
            case "Creative": {
                DVIPvyhody.add("§e· §fMaximální počet pozemků až 100 (normal 10)");
                break;
            }
        }
        DVIPvyhody.add("");
        DVIPvyhody.add("§cToto VIP platí pouze na " + getCurrentServer() + " serveru!");
        return DVIPvyhody;
    }
    List <String> getDVIPvyhodyGlobalList() {
        List <String> DVIPvyhody = new ArrayList<>();
        DVIPvyhody.add("§7Toto VIP zahrnuje:");
        DVIPvyhody.add("§e· §6Všechny výhody z Golden Global VIP");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Oproti Golden VIP získáš navíc:");
        DVIPvyhody.add("§e· §fNEO - Zobrazení spawnratu mobu §a/ll");
        DVIPvyhody.add("§e· §fNastavení vlastního času §a/ptime");
        DVIPvyhody.add("§e· §fInspekce zníčených bloků §a/co inspect");
        //DVIPvyhody.add("§e· §fMožnost vypnout si zobrazování veřejného chatu");
        DVIPvyhody.add("§e· §fOchrana proti dropům expů (neplatí pro PvP)");
        DVIPvyhody.add("§e· §fLimit nastavení home - 3x");
        DVIPvyhody.add("§e· §fLimit vytvoření aukcí - 10x");
        DVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (2x)");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Lobby extra server výhody:");
        DVIPvyhody.add("§e· §fFly libovolně na lobby");
        DVIPvyhody.add("§e· §fJoin zpráva o připojení na lobby");
        DVIPvyhody.add("§e· §fMěsíční bonus §b1,000 CraftCoins");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Creative extra server výhody:");
        DVIPvyhody.add("§e· §fMaximalní počet pozemků (100)");
        return DVIPvyhody;
    }
    List <String> getDVIPnakupServerList() {
        List <String> DVIPnakup = new ArrayList<>();
        DVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "100kč");
        DVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "4€");
        DVIPnakup.add("");
        DVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return DVIPnakup;
    }
    List <String> getDVIPnakupGlobalList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "180kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "7€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }
    List <String> getEVIPvyhodyServerList() {
        List <String> EVIPvyhody = new ArrayList<>();
        EVIPvyhody.add("§7Toto VIP zahrnuje:");
        EVIPvyhody.add("§e· §6Všechny výhody z Golden " + getCurrentServer() + " VIP");
        EVIPvyhody.add("§e· §bVšechny výhody z Diamond " + getCurrentServer() + " VIP");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Oproti Diamond VIP získáš navíc:");
        EVIPvyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        EVIPvyhody.add("§e· §fLimit nastavení home - 4x");
        EVIPvyhody.add("§e· §fLimit vytvoření aukcí - 14x");
        EVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (4x)");
        switch (getCurrentServer()) {
            case "Survival": {
                EVIPvyhody.add("§e· §fMaximální počet residencí 6 (normal 3)");
                break;
            }
            case "Creative": {
                EVIPvyhody.add("§e· §fMaximální počet pozemků (150)");
                EVIPvyhody.add("§e· §fPristup k WorldEditu");
                break;
            }
        }
        EVIPvyhody.add("");
        EVIPvyhody.add("§cToto VIP platí pouze na " + getCurrentServer() + " serveru!");
        return EVIPvyhody;
    }
    List <String> getEVIPvyhodyGlobalList() {
        List <String> EVIPvyhody = new ArrayList<>();
        EVIPvyhody.add("§7Toto VIP zahrnuje:");
        EVIPvyhody.add("§e· §6Všechny výhody z Golden Global VIP");
        EVIPvyhody.add("§e· §bVšechny výhody z Diamond Global VIP");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Oproti Diamond VIP získáš navíc:");
        EVIPvyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        EVIPvyhody.add("§e· §fLimit nastavení home - 4x");
        EVIPvyhody.add("§e· §fLimit vytvoření aukcí - 14x");
        EVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (4x)");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Lobby extra server výhody:");
        EVIPvyhody.add("§e· §fFly libovolně na lobby");
        EVIPvyhody.add("§e· §fJoin zpáva o připojení na lobby");
        EVIPvyhody.add("§e· §fMěsíční bonus §b1,500 CraftCoins");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Creative extra server výhody:");
        EVIPvyhody.add("§e· §fMaximální počet pozemků (150)");
        EVIPvyhody.add("§e· §fPřístup k WorldEditu");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Survival extra výhody:");
        EVIPvyhody.add("§e· §fMaximalni pocet residenci 6 (normal 3)");
        return EVIPvyhody;
    }
    List <String> getEVIPnakupServerList() {
        List <String> EVIPnakup = new ArrayList<>();
        EVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "180kč");
        EVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "7€");
        EVIPnakup.add("");
        EVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return EVIPnakup;
    }
    List <String> getEVIPnakupGlobalList() {
        List <String> EVIPnakup = new ArrayList<>();
        EVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "390kč");
        EVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "15€");
        EVIPnakup.add("");
        EVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return EVIPnakup;
    }
    List <String> getOVIPvyhodyServerList() {
        List <String> OVIPvyhody = new ArrayList<>();
        OVIPvyhody.add("§7Toto VIP zahrnuje:");
        OVIPvyhody.add("§e· §6Všechny výhody z Golden " + getCurrentServer() + " VIP");
        OVIPvyhody.add("§e· §bVšechny výhody z Diamond " + getCurrentServer() + " VIP");
        OVIPvyhody.add("§e· §aVšechny výhody z Emerald " + getCurrentServer() + " VIP");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Oproti Emerald VIP získáš navíc:");
        OVIPvyhody.add("§e· §fArtMap - kreslení na mapu §a/artmap");
        OVIPvyhody.add("§e· §fLimit nastavení home - 5x");
        OVIPvyhody.add("§e· §fLimit vytvoření aukcí - 20x");
        OVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (6x)");
        OVIPvyhody.add("§e· §fSkyblock Minioni - Limit 15x");
        switch (getCurrentServer()) {
            case "Survival": {
                OVIPvyhody.add("§e· §fMaximální počet residencí 8 (normal 3)");
                break;
            }
            case "Creative": {
                OVIPvyhody.add("§e· §fMaximální počet pozemků (200)");
                break;
            }
        }
        OVIPvyhody.add("");
        OVIPvyhody.add("§cToto VIP platí pouze na " + getCurrentServer() + " serveru!");
        return OVIPvyhody;
    }
    List <String> getOVIPvyhodyGlobalList() {
        List <String> OVIPvyhody = new ArrayList<>();
        OVIPvyhody.add("§7Toto VIP zahrnuje:");
        OVIPvyhody.add("§e· §6Všechny výhody z Golden Global VIP");
        OVIPvyhody.add("§e· §bVšechny výhody z Diamond Global VIP");
        OVIPvyhody.add("§e· §aVšechny výhody z Emerald Global VIP");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Oproti Emerald VIP získáš navíc:");
        OVIPvyhody.add("§e· §fArtMap - kreslení na mapu §a/artmap");
        OVIPvyhody.add("§e· §fLimit nastavení home - 5x");
        OVIPvyhody.add("§e· §fLimit vytvoření aukcí - 20x");
        OVIPvyhody.add("§e· §fVlastní warpy - vlastní warpy (6x)");
        OVIPvyhody.add("§e· §fSkyblock Minioni - Limit 15x");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Lobby extra server výhody:");
        OVIPvyhody.add("§e· §fFly libovolně na lobby");
        OVIPvyhody.add("§e· §fJoin zpráva po připojení na lobby");
        OVIPvyhody.add("§e· §fMěsíční bonus §b2,000 CraftCoins");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Creative extra server výhody:");
        OVIPvyhody.add("§e· §fMaximální počet pozemků (200)");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Survival extra výhody:");
        OVIPvyhody.add("§e· §fMaximální počet residencí 8 (normal 3)");
        return OVIPvyhody;
    }
    List <String> getOVIPnakupServerList() {
        List <String> OVIPnakup = new ArrayList<>();
        OVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "390kč");
        OVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "15€");
        OVIPnakup.add("");
        OVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return OVIPnakup;
    }
    List <String> getOVIPnakupGlobalList() {
        List <String> OVIPnakup = new ArrayList<>();
        OVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "1040kč");
        OVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "40€");
        OVIPnakup.add("");
        OVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return OVIPnakup;
    }
    //VIP data

    //GOLDEN VIP HEADS
    private ItemStack getGVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Nejnižší VIP, které ale obsahuje");
        lore.add(ChatColor.GRAY + "mnoho výhod, které určitě chceš!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromData(goldBlockData, ChatColor.GOLD + "Golden VIP", lore);
    }
    private ItemStack getGVIPvyhodyServerItem() {
        List <String> GVIPvyhody = getGVIPvyhodyServerList();
        return createHeadFromData(goldBlockData, ChatColor.GOLD + "Golden " + getCurrentServer() + " VIP", GVIPvyhody);
    }
    private ItemStack getGVIPvyhodyGlobalItem() {
        List <String> GVIPvyhody = getGVIPvyhodyGlobalList();
        return createHeadFromData(goldBlockData, ChatColor.GOLD + "Golden Global VIP", GVIPvyhody);
    }
    private ItemStack getGVIPnakupServerItem() {
        List <String> GVIPnakup = getGVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 30 dní", GVIPnakup);
    }
    private ItemStack getGVIPnakupGlobalItem() {
        List <String> GVIPnakup = getGVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 30 dní", GVIPnakup);
    }
    //GOLDEN VIP HEADS

    //DIAMOND VIP HEADS
    private ItemStack getDVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Vyšší VIP, se kterým všem");
        lore.add(ChatColor.GRAY + "ukážeš kdo je tu pán!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromData(diamondBlockData, ChatColor.AQUA + "Diamond VIP", lore);
    }
    private ItemStack getDVIPvyhodyServerItem() {
        List <String> DVIPvyhody = getDVIPvyhodyServerList();
        return createHeadFromData(diamondBlockData, ChatColor.AQUA + "Diamond " + getCurrentServer() + "VIP", DVIPvyhody);
    }
    private ItemStack getDVIPvyhodyGlobalItem() {
        List <String> DVIPvyhody = getDVIPvyhodyGlobalList();
        return createHeadFromData(diamondBlockData, ChatColor.AQUA + "Diamond Global VIP", DVIPvyhody);
    }
    private ItemStack getDVIPnakupServerItem() {
        List <String> DVIPnakup = getDVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 60 dní", DVIPnakup);
    }
    private ItemStack getDVIPnakupGlobalItem() {
        List <String> DVIPnakup = getDVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 60 dní", DVIPnakup);
    }
    //DIAMOND VIP HEADS

    //EMERALD VIP HEADS
    private ItemStack getEVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Čím víc výhod, tím víc VIP!");
        lore.add(ChatColor.GRAY + "Čím lepší VIP, tím víc výhod!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromData(emeraldBlockData, ChatColor.GREEN + "Emerald VIP", lore);
    }
    private ItemStack getEVIPvyhodyServerItem() {
        List <String> EVIPvyhody = getEVIPvyhodyServerList();
        return createHeadFromData(emeraldBlockData, ChatColor.GREEN + "Emerald " + getCurrentServer() + " VIP", EVIPvyhody);
    }
    private ItemStack getEVIPvyhodyGlobalItem() {
        List <String> EVIPvyhody = getEVIPvyhodyGlobalList();
        return createHeadFromData(emeraldBlockData, ChatColor.GREEN + "Emerald Global VIP", EVIPvyhody);
    }
    private ItemStack getEVIPnakupServerItem() {
        List <String> EVIPnakup = new ArrayList<String>();
        EVIPnakup = getEVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 90 dní", EVIPnakup);
    }
    private ItemStack getEVIPnakupGlobalItem() {
        List <String> EVIPnakup = getEVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 90 dní", EVIPnakup);
    }
    //EMERALD VIP HEADS

    //OBSIDIAN VIP HEADS
    private ItemStack getOVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Největší a nejlepší VIP,");
        lore.add(ChatColor.GRAY + "tak vělký, že z tebe bude");
        lore.add(ChatColor.GRAY + "na serveru Lich King!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromData(obsidianBlockData, ChatColor.BLUE + "Obsidian VIP", lore);
    }
    private ItemStack getOVIPvyhodyServerItem() {
        List <String> OVIPvyhody = getOVIPvyhodyServerList();
        return createHeadFromData(obsidianBlockData, ChatColor.BLUE + "Obsidian " + getCurrentServer() + " VIP", OVIPvyhody);
    }
    private ItemStack getOVIPvyhodyGlobalItem() {
        List <String> OVIPvyhody = getOVIPvyhodyGlobalList();
        return createHeadFromData(obsidianBlockData, ChatColor.BLUE + "Obsidian Global VIP", OVIPvyhody);
    }
    private ItemStack getOVIPnakupServerItem() {
        List <String> OVIPnakup = getOVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip NAVŽDY!", OVIPnakup);
    }
    private ItemStack getOVIPnakupGlobalItem() {
        List <String> OVIPnakup = getOVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip NAVŽDY!", OVIPnakup);
    }
    //OBSIDIAN VIP HEADS


    //UTILS
    private void createColumnServerAndGlobalVip(InventoryContents contents) {
        ItemStack side = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = side.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "<- Server " + ChatColor.DARK_GRAY + "|" + ChatColor.GREEN + " Global ->");
        side.setItemMeta(meta);
        contents.fillColumn(contents.inventory().getColumns()/2, ClickableItem.empty(side));
    }
    private ItemStack createHead(String ownerName, String itemName, List <String> itemLore) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(ownerName);
        meta.setDisplayName(itemName);
        meta.setLore(itemLore);
        head.setItemMeta(meta);
        head.setAmount(1);
        return head;
    }
    private ItemStack createHeadFromData(String headData, String itemName, List <String> itemLore) {
        ItemStack sHead = new ItemStack(Material.PLAYER_HEAD, 1);
        sHead = SkullCreator.itemWithBase64(sHead, headData);
        SkullMeta sMeta = (SkullMeta) sHead.getItemMeta();
        sMeta.setDisplayName(itemName);
        sMeta.setLore(itemLore);
        sHead.setItemMeta(sMeta);
        sHead.setAmount(1);
        return sHead;
    }
    private ItemStack createArrow(String itemName) {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        item.setItemMeta(meta);
        item.setAmount(1);
        return item;
    }
    private ItemStack createList(String itemName,  List <String> itemLore) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        item.setAmount(1);
        return item;
    }
    private void createRowsUpAndDown(InventoryContents contents, ItemStack itemMaterial) {
        ItemMeta meta = itemMaterial.getItemMeta();
        meta.setDisplayName(" ");
        itemMaterial.setItemMeta(meta);
        contents.fillRow(0, ClickableItem.empty(itemMaterial));
        contents.fillRow(contents.inventory().getRows()-1, ClickableItem.empty(itemMaterial));
    }
    private String getCurrentServer() {
        ServerType currentServer = Main.getServerType();
        if (currentServer == ServerType.CREATIVE)
            server = "Creative";
        else if (currentServer == ServerType.SURVIVAL)
            server = "Survival";
        else if (currentServer == ServerType.PRISON)
            server = "Prison";
        else if (currentServer == ServerType.SKYBLOCK)
            server = "Skyblock";
        else if (currentServer == ServerType.SKYCLOUD)
            server = "Skycloud";
        else if (currentServer == ServerType.SKYGRID)
            server = "Skygrid";
        else if (currentServer == ServerType.VANILLA)
            server = "Vanilla";
        else if (currentServer == ServerType.HARDCORE_VANILLA)
            server = "Hardcore Vanilla";
        else if (currentServer == ServerType.ANARCHY)
            server = "Anarchy";
        else if (currentServer == ServerType.UNKNOWN)
            server = "Unknown";
        return server;
    }
    private void sendStoreLink(Player player) {
        player.closeInventory();
        player.sendMessage("");
        player.sendMessage("§e§lOdkaz na Store:");
        player.sendMessage("§bhttps://store.craftmania.cz/");
        player.sendMessage("");
    }
    //UTILS
}
