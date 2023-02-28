package magicCombat;

import static magicCombat.MagicHelpers.isAutoCastOpen;

import org.powbot.api.rt4.Game;

import helpers.PlayerHelper;
import helpers.Task;
import script.mMain;

public class SetAutoCastSpell extends Task {
    @Override
    public boolean activate() {
        return MagicHelpers.getAutoCastSpell().getSpell() != MagicData.MagicSpell() && !MagicHelpers.isAutoCasting() && PlayerHelper.hasItem(MagicData.Runes);
    }
    @Override
    public boolean execute() {
        mMain.state = "Setting spell";
        Game.tab(Game.Tab.ATTACK);
        if (isAutoCastOpen() || MagicHelpers.openAutocastTab()) {
            MagicHelpers.AutoCastSpell[] spellValues = MagicHelpers.AutoCastSpell.values();
            for (MagicHelpers.AutoCastSpell spell : spellValues) {
                if (spell.getSpell() != null && spell.getSpell().equals(MagicData.MagicSpell())) {
                    System.out.println("Set autocast spell");
                    MagicHelpers.setAutoCast(spell);
                    System.out.println("Autocast spell set");
                    break;
                }
            }
        }
        return false;
    }
}
