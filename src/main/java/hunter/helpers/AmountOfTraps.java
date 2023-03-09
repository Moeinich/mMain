package hunter.helpers;

import org.powbot.api.rt4.walking.model.Skill;

import java.util.HashMap;
import java.util.Map;

public class AmountOfTraps {
        private static Map<Integer, Integer> skillOutputMap;
        public AmountOfTraps() {
            skillOutputMap = new HashMap<>();
            skillOutputMap.put(80, 5);
            skillOutputMap.put(60, 4);
            skillOutputMap.put(40, 3);
            skillOutputMap.put(20, 2);
            skillOutputMap.put(1, 1);
            // add more mappings as needed
        }

    public static int getOutputValue(Skill skill) {
        for (Map.Entry<Integer, Integer> entry : skillOutputMap.entrySet()) {
            if (skill.realLevel() >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 1; // no matching skill level found
    }
}
