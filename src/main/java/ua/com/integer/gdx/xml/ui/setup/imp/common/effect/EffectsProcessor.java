package ua.com.integer.gdx.xml.ui.setup.imp.common.effect;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ua.com.integer.gdx.xml.ui.eval.Eval;
import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class EffectsProcessor extends XUIProcessor {
    private Actor a;

    @Override
    public void setup() {
        a = XUIElement.getActor();

        checkForFloatingEffect();
        checkForMoveUpDownEffect();
        checkForColorChangeEffect();
        checkForSoundClickEffect();
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
                color = getColor("changeColorOnClick");
            }

            a.addListener(new ColorChangeListener(color));
        }
    }

    private void checkForSoundClickEffect() {
        if (hasValue("soundOnClick")) {
            Sound sound = getSound("soundOnClick");
            a.addListener(new SoundClickListener(sound));
        }
    }
}
