package ua.com.integer.gdx.xml.ui.setup.imp.common.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ua.com.integer.gdx.xml.ui.eval.Eval;
import ua.com.integer.gdx.xml.ui.setup.ActorSetup;

public class VisualEffectsSetup extends ActorSetup {
    private Actor a;

    @Override
    public void setup() {
        a = actorDef.getActor();

        checkForFloatingEffect();
        checkForMoveUpDownEffect();
        checkForColorChangeEffect();
    }

    private void checkForFloatingEffect() {
        if (hasValue("floatingEffect")) {
            a.addAction(new FloatingEffectAction(a, Eval.eval(a, getValue("floatingEffect"))));
        }
    }

    private void checkForMoveUpDownEffect() {
        if (hasValue("moveUpDown")) {
            float moveInterval = Eval.eval(a, getValue("moveUpDown"));
            float moveTime = 0.1f;
            if (hasValue("moveUpDownTime")) {
                moveTime = getFloat("moveUpDownTime");
            }
            a.addAction(Actions.forever(
                    Actions.sequence(
                            Actions.moveBy(0, moveInterval, moveTime),
                            Actions.moveBy(0, -moveInterval, moveTime)
                    )
            ));
        }
    }

    private void checkForColorChangeEffect() {
        if (hasValue("changeColorOnClick")) {
            String colorName = getValue("changeColorOnClick");
            Color color = Color.GRAY;
            if (!colorName.equals("default")) {
                color = color("changeColorOnClick");
            }

            a.addListener(new ColorChangeListener(color));
        }
    }
}
