package src.PastShadie.scripts.mMain.Progressive;

import org.powbot.mobile.script.ScriptManager;
import java.util.ArrayList;
import src.PastShadie.scripts.mMain.Assets.Task;

public class startProgressive {
    private ArrayList<Task> progressiveTasks = new ArrayList<>();
    public void Progressive() {
            if (progressiveTasks.isEmpty()) {
                progressiveTasks.add(new skillSwitcher());
                progressiveTasks.add(new startSkill());
            }

            for (Task task : progressiveTasks) {
                if (task.activate()) {
                    task.execute();
                    if (ScriptManager.INSTANCE.isStopping()) {
                        ScriptManager.INSTANCE.stop();
                        break;
                    }
                    return;
                }
            }
        }
    }
