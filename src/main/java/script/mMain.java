package script;

import org.powbot.api.Notifications;
import org.powbot.api.Random;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        version = "0.1.18"
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name =  "Mode",
                        description = "Which skill would you like to do?",
                        defaultValue = "Progressive",
                        allowedValues = {"Progressive", "Mining", "Fishing", "Woodcutting", "Cooking", "Firemaking", "Smithing", "Thieving", "Crafting", "Fletching", "Agility", "Herblore", "Ranged", "Magic"},
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

    public static String runningSkill;
    public static String state;
    public static Boolean shouldBank = true;
    Executor taskHandler = Executors.newSingleThreadExecutor();
    public static final AtomicBoolean taskRunning = new AtomicBoolean(false);

    @Override
    public void onStart() {
        String skill = getOption("Mode");
        runningSkill = "Determining...";
        state = "Starting...";
        InteractionsHelper.cameraCheck();
        SimpleDateFormat timerFormat = new SimpleDateFormat("mm:ss");
        Notifications.showNotification("mMain starting " + skill);

        PaintBuilder builder = new PaintBuilder()
                .addString("Mode: ", () -> skill)
                .addString("State: ", () -> state);

        if (skill.equals("Progressive")) {
            builder.addString("Running: ", () -> runningSkill)
                    .addString("Skill switch: ", () -> timerFormat.format(new Date(Stopwatch.timeLeft())));
        }
        builder.trackSkill(Skill.Mining, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Fishing, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Woodcutting, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Cooking, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Firemaking, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Smithing, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Thieving, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Crafting, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Fletching, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Herblore, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Agility, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Ranged, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Defence, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Magic, TrackSkillOption.LevelProgressBar);
        Paint p = builder.x(40).y(300).build();
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
        var startAgility = new Agility.StartAgility();
        var startCooking = new Cooking.StartCooking();
        var startCrafting = new Crafting.StartCrafting();
        var startFiremaking = new Firemaking.StartFiremaking();
        var startFishing = new Fishing.StartFishing();
        var startFletching = new Fletching.startFletching();
        var startHerblore = new Herblore.StartHerblore();
        var startMagic = new MagicCombat.StartMagic();
        var startMelee = new MeleeCombat.StartMelee();
        var startMining = new Mining.StartMining();
        var startRanged = new RangedCombat.StartRanged();
        var startSmithing = new Smithing.StartSmithing();
        var startThieving = new Thieving.StartThieving();
        var startWoodcutting = new Woodcutting.StartWoodcutting();

        if (SkillData.allSkillsDone()) {
            ScriptManager.INSTANCE.stop();
        }

        String skill = getOption("Mode");

        switch (skill) {
            case "Progressive":
                List<Runnable> tasks = Arrays.asList(
                        startAgility::Agility,
                        startCooking::Cooking,
                        startCrafting::Crafting,
                        startFiremaking::Firemaking,
                        startFishing::Fishing,
                        startFletching::Fletching,
                        startHerblore::Herblore,
                        //startMagic::Magic,
                        //startMelee::Melee,
                        startMining::Mining,
                        startRanged::Ranged,
                        startSmithing::Smithing,
                        startThieving::Thieving,
                        startWoodcutting::Woodcutting
                        // Add future skills to this tasklist!
                );
                if (SkillData.allSkillsDone()) {
                    ScriptManager.INSTANCE.stop();
                }
                if (taskRunning.compareAndSet(false, true)) {
                    final Stopwatch runtime = new Stopwatch();
                    if (!runtime.isRunning()) {
                        if (!mMain.shouldBank) {
                            mMain.shouldBank = true;
                        } else runtime.reset(Random.nextInt(40, 55 * 1000 * 60));
                    }
                    final int taskIndex = ThreadLocalRandom.current().nextInt(tasks.size());
                    final CountDownLatch countdownLatch = new CountDownLatch(1);
                    taskHandler.execute(() -> {
                        try {
                            while(!runtime.hasFinished() && taskRunning.get() && !ScriptManager.INSTANCE.isStopping()) {
                                countdownLatch.await();
                                tasks.get(taskIndex).run();
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

            case "Agility":
                if (SkillData.agilityDone) {
                    ScriptManager.INSTANCE.stop();
                } else startAgility.Agility();
                break;
            case "Cooking":
                if (SkillData.cookingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startCooking.Cooking();
                break;
            case "Crafting":
                if (SkillData.craftingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startCrafting.Crafting();
                break;
            case "Firemaking":
                if (SkillData.firemakingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startFiremaking.Firemaking();
                break;
            case "Fishing":
                if (SkillData.fishingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startFishing.Fishing();
                break;
            case "Fletching":
                if (SkillData.fletchingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startFletching.Fletching();
                break;
            case "Herblore":
                if (SkillData.herbloreDone) {
                    ScriptManager.INSTANCE.stop();
                } else startHerblore.Herblore();
                break;
            case "Magic":
                if (SkillData.magicCombatDone) {
                    ScriptManager.INSTANCE.stop();
                } else startMagic.Magic();
                break;
            case "Melee":
                if (SkillData.meleeCombatDone) {
                    ScriptManager.INSTANCE.stop();
                } else startMelee.Melee();
                break;
            case "Mining":
                if (SkillData.miningDone) {
                    ScriptManager.INSTANCE.stop();
                } else startMining.Mining();
                break;
            case "Ranged":
                if (SkillData.rangeCombatDone) {
                    ScriptManager.INSTANCE.stop();
                } else startRanged.Ranged();
                break;
            case "Smithing":
                if (SkillData.smithingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startSmithing.Smithing();
                break;
            case "Thieving":
                if (SkillData.thievingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startThieving.Thieving();
                break;
            case "Woodcutting":
                if (SkillData.woodcuttingDone) {
                    ScriptManager.INSTANCE.stop();
                } else startWoodcutting.Woodcutting();
                break;
        }
    }
}