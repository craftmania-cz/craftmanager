package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;

public class JoinAnnouncerTask implements Runnable {

    @Override
    public void run() {
        Log.info("Synchronizace join-announcer zpráv.");
        Main.getInstance().loadJoinAnnouncer();
    }
}
