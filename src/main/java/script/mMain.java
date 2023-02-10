package script;

import org.powbot.api.Random;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
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

import Helpers.InteractionsHelper;
import Helpers.SkillData;

@ScriptManifest(
        name = "mMain",
        description = "Progressively levels different skills",
        version = "0.0.10"
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name =  "Skill",
                        description = "Which skill would you like to do?",
                        defaultValue = "Mining",
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
    public static String RunningSkill;
    public static String State;
    public static Boolean ShouldBank = true;
    Executor taskHandler = Executors.newSingleThreadExecutor();
    public static final AtomicBoolean taskRunning = new AtomicBoolean(false);

    @Override
    public void onStart() {
        String skill = getOption("Skill");
        RunningSkill = "Determining...";
        State = "Starting...";
        InteractionsHelper.cameraCheck();

        Paint p;
        if (skill.equals("Progressive")) {
            p = new PaintBuilder()
                    .addString("Skill: ", () -> skill)
                    .addString("Running: ", () -> RunningSkill)
                    .addString("Skill Time left: ", () -> Stopwatch.timeLeft() / 1000 / 60 + " min")
                    .addString("State: ", () -> State)
                    .trackSkill(Skill.Mining)
                    .trackSkill(Skill.Fishing)
                    .trackSkill(Skill.Woodcutting)
                    .trackSkill(Skill.Cooking)
                    .trackSkill(Skill.Firemaking)
                    .trackSkill(Skill.Smithing)
                    .trackSkill(Skill.Thieving)
                    .trackSkill(Skill.Crafting)
                    .trackSkill(Skill.Fletching)
                    .trackSkill(Skill.Herblore)
                    .trackSkill(Skill.Agility)
                    .x(40)
                    .y(300)
                    .build();
        } else {
            p = new PaintBuilder()
                    .addString("Skill: ", () -> skill)
                    .addString("State: ", () -> State)
                    .trackSkill(Skill.Mining)
                    .trackSkill(Skill.Fishing)
                    .trackSkill(Skill.Woodcutting)
                    .trackSkill(Skill.Cooking)
                    .trackSkill(Skill.Firemaking)
                    .trackSkill(Skill.Smithing)
                    .trackSkill(Skill.Thieving)
                    .trackSkill(Skill.Crafting)
                    .trackSkill(Skill.Fletching)
                    .trackSkill(Skill.Herblore)
                    .trackSkill(Skill.Agility)
                    .x(40)
                    .y(300)
                    .build();
        }
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
        var startMining = new Mining.StartMining();
        var startWoodcutting = new Woodcutting.StartWoodcutting();
        var startFishing = new Fishing.StartFishing();
        var startCooking = new Cooking.StartCooking();
        var startFiremaking = new Firemaking.StartFiremaking();
        var startSmithing = new Smithing.StartSmithing();
        var startCombat = new Combat.StartCombat();
        var startThieving = new Thieving.StartThieving();
        var startCrafting = new Crafting.StartCrafting();
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
                        startFiremaking::Firemaking,
                        startThieving::Thieving,
                        startCrafting::Crafting,
                        startFletching::Fletching,
                        startAgility::Agility,
                        startHerblore::Herblore,
                        startCooking::Cooking
                        // Add future skills to this tasklist!
                );
                if (SkillData.AllSkillsDone()) {
                    ScriptManager.INSTANCE.stop();
                }
                if (taskRunning.compareAndSet(false, true)) {
                    final Stopwatch runtime = new Stopwatch();
                    if (!runtime.isRunning()) {
                        if (!mMain.ShouldBank) {
                            mMain.ShouldBank = true;
                        } else runtime.reset(Random.nextInt(5, 10 * 1000 * 60));
                    }
                    final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                    final CountDownLatch countdownLatch = new CountDownLatch(1);
                    taskHandler.execute(() -> {
                        try {
                            while(!runtime.hasFinished() && taskRunning.get()) {
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
                if (SkillData.MiningDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startMining.Mining();
                break;

            case "Combat":
                if (SkillData.FishingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startCombat.Combat();
                break;

            case "Fishing":
                if (SkillData.FishingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startFishing.Fishing();
                break;

            case "Woodcutting":
                if (SkillData.WoodcuttingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startWoodcutting.Woodcutting();
                break;

            case "Cooking":
                if (SkillData.CookingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startCooking.Cooking();
                break;
            case "Firemaking":
                if (SkillData.FiremakingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startFiremaking.Firemaking();
                break;

            case "Smithing":
                if (SkillData.SmithingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startSmithing.Smithing();
                break;

            case "Thieving":
                if (SkillData.ThievingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startThieving.Thieving();
                break;

            case "Crafting":
                if (SkillData.CraftingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startCrafting.Crafting();
                break;

            case "Fletching":
                if (SkillData.FletchingDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startFletching.Fletching();
                break;

            case "Agility":
                if (SkillData.AgilityDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startAgility.Agility();
                break;

            case "Herblore":
                if (SkillData.HerbloreDone == true) {
                    ScriptManager.INSTANCE.stop();
                } else startHerblore.Herblore();
                break;
        }
    }
}