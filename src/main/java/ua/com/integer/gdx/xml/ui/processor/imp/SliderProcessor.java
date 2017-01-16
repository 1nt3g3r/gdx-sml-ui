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
            slider.setDisabled(bool("disabled"));
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

        if (hasAttribute("knobMinWidth")) {
            float width = eval("knobMinWidth");
            setMinWidth(width, knobs);
        }

        if (hasAttribute("knobMinHeight")) {
            float height = eval("knobMinHeight");
            setMinHeight(height, knobs);
        }

        if (hasAttribute("knobLeftWidth")) {
            float width = eval("knobLeftWidth");
            setLeftWidth(width, knobs);
        }

        if (hasAttribute("knobRightWidth")) {
            float width = eval("knobRightWidth");
            setRightWidth(width, knobs);
        }

        if (hasAttribute("knobTopHeight")) {
            float height = eval("knobTopHeight");
            setTopHeight(height, knobs);
        }

        if (hasAttribute("knobBottomHeight")) {
            float height = eval("knobBottomHeight");
            setBottomHeight(height, knobs);
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

        if (hasAttribute("backgroundMinWidth")) {
            float width = eval("backgroundMinWidth");
            setMinWidth(width, drawables);
        }

        if (hasAttribute("backgroundMinHeight")) {
            float height = eval("backgroundMinHeight");
            setMinHeight(height, drawables);
        }

        if (hasAttribute("backgroundLeftWidth")) {
            float width = eval("backgroundLeftWidth");
            setLeftWidth(width, drawables);
        }

        if (hasAttribute("backgroundRightWidth")) {
            float width = eval("backgroundRightWidth");
            setRightWidth(width, drawables);
        }

        if (hasAttribute("backgroundTopHeight")) {
            float height = eval("backgroundTopHeight");
            setTopHeight(height, drawables);
        }

        if (hasAttribute("backgroundBottomHeight")) {
            float height = eval("backgroundBottomHeight");
            setBottomHeight(height, drawables);
        }
    }

    private void setMinWidth(float width, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setMinWidth(width);
            }
        }
    }

    private void setMinHeight(float height, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setMinHeight(height);
            }
        }
    }

    private void setLeftWidth(float width, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setLeftWidth(width);
            }
        }
    }

    private void setRightWidth(float width, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setRightWidth(width);
            }
        }
    }

    private void setTopHeight(float height, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setTopHeight(height);
            }
        }
    }

    private void setBottomHeight(float height, Drawable ... drawables) {
        for(Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setBottomHeight(height);
            }
        }
    }
}
