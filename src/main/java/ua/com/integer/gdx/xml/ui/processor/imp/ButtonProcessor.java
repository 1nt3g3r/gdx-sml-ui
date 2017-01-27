package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Setup {@link Button}
 */
public class ButtonProcessor extends XUIProcessor {
    private Button button;

    @Override
    public void process() {
        button = (Button) element.resultActor;

        if (hasAttribute("disabled")) {
            button.setDisabled(getBoolean("disabled"));
        }

        if (hasAttribute("checked")) {
            button.setChecked(getBoolean("checked"));
        }

        if (hasAttribute("programmaticChangeEvents")) {
            button.setProgrammaticChangeEvents(getBoolean("programmaticChangeEvents"));
        }

        setupStyle();
    }

    private void setupStyle() {
        Button.ButtonStyle buttonStyle = (Button.ButtonStyle) button.getStyle();

        if (hasAttribute("styleUp")) {
            buttonStyle.up = getDrawable("styleUp");
        }
        setupDrawableParams(buttonStyle.up, "styleUp");

        if (hasAttribute("styleDown")) {
            buttonStyle.down = getDrawable("styleDown");
        }
        setupDrawableParams(buttonStyle.down, "styleDown");

        if (hasAttribute("styleChecked")) {
            buttonStyle.checked = getDrawable("styleChecked");
        }
        setupDrawableParams(buttonStyle.checked, "styleChecked");

        if (hasAttribute("styleCheckedOver")) {
            buttonStyle.checkedOver = getDrawable("styleCheckedOver");
        }
        setupDrawableParams(buttonStyle.checkedOver, "styleCheckedOver");

        if (hasAttribute("styleDisabled")) {
            buttonStyle.disabled = getDrawable("styleDisabled");
        }
        setupDrawableParams(buttonStyle.disabled, "styleDisaled");

        if (hasAttribute("styleOver")) {
            buttonStyle.over = getDrawable("styleOver");
        }
        setupDrawableParams(buttonStyle.over, "styleOver");

        if (hasAttribute("pressedOffsetX")) {
            buttonStyle.pressedOffsetX = eval("pressedOffsetX");
        }

        if (hasAttribute("pressedOffsetY")) {
            buttonStyle.pressedOffsetY = eval("pressedOffsetY");
        }

        if (hasAttribute("unpressedOffsetX")) {
            buttonStyle.unpressedOffsetX = eval("unpressedOffsetX");
        }

        if (hasAttribute("unpressedOffsetY")) {
            buttonStyle.unpressedOffsetY = eval("unpressedOffsetY");
        }

        if (hasAttribute("chekedOffsetX")) {
            buttonStyle.checkedOffsetX = eval("checkedOffsetX");
        }

        if (hasAttribute("chekedOffsetY")) {
            buttonStyle.checkedOffsetX = eval("checkedOffsetY");
        }
    }
}
