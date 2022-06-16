package src.PastShadie.scripts.mMain;

import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.service.ScriptUploader;

@ScriptManifest(
        name = "mMain",
        description = "Progressively levels different skills with an emphasis on ironman-mode",
        version = "0.0.1"
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name =  "Skill",
                        description = "Which skill would you like to do?",
                        defaultValue = "Mining",
                        allowedValues = {"Mining", "Woodcutting"},
                        optionType = OptionType.STRING
                )
        }
)

public class mMain extends AbstractScript {
    //%userprofile%\.powbot\android\platform-tools\adb.exe
    //adb devices
    //adb kill-server
    //adb.exe forward tcp:61666 tcp:61666

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("mMain", "Account", "127.0.0.1:5555", true, true);
    }

    @Override
    public void onStart() {
        String skill = getOption("Skill");

        Paint p = new PaintBuilder()
                .addString("Skill: " , () -> skill)
                .trackSkill(Skill.Mining)
                .x(30)
                .y(50)
                //.trackInventoryItem(ItemList.LOGS_1511, "Logs")
                .withoutDiscordWebhook()
                .build();
        addPaint(p);
    }

    @Override
    public void poll() {
        String skill = getOption("Skill");

        if (skill.equals("Mining")) {
            var startMining = new src.PastShadie.scripts.mMain.Mining.startMining();
            startMining.Mining();
    }
}
}