package cz.wake.manager.objects.pvp;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class PvPStatistics {

    private @Getter int kills;
    private final @Getter long start;

    public PvPStatistics() {
        this.start = System.currentTimeMillis();
    }

    public void addKill() {
        kills++;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - start;
    }

    public String getElapsedTimePretty() {
        long milliseconds = getElapsedTime();

        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        String time = "";

        if (minutes != 0) {
            time += minutes + "m ";
        }
        seconds -= minutes * 60;
        if (seconds != 0) {
            time += seconds + "s";
        }

        if (time.equals("")) {
            time = "0s";
        }

        return time;
    }
}
