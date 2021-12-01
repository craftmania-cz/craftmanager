package cz.wake.manager;

import co.aikar.commands.PaperCommandManager;
import cz.craftmania.crafteconomy.objects.LevelType;
import cz.craftmania.craftlibs.sentry.CraftSentry;
import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.commads.staff.CosAdmin_command;
import cz.wake.manager.commads.staff.RawBroadcast;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.commads.staff.ServerSlots_command;
import cz.wake.manager.listener.*;
import cz.wake.manager.listener.suggestions.PlayerCommandSendListener;
import cz.wake.manager.managers.CompassManager;
import cz.wake.manager.managers.CshopManager;
import cz.wake.manager.perks.coloranvil.AnvilListener;
import cz.wake.manager.perks.general.*;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.servers.global.LeaveDecayListener;
import cz.wake.manager.servers.skycloud.ItemDropListener;
import cz.wake.manager.servers.skycloud.VillagerDamageListener;
import cz.wake.manager.servers.skycloud.VillagerManager;
import cz.wake.manager.servers.vanilla.DragonSlayerListener;
import cz.wake.manager.sql.SQLManager;
import cz.wake.manager.utils.*;
import cz.wake.manager.utils.configs.Config;
import cz.wake.manager.utils.configs.ConfigAPI;
import cz.wake.manager.utils.configs.CrazyCratesConfig;
import cz.wake.manager.utils.scoreboard.ScoreboardManager;
import cz.wake.manager.utils.tasks.ATAfkTask;
import cz.wake.manager.utils.tasks.*;
import cz.wake.manager.commads.VIP_command;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.*;

public class Main extends JavaPlugin implements PluginMessageListener {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> at_list = new ArrayList<>();
    public HashMap<Player, Integer> at_afk = new HashMap<>();
    public ArrayList<Player> death_messages = new ArrayList<>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    public static Long restartTime = null;
    public static String restartReason = null;
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private SQLManager sql;
    private boolean testing = false;
    private static String mentionPrefix;
    private ConfigAPI configAPI;

    // Managers
    private CshopManager cshopManager;
    private static ScoreboardManager scoreboardManager = null;
    private CompassManager compassManager = null;

    // Plugin dependencies
    private static boolean isPremiumVanishEnabled = false;
    private static boolean isCraftPackEnabled = false;
    private static boolean isLibsDisguiseEnabled = false;

    // Commands manager
    private PaperCommandManager manager;

    private static Main instance;

    // Servery type
    private ServerType serverType = ServerType.UNKNOWN;
    private LevelType levelType = null;

    // Sentry
    private CraftSentry sentry = null;

    @Override
    public void onLoad() {
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

        // Custom recipes
        List<Recipe> serverRecipes = new ArrayList<>();
        Iterator<Recipe> activeRecipes = this.getServer().recipeIterator();
        activeRecipes.forEachRemaining((recipe -> {
            if (recipe.getResult().getType() == Material.END_CRYSTAL) {
                return;
            }
            serverRecipes.add(recipe);
        }));

        if (serverType == ServerType.VANILLA) { // Tady přidat recepty pro servery
            serverRecipes.add(CustomCrafting.getEndCrystalRecipe());
        }
        serverRecipes.add(CustomCrafting.getInvisibleItemFrame());
        serverRecipes.add(CustomCrafting.getSaddleRecipe());

        this.getServer().clearRecipes();
        serverRecipes.forEach((recipe -> this.getServer().addRecipe(recipe)));

        // Generování head configu pouze pokud head config existuje
        if (new File(Main.getInstance().getServer().getWorldContainer().getAbsolutePath() + "/plugins/CrazyCrates/Crates/Head.yml").exists()) {
            Log.normalMessage("CrazyCrates head config existuje. Obnovuji jeho obsah podle aktuálního AT.");
            CrazyCratesConfig.generateHeadCrate();
        } else {
            Log.normalMessage("CrazyCrates head config neexistuje.");
        }
    }

