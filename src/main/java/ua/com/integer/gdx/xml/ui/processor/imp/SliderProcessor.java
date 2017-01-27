package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class SliderProcessor extends XUIProcessor {
    private Slider slider;
    private Slider.SliderStyle style;

    @Override
    public void process() {
        slider = (Slider) element.resultActor;
        style = slider.getStyle();

        if (hasAttribute("disabled")) {
            slider.setDisabled(getBoolean("disabled"));
        }

        if (hasAttribute("value")) {
            slider.setValue(getFloat("value"));
        }

        setupDisabledBackground();
        setupDisabledKnob();
        setupKnobDownAndKnobOver();
        setupKnobBeforeAndKnobAfter();

        setupBackgroundSize();
        setupKnobSize();
    }

    private void setupDisabledBackground() {
        if (hasAttribute("disabledBackground")) {
            style.disabledBackground = getDrawableCopy("disabledBackground");
        }
    }

    private void setupDisabledKnob() {
        if (hasAttribute("disabledKnob")) {
            style.disabledKnob = getDrawableCopy("disabledKnob");
        }
    }

    private void setupKnobDownAndKnobOver() {
        if (hasAttribute("knobDown")) {
            style.knobDown = getDrawableCopy("knobDown");
        }

        if (hasAttribute("knobOver")) {
            style.knobOver = getDrawableCopy("knobOver");
        }
    }

    private void setupKnobBeforeAndKnobAfter() {
        if (hasAttribute("knobBefore")) {
            style.knobBefore = getDrawableCopy("knobBefore");
        }
        if (hasAttribute("disabledKnobBefore")) {
            style.disabledKnobBefore = getDrawableCopy("disabledKnobBefore");
        }
        if (hasAttribute("knobAfter")) {
            style.knobAfter = getDrawableCopy("knobAfter");
        }
        if (hasAttribute("disabledKnobAfter")) {
            style.disabledKnobAfter = getDrawableCopy("disabledKnobAfter");
        }
    }

    private void setupKnobSize() {
        Drawable[] knobs = new Drawable[] {
                style.knob,
                style.disabledKnob,
                style.knobDown,
                style.knobOver
        };

        for(int i = 0; i < knobs.length; i++) {
            setupDrawableParams(knobs[i], "knob");
        }
    }

    private void setupBackgroundSize() {
        Drawable[] drawables = new Drawable[] {
                style.background,
                style.disabledBackground,

                style.knobBefore,
                style.knobAfter,

                style.disabledKnobBefore,
                style.disabledKnobAfter
        };

        for(int i = 0; i < drawables.length; i++) {
            setupDrawableParams(drawables[i], "background");
        }
    }
}
