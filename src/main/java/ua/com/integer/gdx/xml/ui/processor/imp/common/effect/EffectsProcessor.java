package ua.com.integer.gdx.xml.ui.processor.imp.common.effect;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ua.com.integer.gdx.xml.ui.util.ActorMathEval;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class EffectsProcessor extends XUIProcessor {
    private Actor a;

    @Override
    public void process() {
        a = element.resultActor;

        checkForFloatingEffect();
        checkForMoveUpDownEffect();
        checkForColorChangeEffect();
        checkForSoundClickEffect();
    }

    private void checkForFloatingEffect() {
        if (hasValue("floatingEffect")) {
            a.addAction(new FloatingEffectAction(a, ActorMathEval.eval(a, getAttribute("floatingEffect"))));
        }
    }

    private void checkForMoveUpDownEffect() {
        if (hasValue("moveUpDown")) {
            float moveInterval = ActorMathEval.eval(a, getAttribute("moveUpDown"));
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
            String colorName = getAttribute("changeColorOnClick");
            Color color = Color.GRAY;
            if (!colorName.equals("default")) {
                color = getColor("changeColorOnClick");
            }

            a.addListener(new ua.com.integer.gdx.xml.ui.processor.imp.common.effect.ColorChangeListener(color));
        }
    }

    private void checkForSoundClickEffect() {
        if (hasValue("soundOnClick")) {
            Sound sound = getSound("soundOnClick");
            a.addListener(new ua.com.integer.gdx.xml.ui.processor.imp.common.effect.SoundClickListener(sound));
        }
    }
}
