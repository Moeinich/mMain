package meleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widget;
import org.powbot.api.rt4.Widgets;

import helpers.Task;
import script.mMain;

public class SetAttackStyle extends Task {
    int autoRetaliateWidget = 593;
    int autoRetaliateComponent = 30;
    int autoRetaliateVarp = 172;

    @Override
    public boolean activate() {
        return Combat.style() != MeleeData.AttackStyle() || !Combat.autoRetaliate();
    }

    @Override
    public boolean execute() {
        if (!Combat.autoRetaliate()) {
            EnableAutoRetaliate();
            Condition.wait(Combat::autoRetaliate, 350, 10);
        }
        System.out.println("Switching attack style to " + MeleeData.AttackStyle());
        mMain.state = "Set style";
        Combat.style(MeleeData.AttackStyle());
        return false;
    }
    public void EnableAutoRetaliate() {
        if (Game.tab(Game.Tab.ATTACK)) {
            Widget widget = Widgets.widget(autoRetaliateWidget);
            if (widget.valid()) {
                Component autoRetaliateComp = widget.component(autoRetaliateComponent);
                if (autoRetaliateComp.visible()) {
                    autoRetaliateComp.click();
                    Condition.wait(() -> Varpbits.varpbit(autoRetaliateVarp) == 1, 200, 10);
                }
            }
        }
    }
}

