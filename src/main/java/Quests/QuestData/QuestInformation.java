package Quests.QuestData;

import org.powbot.api.rt4.Varpbits;

public class QuestInformation {
        private final Varpbits questVarbits;
        private final String[] foodName;

        private BaseQuestStep currentQuest;
        private String currentQuestStatus;
        private boolean complete;

        public QuestInformation(Varpbits questVarbits, String[] foodName) {
            this.questVarbits = questVarbits;
            this.foodName = foodName;
        }

        public Varpbits getQuestVarbits() {
            return questVarbits;
        }

        public BaseQuestStep getCurrentQuest() {
            return currentQuest;
        }

        public void setCurrentQuest(BaseQuestStep currentQuest) {
            this.currentQuest = currentQuest;
        }

        public String getCurrentQuestStatus() {
            return currentQuestStatus;
        }

        public void setCurrentQuestStatus(String currentQuestStatus) {
            this.currentQuestStatus = currentQuestStatus;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
}
