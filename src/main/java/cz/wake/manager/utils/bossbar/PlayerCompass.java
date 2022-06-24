package cz.wake.manager.utils.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class PlayerCompass {

    private Player player;
    private BossBar bossBar;

    public PlayerCompass() {
        this.bossBar = Bukkit.createBossBar("Výpočet polohy...", BarColor.YELLOW, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
        this.bossBar.removeFlag(BarFlag.PLAY_BOSS_MUSIC);
        this.bossBar.setProgress(1.0);
    }

    public PlayerCompass send(Player player) {
        this.player = player;
        this.bossBar.addPlayer(player);
        return this;
    }

    public PlayerCompass remove() {
        this.bossBar.setVisible(false);
        this.bossBar.removeAll();
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        this.bossBar.setTitle("⻭ §7X: " + this.player.getLocation().getBlockX()
                + "§7 Y: " + this.player.getLocation().getBlockY()
                + "§7 Z: " + this.player.getLocation().getBlockZ() + " §8(/hud)");
    }






}
