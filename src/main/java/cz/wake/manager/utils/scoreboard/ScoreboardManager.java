package cz.wake.manager.utils.scoreboard;

//import cz.craftmania.craftcore.fastboard.FastBoard;
// TODO: Až bude CraftCore na MC 1.21 změnit zpět
import fr.mrmicky.fastboard.FastBoard;
import cz.wake.manager.Main;
import cz.wake.manager.utils.configs.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScoreboardManager {

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private String boardName = "[DFEAULT]";
    private List<String> boardLines = new ArrayList<>();

    public ScoreboardManager() {
        Config configLines = Main.getInstance().getConfigAPI().getConfig("scoreboardConfig");
        if (configLines != null) {
            boardName = configLines.getString("scoreboard.name");
            boardLines = configLines.getStringList("scoreboard.lines");
        }
    }

    public void setupPlayer(final Player player) {
        try {
            FastBoard board = new FastBoard(player);
            board.updateTitle(this.boardName);
            List<String> finalLines = updateLines(this.boardLines, player.getPlayer());
            board.updateLines(finalLines);
            boards.put(player.getUniqueId(), board);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void removePlayer(Player player) {
        FastBoard board = this.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public void update() {
        try {
            for (FastBoard board : this.boards.values()) {
                List<String> finalLines = updateLines(this.boardLines, board.getPlayer());
                board.updateLines(finalLines);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private List<String> updateLines(List<String> list, final Player player) {
        return list.stream().map((line) -> PlaceholderAPI.setPlaceholders(player.getPlayer(), line)).map(this::miniMessageBuilder).collect(Collectors.toList());
    }

    private String miniMessageBuilder(String text) {
        return MessageColorEdit.replaceHex(text);
    }

    //TODO: Přesunout do CraftLibs s lepší podporou
    static class MessageColorEdit {

        public static final Pattern HEX_PATTERN = Pattern.compile("&(#[A-Fa-f0-9]{6})");

        public MessageColorEdit(){};

        public static String color(String string) {
            return string == null ? null : replaceHex(ChatColor.translateAlternateColorCodes('&', string));
        }

        public static String replaceHex(String str) {
            Matcher matcher = HEX_PATTERN.matcher(str);
            while (matcher.find()) {
                str = str.replace(matcher.group(0), ChatColor.of(matcher.group(1)).toString());
            }
            return str;
        }

        public static List<String> colorAllLines(List<String> list) {
            list.replaceAll(MessageColorEdit::color);
            return list;
        }
    }
}
