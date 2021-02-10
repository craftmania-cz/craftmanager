package cz.wake.manager;

import co.aikar.commands.PaperCommandManager;
import cz.craftmania.craftlibs.sentry.CraftSentry;
import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.commads.staff.RawBroadcast;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.commads.staff.ServerSlots_command;
import cz.wake.manager.listener.*;
import cz.wake.manager.listener.suggestions.PlayerCommandSendListener;
import cz.wake.manager.managers.CshopManager;
import cz.wake.manager.perks.coloranvil.AnvilListener;
import cz.wake.manager.perks.general.*;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.twerking.TwerkEvent;
import cz.wake.manager.servers.global.LeaveDecayListener;
import cz.wake.manager.servers.hvanilla.BanTimesListener;
import cz.wake.manager.servers.skycloud.ItemDropListener;
import cz.wake.manager.servers.skycloud.VillagerDamageListener;
import cz.wake.manager.servers.skycloud.VillagerManager;
import cz.wake.manager.servers.vanilla.LecternBookTakeListener;
import cz.wake.manager.shop.TempShop;
import cz.wake.manager.sql.SQLManager;
import cz.wake.manager.utils.*;
import cz.wake.manager.utils.configs.Config;
import cz.wake.manager.utils.configs.ConfigAPI;
import cz.wake.manager.utils.prometheus.MetricsController;
import cz.wake.manager.utils.scoreboard.ScoreboardManager;
import cz.wake.manager.utils.scoreboard.ScoreboardProvider;
import cz.wake.manager.utils.tasks.ATAfkTask;
import cz.wake.manager.utils.tasks.*;
import cz.wake.manager.commads.VIP_command;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main extends JavaPlugin implements PluginMessageListener {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> at_list = new ArrayList<>();
    public HashMap<Player, Integer> at_afk = new HashMap<>();
    public ArrayList<Player> death_messages = new ArrayList<>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    private List<String> dontdrop_worlds = new ArrayList<>();
    public static Long restartTime = null;
    public static String restartReason = null;
    private MainGUI gui = new MainGUI();
    private ServerFactory sf = new ServerFactory();
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private SQLManager sql;
    private boolean testing = false;
    private static String mentionPrefix;
    private ConfigAPI configAPI;
    private CshopManager cshopManager;
    private static ScoreboardManager scoreboardManager = null;
    private static ScoreboardProvider scoreboardProvider = null;

    // Plugin dependencies
    private static boolean isPremiumVanishEnabled = false;
    private static boolean isCraftPackEnabled = false;
    private static boolean isLibsDisguiseEnabled = false;

    // Commands manager
    private PaperCommandManager manager;

    private static Main instance;

    private static ServerType serverType = ServerType.UNKNOWN;

    // Sentry
    private CraftSentry sentry = null;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        instance = this;

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Nacteni config souboru
        configAPI = new ConfigAPI(this);
        loadConfiguration();

        // ID serveru a typ
        serverType = resolveServerType();
        Log.withPrefix("Server zaevidovany jako: " + serverType.name());

        // Sentry integration
        if (!(Objects.requireNonNull(getConfig().getString("sentry-dsn")).length() == 0) && Bukkit.getPluginManager().isPluginEnabled("CraftLibs")) {
            String dsn = getConfig().getString("sentry-dsn");
            Log.normalMessage("Sentry integration je aktivní: §7" + dsn);
            sentry = new CraftSentry(dsn);
        } else {
            Log.normalMessage("Sentry integration neni aktivovana!");
        }

        // Premium Vanish
        if (this.getServer().getPluginManager().isPluginEnabled("PremiumVanish")) {
            isPremiumVanishEnabled = true;
        }

        if (this.getServer().getPluginManager().isPluginEnabled("CraftPack")) {
            isCraftPackEnabled = true;
        }

        if (this.getServer().getPluginManager().isPluginEnabled("LibsDisguise")) {
            isLibsDisguiseEnabled = true;
        }

        // Register eventu a prikazu
        loadListeners();

        manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");
        loadCommands();

        // HikariCP
        initDatabase();

        // Nastaveni hodnot
        testing = getConfig().getBoolean("testing");

        // Bungee channels
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        // Update ID stats task (1 min)
        if (!testing) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateServerTask(), 200, 1200);
            Log.withPrefix("Aktivace update serveru kazdych 60 vterin.");

            getServer().getScheduler().runTaskTimerAsynchronously(this, new ATCheckerTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Stalkeru");

            getServer().getScheduler().runTaskTimer(this, new ATAfkTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Afk checkeru");

            getServer().getScheduler().runTaskTimerAsynchronously(this, new VoteReminderTask(), 100, 1200);
        }

        if (serverType == ServerType.SKYCLOUD) {
            this.getServer().getRecipesFor(new ItemStack(Material.COARSE_DIRT)).clear();
            this.getServer().addRecipe(CustomCrafting.getSandCrafting());
            this.getServer().addRecipe(CustomCrafting.getNetherBrickRecipe());
            this.getServer().addRecipe(CustomCrafting.getGravelCutterRecipe());
            this.getServer().addRecipe(CustomCrafting.getShulkerShellRecipe());
            this.getServer().addRecipe(CustomCrafting.getDiamondRecipe());
            this.getServer().addRecipe(CustomCrafting.getClayBlockRecipe());
            this.getServer().addRecipe(CustomCrafting.getRedSandRecipe());
            this.getServer().addRecipe(CustomCrafting.getSaddleRecipe());
            this.getServer().addRecipe(CustomCrafting.getCoarseDirtRecipe());
        }
        this.getServer().addRecipe(CustomCrafting.getInvisibleItemFrame());

        // Nastaveni mention prefixu
        mentionPrefix = Main.getInstance().getConfig().getString("mentions.prefix");
        if (mentionPrefix == null) {
            mentionPrefix = "@";
        }
        Log.withPrefix("Mention prefix nastaven na: " + mentionPrefix);

        // Prometheus
        if (getConfig().getBoolean("prometheus.state", false)) {
            MetricsController.setup(this);
            Log.withPrefix("Aktivace Prometheus Endpointu na portu: " + getConfig().get("prometheus.port").toString());
        }

        // SkyCloud nastaveni
        if (serverType == ServerType.SKYCLOUD) {
            VillagerManager.loadChunksAndKill();
            VillagerManager.spawnVillagers();
        }

        Bukkit.getWorlds().forEach(world -> {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        });

        // Načtení Cshopu
        this.cshopManager = new CshopManager(this);
        this.cshopManager.loadCshop();

        // Načtení ScoreboardManageru
        if (getConfigAPI().getConfig("scoreboardConfig").getBoolean("scoreboard.enabled")) {
            Log.withPrefix("Aktivace Scoreboard!");
            scoreboardManager = new ScoreboardManager();
            scoreboardProvider = new ScoreboardProvider();
            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> getScoreboardManager().update(), 0L, getConfigAPI().getConfig("scoreboardConfig").getLong("scoreboard.refreshTime"));
        } else {
            Log.withPrefix("Scoreboard je deaktivovaná...");
        }
    }

    public void onDisable() {

        if (serverType == ServerType.SKYCLOUD) {
            VillagerManager.killVillagers();
        }

        // Deaktivace MySQL
        sql.onDisable();

        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new ParticlesAPI(), this);
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new MainGUI(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new TempShop(), this);
        pm.registerEvents(new DeathListener(), this); //TODO: Zkontrolovat damage, pry se pkazdy posle zprava
        pm.registerEvents(new TwerkEvent(), this);
        pm.registerEvents(new BeaconCommand(), this);
        pm.registerEvents(new PlayerSwapListener(), this);
        pm.registerEvents(new SignClickListener(), this);
        pm.registerEvents(new Votes_command(), this);
        pm.registerEvents(new PlayerCommandSendListener(this), this);
        pm.registerEvents(new CommandListener(), this);
        pm.registerEvents(new PlayerLoginListener(), this);
        pm.registerEvents(new InvisibleItemFrameListener(), this);
        pm.registerEvents(new CustomItemsListener(), this);

        if (serverType != ServerType.HARDCORE_VANILLA) {
            pm.registerEvents(new OnEXPBottleThrownListener(), this);
        }

        // Skyblock PVP listener
        if (serverType == ServerType.SKYBLOCK) {
            pm.registerEvents(new SkyblockPVPListener(), this);
            pm.registerEvents(new LeaveDecayListener(), this);
        }

        // Survival PVP listener
        if (serverType == ServerType.SURVIVAL) {
            pm.registerEvents(new SurvivalPVPListener(), this);
        }

        // Colored Anvils (VIP vyhoda)
        if (getConfig().getBoolean("coloredanvils")) {
            pm.registerEvents(new AnvilListener(), this);
            Log.withPrefix("Aktivace barevneho psani v kovadline.");
        }

        if (serverType == ServerType.SKYCLOUD) {
            pm.registerEvents(new VillagerDamageListener(), this);
            pm.registerEvents(new ItemDropListener(), this);
            pm.registerEvents(new LeaveDecayListener(), this);
        }

        if (serverType == ServerType.VANILLA) {
            pm.registerEvents(new LecternBookTakeListener(), this);
        }

        if (serverType == ServerType.HARDCORE_VANILLA) {
            Log.withPrefix("Aktivace hardcore zobrazování HP.");
            //ProtocolLibrary.getProtocolManager().addPacketListener(new HardcorePacketListener(this, ListenerPriority.NORMAL, PacketType.Play.Server.LOGIN));
            Log.withPrefix("Aktivace banování hráčů za smrt.");
            pm.registerEvents(new BanTimesListener(), this);
        }

        if (isPremiumVanishEnabled) {
            pm.registerEvents(new PlayerVanishListener(), this);
        }
    }

    private void loadCommands() {
        manager.registerCommand(new SkullCommand());
        manager.registerCommand(new Menu_command());
        manager.registerCommand(new Coinshop_command());
        manager.registerCommand(new Glow_command());
        manager.registerCommand(new Help_command());
        manager.registerCommand(new Disenchant()); //TODO: Deep test needed
        manager.registerCommand(new Vote_command());
        manager.registerCommand(new Navody_command());
        manager.registerCommand(new BeaconCommand());
        manager.registerCommand(new Cm_command());
        manager.registerCommand(new GlowItemCommand());
        manager.registerCommand(new Blocks_command());
        manager.registerCommand(new Repair_command());
        manager.registerCommand(new Votes_command());
        manager.registerCommand(new VIP_command()); //FIXME: /vip občas nejde, /vipmenu vždy jde...
        manager.registerCommand(new RawBroadcast());
        manager.registerCommand(new ServerSlots_command());
        manager.registerCommand(new RestartManager_command()); //TODO: Nenačítat, pokud nebude CraftCore na serveru?
        manager.registerCommand(new Discord_command());
        manager.registerCommand(new Wiki_command());
        manager.registerCommand(new Cosmetics_command());

        if (serverType != ServerType.HARDCORE_VANILLA) {
            manager.registerCommand(new GetXP_command());
        }

        //Servers
        manager.registerCommand(new Survival_command());
        manager.registerCommand(new Skyblock_command());
        manager.registerCommand(new Creative_command());
        manager.registerCommand(new Prison_command());
        manager.registerCommand(new Vanilla_command());
        manager.registerCommand(new Skycloud_command());
        manager.registerCommand(new HardcoreVanilla_command());


        // Aktivace test prikazu, pouze pokud je povolene hlasovani
        if (getConfig().getBoolean("hlasovani")) {
            //manager.registerCommand(new Fakevote_command());
        }
    }

    public ConfigAPI getConfigAPI() {
        if (!hasConfigAPI()) {
            configAPI = new ConfigAPI(this);
        }
        return configAPI;
    }

    private boolean hasConfigAPI() {
        return configAPI != null;
    }

    private void loadConfiguration() {
        Config blockedTagsFile = new Config(this.configAPI,  "blockedTags");
        configAPI.registerConfig(blockedTagsFile);

        Config deathMessagesFile = new Config(this.configAPI, "deathMessages");
        configAPI.registerConfig(deathMessagesFile);

        Config tabCommandsFile = new Config(this.configAPI, "tabCommands");
        configAPI.registerConfig(tabCommandsFile);

        Config scoreboardFile = new Config(this.configAPI, "scoreboardConfig");
        configAPI.registerConfig(scoreboardFile);
    }

    public Config getBlockedTagsFile() {
        return this.configAPI.getConfig("blockedTags");
    }

    public Config getDeathMessFile() {
        return this.configAPI.getConfig("deathMessages");
    }

    public Config getTabCommandsFile() {
        return this.configAPI.getConfig("tabCommands");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isVisibleForPlayer(Player p) {
        return players.contains(p);
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public ParticlesAPI getParticlesAPI() {
        return particlesAPI;
    }

    public MainGUI getMainGUI() {
        return gui;
    }

    public SQLManager getMySQL() {
        return sql;
    }

    public ServerFactory getServerFactory() {
        return sf;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {}

    public void sendToServer(Player player, String target) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }

    public boolean isValidMaterial(Material material) {
        String name = String.valueOf(material);
        return name.endsWith("_AXE") || name.endsWith("_PICKAXE") || name.endsWith("_SHOVEL") || name.endsWith("_SWORD") || name.endsWith("_HOE")
                || name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS")
                || name.equalsIgnoreCase("FISHING_ROD") || name.equalsIgnoreCase("FLINT_AND_STEEL") || name.equalsIgnoreCase("BOW") || name.equalsIgnoreCase("CARROT_STICK")
                || name.equalsIgnoreCase("SHIELD") || name.equalsIgnoreCase("SHEARS");
    }

    private void initDatabase() {
        sql = new SQLManager(this);
    }

    public static String getMentionPrefix() {
        return mentionPrefix;
    }

    public boolean areDeathMessagesEnabled() {
        return getConfig().getBoolean("d_msgs.enabled");
    }

    public static ServerType getServerType() {
        return serverType;
    }

    private ServerType resolveServerType() {
        String type = getInstance().getConfig().getString("server");
        if (type == null) {
            return ServerType.UNKNOWN;
        }
        if (type.equalsIgnoreCase("survival") || type.equalsIgnoreCase("survival2")) { // Survival2 = 1.15
            return ServerType.SURVIVAL;
        } else if (type.equalsIgnoreCase("skyblock")) {
            return ServerType.SKYBLOCK;
        } else if (type.equalsIgnoreCase("creative") || type.equalsIgnoreCase("creative2")) { // creative2 = 1.15
            return ServerType.CREATIVE;
        } else if (type.equalsIgnoreCase("prison")) {
            return ServerType.PRISON;
        } else if (type.equalsIgnoreCase("vanilla") || type.equalsIgnoreCase("vanilla2")) { // vanilla2 = 1.15
            return ServerType.VANILLA;
        } else if (type.equalsIgnoreCase("skycloud")) {
            return ServerType.SKYCLOUD;
        } else if (type.equalsIgnoreCase("hardcore-vanilla")) {
            return ServerType.HARDCORE_VANILLA;
        } else {
            return ServerType.UNKNOWN;
        }
    }

    public CshopManager getCshopManager() {
        return cshopManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ScoreboardProvider getScoreboardProvider() {
        return scoreboardProvider;
    }

    public static boolean isPremiumVanishEnabled() {
        return isPremiumVanishEnabled;
    }

    public static boolean isCraftPackEnabled() {
        return isCraftPackEnabled;
    }

    public static boolean isLibsDisguiseEnabled() {
        return isLibsDisguiseEnabled;
    }

    /**
     * Odesilá exception na Sentry
     */
    public void sendSentryException(Exception exception) {
        if (sentry == null) {
            Log.normalMessage("Sentry neni aktivovany, error nebude zaslan!");
            return;
        }
        sentry.sendException(exception);
    }
}
