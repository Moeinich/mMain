package script;

import com.google.common.eventbus.Subscribe;

import org.powbot.api.Random;
import org.powbot.api.event.MessageEvent;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import agility.StartAgility;
import cooking.StartCooking;
import crafting.StartCrafting;
import firemaking.StartFiremaking;
import fishing.StartFishing;
import fletching.StartFletching;
import helpers.InteractionsHelper;
import helpers.SkillData;
import herblore.StartHerblore;
import hunter.StartHunter;
import magicCombat.StartMagic;
import meleeCombat.StartMelee;
import mining.StartMining;
import quests.common.helpers.SystemMessageManager;
import rangedCombat.StartRanged;
import runecrafting.startRunecrafting;
import smithing.StartSmithing;
import thieving.StartThieving;
import woodcutting.StartWoodcutting;

@ScriptManifest(
        name = "mMain",
        description = "Progressively levels different skills and does a few quests",
        version = "0.3.3",
        category = ScriptCategory.Other
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name =  "Mode",
                        description = "Which skill would you like to do?",
                        defaultValue = "progressive",
                        allowedValues = {
                                "progressive", "mining", "fishing", "woodcutting", "cooking", "firemaking", "smithing", "thieving",
                                "crafting", "fletching", "agility", "herblore", "hunter", "ranged", "runecrafting", "magic", "melee"
                        },
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
        new ScriptUploader().uploadAndStart(
                "mMain",
                "christianflores@grind.rs",
                "emulator-5554",
                true,
                true
        );
    }

    public static final int MIN_TIME_LIMIT = 3600000;
    public static final int MAX_TIME_LIMIT = 5400000;
    public static volatile String runningSkill;
    public static String state;
    public static Boolean shouldBank = true;
    Executor taskHandler = Executors.newSingleThreadExecutor();
    public static final AtomicBoolean skillRunning = new AtomicBoolean(false);
    public static final Stopwatch runtime = new Stopwatch();

    private static final Map<String, Start> skillStarters = new HashMap<>();
    static {
        skillStarters.put("agility", new StartAgility());
        skillStarters.put("cooking", new StartCooking());
        skillStarters.put("crafting", new StartCrafting());
        skillStarters.put("firemaking", new StartFiremaking());
        skillStarters.put("fishing", new StartFishing());
        skillStarters.put("fletching", new StartFletching());
        skillStarters.put("herblore", new StartHerblore());
        skillStarters.put("hunter", new StartHunter());
        skillStarters.put("magic", new StartMagic());
        skillStarters.put("melee", new StartMelee());
        skillStarters.put("mining", new StartMining());
        skillStarters.put("ranged", new StartRanged());
        skillStarters.put("runecrafting", new startRunecrafting());
        skillStarters.put("smithing", new StartSmithing());
        skillStarters.put("thieving", new StartThieving());
        skillStarters.put("woodcutting", new StartWoodcutting());
    }


    @Override
    public void onStart() {
        String skill = getOption("Mode");
        runningSkill = "Determining..";
        state = "Starting...";
        InteractionsHelper.cameraCheck();

        final DateTimeFormatter TIMER_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        final Duration[] remainingDuration = {Duration.ofMillis(runtime.timeLeft())};
        final LocalTime[] remainingTime = {LocalTime.MIDNIGHT.plus(remainingDuration[0])};
        final String[] formattedTime = {TIMER_FORMAT.format(remainingTime[0])};

        PaintBuilder builder = new PaintBuilder()
                .addString("Mode: ", () -> skill)
                .addString("State: ", () -> state);

        if (skill.equals("progressive")) {
            builder.addString("Running skill: ", () -> runningSkill)
            .addString("Skill switch: ", () -> formattedTime[0]);
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
                .trackSkill(Skill.Hunter, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Agility, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Ranged, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Runecrafting, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Magic, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Defence, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Strength, TrackSkillOption.LevelProgressBar)
                .trackSkill(Skill.Attack, TrackSkillOption.LevelProgressBar);
        Paint p = builder.x(40).y(100).build();
        addPaint(p);

        executor.scheduleAtFixedRate(() -> {
            remainingDuration[0] = Duration.ofMillis(runtime.timeLeft());
            remainingTime[0] = LocalTime.MIDNIGHT.plus(remainingDuration[0]);
            formattedTime[0] = TIMER_FORMAT.format(remainingTime[0]);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void poll() {
        if (SkillData.allSkillsDone()) {
            ScriptManager.INSTANCE.stop();
        }
        String skill = getOption("Mode");

        if (skill.equals("progressive")) {
            List<Start> tasks = new ArrayList<>(skillStarters.values());
            if (tasks.isEmpty()) {
                ScriptManager.INSTANCE.stop();
            } else {
                taskHandler.execute(() -> {
                    if (ScriptManager.INSTANCE.isStopping()) {
                        ScriptManager.INSTANCE.stop();
                    }

                    mMain.state = "Resetting task";
                    if (!mMain.shouldBank) {
                        mMain.shouldBank = true;
                        System.out.println("shouldBank set true");
                    }

                    Start skillLoop = tasks.get(Random.nextInt(0, tasks.size())); //Randomize next task
                    System.out.println("New task chosen: " + skillLoop.getClass().getSimpleName());

                    if (!skillRunning.get()) {
                        runtime.reset(Random.nextInt(MIN_TIME_LIMIT, MAX_TIME_LIMIT)); //Randomize runtime value
                        System.out.println("Runtime reset to: " + runtime.timeLeft() + "ms");

                        skillRunning.set(true); //Set taskRunning to true after we've randomized task+runtime
                        System.out.println("Taskrunning set true");
                    }
                    //Enter loop of running the task!
                    while (!ScriptManager.INSTANCE.isStopping() && !runtime.hasFinished() && skillRunning.get()) {
                        if (Game.loggedIn()) {
                            skillLoop.run();
                        }
                    }
                    tasks.removeIf(task -> SkillData.skillsMap.get(mMain.runningSkill)); //Remove task if its marked done!
                    skillRunning.set(false); //Finally, set taskRunning to false, so we're ready for the next skill task.
                });
            }
        } else {
            Start start = skillStarters.get(skill);
            if (SkillData.skillsMap.get(skill)) {
                ScriptManager.INSTANCE.stop();
            } else {
                start.run();
            }
        }
    }

    public static class Stopwatch {
        private long stopTime;

        public Stopwatch() {
            stopTime = 0;
        }

        public void reset(long time) {
            long startTime = System.currentTimeMillis();
            stopTime = startTime + time;
        }

        public long timeLeft() {
            return (stopTime - System.currentTimeMillis());
        }

        public boolean hasFinished() {
            return System.currentTimeMillis() >= stopTime;
        }
    }

    public interface Start {
        void run();
    }

    @Subscribe
    public void messaged(MessageEvent messageEvent) {
        SystemMessageManager manager = SystemMessageManager.INSTANCE;
        manager.messageRecieved(messageEvent);
    }
}