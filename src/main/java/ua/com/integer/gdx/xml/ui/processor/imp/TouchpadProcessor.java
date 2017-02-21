package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class TouchpadProcessor extends XUIProcessor {
    private Touchpad touchpad;

    @Override
    public void process() {
        touchpad = (Touchpad) element.resultActor;

        if (hasAttribute("deadzoneRadius")) {
            touchpad.setDeadzone(eval("deadzoneRadius"));
        }

        if (hasAttribute("resetOnTouch")) {
            touchpad.setResetOnTouchUp(getBoolean("resetOnTouch"));
        }

        Touchpad.TouchpadStyle style = touchpad.getStyle();

        if (hasAttribute("background")) {
            style.background = getDrawableCopy("background");
        }
        setupDrawableParams(style.background, "background");

        if (hasAttribute("knob")) {
            style.knob = getDrawableCopy("knob");
        }
        setupDrawableParams(style.knob, "knob");

        touchpad.setStyle(style);
    }
}
