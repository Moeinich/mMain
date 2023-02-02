package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;

import script.mMain;

public class FletchingHelper {
        public void withdrawLogs(String logName) {
            if (Bank.stream().name(logName).first().stackSize() < 27) {
                mMain.State = "We ran out of logs";
                mMain.taskRunning.set(false); //Skip task on progressive
            } else {
                Bank.withdraw(logName, 27);
                Bank.close();
                Condition.wait(() -> !Bank.opened(), 200,50);
            }
        }
}
