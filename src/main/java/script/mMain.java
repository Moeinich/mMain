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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

import Agility.StartAgility;
import Combat.StartCombat;
import Cooking.StartCooking;
import Crafting.StartCrafting;
import Firemaking.StartFiremaking;
import Fishing.StartFishing;
import Mining.StartMining;
import Smithing.StartSmithing;
import Thieving.StartThieving;
import Woodcutting.StartWoodcutting;

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
                        defaultValue = "Agility",
                        allowedValues = {"Progressive", "Mining", "Fishing", "Woodcutting", "Cooking", "Firemaking", "Smithing", "Thieving", "Crafting", "Fletching", "Agility", "Herblore"},
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

    public static String State;
    Executor taskHandler = Executors.newSingleThreadExecutor();
    public static final AtomicBoolean taskRunning = new AtomicBoolean(false);

    @Override
    public void onStart() {
        String skill = getOption("Skill");
        State = "Starting...";

        Paint p = new PaintBuilder()
                .addString("Skill: " , () -> skill)
                .addString("State: ", () -> State)
                .addString("Skill Time left: ", () -> Stopwatch.timeLeft() / 1000 / 60 + " min")
                .trackSkill(Skill.Mining)
                .trackSkill(Skill.Fishing)
                .trackSkill(Skill.Woodcutting)
                .trackSkill(Skill.Cooking)
                .trackSkill(Skill.Firemaking)
                .trackSkill(Skill.Smithing)
                .trackSkill(Skill.Thieving)
                .trackSkill(Skill.Crafting)
                .trackSkill(Skill.Fletching)
                .x(40)
                .y(400)
                .removeScriptNameVersion()
                .withoutDiscordWebhook()
                .build();
        addPaint(p);
    }

    public static class Stopwatch {
        private static long stopTime;
        private boolean running;

        public Stopwatch() {
            stopTime = 0;
        }
        public void reset(long time) {
            long startTime = System.currentTimeMillis();
            stopTime = startTime + time;
            running = true;
        }
        public static long timeLeft() {
            return (stopTime - System.currentTimeMillis());
        }
        public boolean hasFinished() {
            return System.currentTimeMillis() >= stopTime;
        }
        public boolean isRunning() {
            return running;
        }
    }

    @Override
    public void poll() {
        var startMining = new StartMining();
        var startWoodcutting = new StartWoodcutting();
        var startFishing = new StartFishing();
        var startCooking = new StartCooking();
        var startFiremaking = new StartFiremaking();
        var startSmithing = new StartSmithing();
        var startCombat = new StartCombat();
        var startThieving = new StartThieving();
        var startCrafting = new StartCrafting();
        var startFletching = new Fletching.startFletching();
        var startAgility = new Agility.StartAgility();
        var startHerblore = new Herblore.StartHerblore();

        String skill = getOption("Skill");

        switch (skill) {
            case "Progressive":
                List<Runnable> tasks = Arrays.asList(
                        startMining::Mining,
                        startFishing::Fishing,
                        startWoodcutting::Woodcutting,
                        startCooking::Cooking,
                        startFiremaking::Firemaking,
                        startThieving::Thieving,
                        startCrafting::Crafting,
                        startFletching::Fletching,
                        startAgility::Agility,
                        startHerblore::Herblore
                        // Add future skills to this tasklist!
                );
                if (taskRunning.compareAndSet(false, true)) {
                    final Stopwatch runtime = new Stopwatch();
                    if (!runtime.isRunning()) {
                        runtime.reset(Random.nextInt(44, 120 * 1000 * 60));
                    }
                    final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                    final CountDownLatch countdownLatch = new CountDownLatch(1);
                    taskHandler.execute(() -> {
                        try {
                            while(!runtime.hasFinished()) {
                                countdownLatch.await();
                                tasks.get(taskIndex).run();
                                if (ScriptManager.INSTANCE.isStopping()) {
                                    ScriptManager.INSTANCE.stop();
                                    break;
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            taskRunning.set(false);
                        }
                    });
                    countdownLatch.countDown();
                }
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
            case "Thieving":
                startThieving.Thieving();
                break;
            case "Crafting":
                startCrafting.Crafting();
                break;
            case "Fletching":
                startFletching.Fletching();
                break;
            case "Agility":
                startAgility.Agility();
                break;
            case "Herblore":
                startHerblore.Herblore();
                break;
        }
    }
}