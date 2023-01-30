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
                .addString("Skill Time left: ", () -> String.valueOf(Stopwatch.timeLeft()))
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

    public static class Stopwatch {
        public static long stopTime;
        public Stopwatch() {
            stopTime = 0;
        }
        public void reset(long time) {
            stopTime = System.currentTimeMillis() + time;
        }
        public Stopwatch(long time) {
            reset(time);
        }
        public static long timeLeft() {
            return (stopTime - System.currentTimeMillis());
        }
        public boolean hasFinished() {
            return System.currentTimeMillis() >= stopTime;
        }
    }

    @Override
    public void poll() {
        var startMining = new Mining.startMining();
        var startWoodcutting = new Woodcutting.startWoodcutting();
        var startFishing = new Fishing.startFishing();
        var startCooking = new Cooking.startCooking();
        var startFiremaking = new Firemaking.startFiremaking();
        var startSmithing = new Smithing.startSmithing();
        var startCombat = new Combat.startCombat();
        String skill = getOption("Skill");

        switch (skill) {
            case "Progressive":
                Executor executor = Executors.newSingleThreadExecutor();
                List<Runnable> tasks = Arrays.asList(
                        () -> {

                            startMining.Mining();
                        },
                        () -> {
                            startFishing.Fishing();
                        },
                        () -> {
                            startWoodcutting.Woodcutting();
                        },
                        () -> {
                            startCooking.Cooking();
                        },
                        () -> {
                            startFiremaking.Firemaking();
                        },
                        () -> {
                            startSmithing.Smithing();
                        }
                        // Add future skills to this tasklist!
                );
                final Stopwatch runtime = new Stopwatch();
                runtime.reset(Random.nextInt(1200000, 2640000));
                System.out.println("start stopwatch");
                final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                mMain.scriptStatus = "Started watch + got task";
                System.out.println("got task" + taskIndex);
                executor.execute(() -> {
                    while(runtime.hasFinished() & !ScriptManager.INSTANCE.isStopping()) {
                        tasks.get(taskIndex).run();
                        mMain.scriptStatus = "Running task loop!";
                        System.out.println("Running task loop!");
                        if (ScriptManager.INSTANCE.isStopping()) {
                            break;
                        }
                    }
                });
                break;

                //Starting individual progressive skills
                //in case you want to do x skill only.

            case "Mining":
                startMining.Mining();
                break;
            case "Combat":
                startCombat.Combat();
                break;
            case "Fishing":
                startFishing.Fishing();
                break;
            case "Woodcutting":
                startWoodcutting.Woodcutting();
                break;
            case "Cooking":
                startCooking.Cooking();
                break;
            case "Firemaking":
                startFiremaking.Firemaking();
                break;
            case "Smithing":
                startSmithing.Smithing();
                break;
        }
    }
}