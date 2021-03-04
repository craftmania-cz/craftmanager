package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import cz.wake.manager.utils.bossbar.PlayerCompass;

public class CompassTask implements Runnable {

    @Override
    public void run() {
        assert Main.getInstance().getCompassManager() != null;
        Main.getInstance().getCompassManager().getCompassList().forEach((PlayerCompass::update));

    }
}
