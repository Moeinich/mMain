package script;

import org.powbot.api.Random;
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
        new ScriptUploader().uploadAndStart("mMain", "Account", "emulator-5554", true, false);
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

    public class Stopwatch {

        public long stopTime;

        public Stopwatch(long time) {
            reset(time);
        }

        public Stopwatch() {
            stopTime = 0;
        }

        public void reset(long time) {
            stopTime = System.currentTimeMillis() + time;
        }

        public long timeLeft() {
            return (stopTime - System.currentTimeMillis());
        }

        public boolean hasFinished() {
            return System.currentTimeMillis() >= stopTime;
        }

    }

    @Override
    public void poll() {
        String skill = getOption("Skill");

        switch (skill) {
            case "Progressive":
                Executor executor = Executors.newSingleThreadExecutor();
                List<Runnable> tasks = Arrays.asList(
                        () -> {
                            var startMining = new Mining.startMining();
                            startMining.Mining();
                        },
                        () -> {
                            var startFishing = new Fishing.startFishing();
                            startFishing.Fishing();
                        },
                        () -> {
                            var startWoodcutting = new Woodcutting.startWoodcutting();
                            startWoodcutting.Woodcutting();
                        },
                        () -> {
                            var startCooking = new Cooking.startCooking();
                            startCooking.Cooking();
                        },
                        () -> {
                            var startFiremaking = new Firemaking.startFiremaking();
                            startFiremaking.Firemaking();
                        },
                        () -> {
                            var startSmithing = new Smithing.startSmithing();
                            startSmithing.startSmithing();
                        }
                        // Add future skills to this tasklist!
                );
                final Stopwatch runtime = new Stopwatch();
                runtime.reset(Random.nextInt(1200000, 2640000));
                final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                executor.execute(() -> {
                    while(runtime.hasFinished()) {
                        tasks.get(taskIndex).run();
                        mMain.scriptStatus = "Running task loop!";
                        if (ScriptManager.INSTANCE.isStopping()) {
                            break;
                        }
                    }
                });
                break;

                //Starting individual progressive skills
                //in case you want to do x skill only.

            case "Mining":
                var startMining = new Mining.startMining();
                startMining.Mining();
                break;
            case "Combat":
                var startCombat = new Combat.startCombat();
                startCombat.Combat();
                break;
            case "Fishing":
                var startFishing = new Fishing.startFishing();
                startFishing.Fishing();
                break;
            case "Woodcutting":
                var startWoodcutting = new Woodcutting.startWoodcutting();
                startWoodcutting.Woodcutting();
                break;
            case "Cooking":
                var startCooking = new Cooking.startCooking();
                startCooking.Cooking();
                break;
            case "Firemaking":
                var startFiremaking = new Firemaking.startFiremaking();
                startFiremaking.Firemaking();
                break;
            case "Smithing":
                var startSmithing = new Smithing.startSmithing();
                startSmithing.startSmithing();
                break;
        }
    }
}