    @Override
    public void onEnable() {

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

        if (this.getServer().getPluginManager().isPluginEnabled("LibsDisguises")) {
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

        // Nastaveni mention prefixu
        mentionPrefix = Main.getInstance().getConfig().getString("mentions.prefix");
        if (mentionPrefix == null) {
            mentionPrefix = "@";
        }
        Log.withPrefix("Mention prefix nastaven na: " + mentionPrefix);

        Bukkit.getWorlds().forEach(world -> world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false));

        // Načtení Cshopu
        levelType = resolveLevelType();
        this.cshopManager = new CshopManager(this);
        this.cshopManager.loadCshop();

        // Načtení ScoreboardManageru
        if (getConfigAPI().getConfig("scoreboardConfig").getBoolean("scoreboard.enabled")) {
            Log.withPrefix("Aktivace Scoreboardu!");
            scoreboardManager = new ScoreboardManager();
            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> getScoreboardManager().update(), 0L, getConfigAPI().getConfig("scoreboardConfig").getLong("scoreboard.refreshTime"));
        } else {
            Log.withPrefix("Scoreboard je deaktivovaný.");
        }

        if (serverType == ServerType.ANARCHY) {
            this.compassManager = new CompassManager();
            getServer().getScheduler().runTaskTimerAsynchronously(this, new CompassTask(), 20, 20);
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
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new DeathListener(), this); //TODO: Zkontrolovat damage, pry se pkazdy posle zprava
        pm.registerEvents(new BeaconCommand(), this);
        pm.registerEvents(new Votes_command(), this);
        pm.registerEvents(new PlayerCommandSendListener(this), this);
        pm.registerEvents(new PlayerLoginListener(), this);
        pm.registerEvents(new InvisibleItemFrameListener(), this);

        // Cosmetic items
        pm.registerEvents(new CustomItemsListener(), this);
        pm.registerEvents(new CosmeticDropListener(), this);

        if (serverType != ServerType.HARDCORE_VANILLA) {
            pm.registerEvents(new OnEXPBottleThrownListener(), this);
        }

        if (serverType == ServerType.VANILLA) {
            pm.registerEvents(new DragonSlayerListener(), this);
        }

        // Skyblock PVP listener
        if (serverType == ServerType.SKYBLOCK) {
            pm.registerEvents(new LeaveDecayListener(), this);
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

        if (isPremiumVanishEnabled) {
            pm.registerEvents(new PlayerVanishListener(), this);
        }
    }

    private void loadCommands() {
        manager.registerCommand(new SkullCommand());
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
        manager.registerCommand(new CosAdmin_command());

        if (serverType != ServerType.HARDCORE_VANILLA) {
            manager.registerCommand(new GetXP_command());
        }

        if (serverType == ServerType.ANARCHY) {
            manager.registerCommand(new Hud_Command());
        }

        //Servers
        manager.registerCommand(new Survival_command());
        manager.registerCommand(new Skyblock_command());
        manager.registerCommand(new Creative_command());
        manager.registerCommand(new Prison_command());
        manager.registerCommand(new Vanilla_command());
        manager.registerCommand(new Anarchy_command());
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
        Config deathMessagesFile = new Config(this.configAPI, "deathMessages");
        configAPI.registerConfig(deathMessagesFile);

        Config tabCommandsFile = new Config(this.configAPI, "tabCommands");
        configAPI.registerConfig(tabCommandsFile);

        Config scoreboardFile = new Config(this.configAPI, "scoreboardConfig");
        configAPI.registerConfig(scoreboardFile);
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

    public SQLManager getMySQL() {
        return sql;
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

    private void initDatabase() {
        sql = new SQLManager(this);
    }

    public static String getMentionPrefix() {
        return mentionPrefix;
    }

    public boolean areDeathMessagesEnabled() {
        return getConfig().getBoolean("d_msgs.enabled");
    }

    public ServerType getServerType() {
        return this.serverType;
    }

    public LevelType getLevelType() {
        return this.levelType;
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
        }else if (type.equalsIgnoreCase("anarchy")) {
            return ServerType.ANARCHY;
        } else if (type.equalsIgnoreCase("hardcore-vanilla")) {
            return ServerType.HARDCORE_VANILLA;
        } else {
            return ServerType.UNKNOWN;
        }
    }

    @Nullable
    private LevelType resolveLevelType() {
        String type = getInstance().getConfig().getString("server");
        if (type == null) {
            return null;
        }
        return switch (type) {
            case "survival" -> LevelType.SURVIVAL_LEVEL;
            case "skyblock" -> LevelType.SKYBLOCK_LEVEL;
            case "creative" -> LevelType.CREATIVE_LEVEL;
            case "vanilla" -> LevelType.VANILLA_LEVEL;
            case "anarchy" -> LevelType.ANARCHY_LEVEL;
            case "prison" -> LevelType.PRISON_LEVEL;
            default -> null;
        };
    }

    public CshopManager getCshopManager() {
        return cshopManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
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

    @Nullable
    public CompassManager getCompassManager() {
        return compassManager;
    }
}
