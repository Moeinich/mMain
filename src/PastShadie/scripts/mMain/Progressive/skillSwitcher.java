package src.PastShadie.scripts.mMain.Progressive;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import src.PastShadie.scripts.mMain.Assets.Task;
public class skillSwitcher extends Task {
    private Timer timer;
    private boolean isTimerRunning = false;
    private int randomDuration;
    public int skillNumber;

    @Override
    public boolean activate() {
        return !isTimerRunning;
        //return
    }

    @Override
    public void execute() {
        isTimerRunning = true;
        randomDuration = ThreadLocalRandom.current().nextInt(25000, 750000 + 1);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isTimerRunning = false;
                skillNumber = ThreadLocalRandom.current().nextInt(6) + 1;
            }
        }, randomDuration);
    }
}
