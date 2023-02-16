package MagicCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Components;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widgets;

import java.util.concurrent.Callable;

public class MagicHelpers {
    private static final int WIDGET_AUTOCAST = 201;
    private static final int WIDGET_AUTOCAST_SPELLS_COMPONENT = 1;
    private static final int VARPBIT_AUTOCASTING = 43;
    private static final int VARPBIT_AUTOCASTING_SPELL = 108;

    public static boolean isAutoCasting() {
        return Varpbits.varpbit(VARPBIT_AUTOCASTING) == 4;
    }

    private static Component getAutocastComponent() {
        return Components.stream(WIDGET_AUTOCAST, WIDGET_AUTOCAST_SPELLS_COMPONENT).first();
    }

    public static boolean isAutoCastOpen() {
        return getAutocastComponent().visible();
    }

    public static boolean openAutocastTab() {
        if (isAutoCastOpen()) {
            return true;
        }

        if (Game.tab(Game.Tab.ATTACK) && Widgets.component(593, 26).interact("Choose spell")) {
            return Condition.wait((Callable<Boolean>) MagicHelpers::isAutoCastOpen);
        }

        return false;
    }

    public static AutoCastSpell getAutoCastSpell() {
        int varpbitValue = Varpbits.varpbit(VARPBIT_AUTOCASTING_SPELL);

        if (varpbitValue == 0) {
            return AutoCastSpell.NONE;
        }

        for (AutoCastSpell spell : AutoCastSpell.values()) {
            if (spell.getVarpbitValue() == varpbitValue) {
                return spell;
            }
        }
        return AutoCastSpell.NONE;
    }

    public static boolean setAutoCast(AutoCastSpell spell) {
        Component component = Widgets.component(WIDGET_AUTOCAST, WIDGET_AUTOCAST_SPELLS_COMPONENT, spell.getComponent());
        if (component == Component.Companion.getNil() || spell == AutoCastSpell.NONE) {
            return false;
        }
        if (component.click()) {
            return Condition.wait((Callable<Boolean>) () -> getAutoCastSpell() == spell);
        }
        return false;
    }

    public enum AutoCastSpell {
        NONE(null, 0, 0),
        AIR_STRIKE(Magic.Spell.WIND_STRIKE, 3, 1),
        WATER_STRIKE(Magic.Spell.WATER_STRIKE, 5, 2),
        EARTH_STRIKE(Magic.Spell.EARTH_STRIKE, 19, 3),
        FIRE_STRIKE(Magic.Spell.FIRE_STRIKE, 9, 4),
        WIND_BOLT(Magic.Spell.WIND_BOLT, 11, 5),
        WATER_BOLT(Magic.Spell.WATER_BOLT, 13, 6),
        EARTH_BOLT(Magic.Spell.EARTH_BOLT, 15, 7),
        FIRE_BOLT(Magic.Spell.FIRE_BOLT, 17, 8);

        private final Magic.Spell spell;
        private final int varpbitValue;
        private final int component;

        AutoCastSpell(Magic.Spell spell, int varpbitValue, int component) {
            this.spell = spell;
            this.varpbitValue = varpbitValue;
            this.component = component;
        }
        public Magic.Spell getSpell() {
            return spell;
        }
        public int getVarpbitValue() {
            return varpbitValue;
        }
        public int getComponent() {
            return component;
        }
    }
}
