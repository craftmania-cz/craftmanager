package cz.wake.manager.utils.scoreboard;

import cz.craftmania.craftcore.fastboard.FastBoard;
import cz.wake.manager.Main;
import cz.wake.manager.utils.configs.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.*;
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
            board.updateLines(this.boardLines);
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
                List<String> lines = this.boardLines;
                List<String> finalLines = lines.stream().map((line) -> PlaceholderAPI.setPlaceholders(board.getPlayer(), line)).collect(Collectors.toList());
                board.updateLines(finalLines);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
