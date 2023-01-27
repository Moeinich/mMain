package src.PastShadie.scripts.mMain;

import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import src.PastShadie.scripts.mMain.Combat.startCombat;
import src.PastShadie.scripts.mMain.Cooking.startCooking;
import src.PastShadie.scripts.mMain.Firemaking.startFiremaking;
import src.PastShadie.scripts.mMain.Fishing.startFishing;
import src.PastShadie.scripts.mMain.Mining.startMining;
import src.PastShadie.scripts.mMain.Smithing.startSmithing;
import src.PastShadie.scripts.mMain.Woodcutting.startWoodcutting;

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
                        defaultValue = "Progressive",
                        allowedValues = {"Progressive", "Mining", "Fishing", "Woodcutting", "Cooking", "Firemaking", "Smithing"},
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
        new ScriptUploader().uploadAndStart("mMain", "Account", "emulator-5554", true, true);
    }

    public static String scriptStatus;

    @Override
    public void onStart() {
        String skill = getOption("Skill");
        scriptStatus = "Starting...";

        Paint p = new PaintBuilder()
                .addString("Skill: " , () -> skill)
                .addString("Status: ", () -> scriptStatus)
                .trackSkill(Skill.Mining)
                .trackSkill(Skill.Fishing)
                .trackSkill(Skill.Woodcutting)
                .trackSkill(Skill.Cooking)
                .trackSkill(Skill.Firemaking)
                .trackSkill(Skill.Smithing)
                .x(60)
                .y(100)
                .build();
        addPaint(p);
    }

    @Override
    public void poll() {
        String skill = getOption("Skill");

        switch (skill) {
            case "Progressive":
                Executor executor = Executors.newSingleThreadExecutor();
                List<Runnable> tasks = Arrays.asList(
                        () -> {
                            var startMining = new startMining();
                            startMining.Mining();
                        },
                        () -> {
                            var startFishing = new startFishing();
                            startFishing.Fishing();
                        },
                        () -> {
                            var startWoodcutting = new startWoodcutting();
                            startWoodcutting.Woodcutting();
                        },
                        () -> {
                            var startCooking = new startCooking();
                            startCooking.Cooking();
                        },
                        () -> {
                            var startFiremaking = new startFiremaking();
                            startFiremaking.Firemaking();
                        },
                        () -> {
                            var startSmithing = new startSmithing();
                            startSmithing.startSmithing();
                        }
                        // Add future skills to this tasklist!
                );
                long duration = ThreadLocalRandom.current().nextLong(5000, 10000);
                final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                final long startTime = System.currentTimeMillis();
                executor.execute(() -> {
                    while (System.currentTimeMillis() - startTime < duration && !ScriptManager.INSTANCE.isStopping()) {
                        tasks.get(taskIndex).run();
                    }
                    ScriptManager.INSTANCE.stop();
                });



                //Doing individual progressive skills!
            case "Mining":
                var startMining = new startMining();
                startMining.Mining();
            case "Combat":
                var startCombat = new startCombat();
                startCombat.Combat();
            case "Fishing":
                var startFishing = new startFishing();
                startFishing.Fishing();
            case "Woodcutting":
                var startWoodcutting = new startWoodcutting();
                startWoodcutting.Woodcutting();
            case "Cooking":
                var startCooking = new startCooking();
                startCooking.Cooking();
            case "Firemaking":
                var startFiremaking = new startFiremaking();
                startFiremaking.Firemaking();
            case "Smithing":
                var startSmithing = new startSmithing();
                startSmithing.startSmithing();
        }
    }
}