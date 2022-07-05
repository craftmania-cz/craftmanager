package cz.wake.manager.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftManager] " + ChatColor.WHITE + message);
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftManager] " + ChatColor.RED + message);
    }

    public static void debug(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[DEBUG] " + ChatColor.WHITE + message);
    }
}
