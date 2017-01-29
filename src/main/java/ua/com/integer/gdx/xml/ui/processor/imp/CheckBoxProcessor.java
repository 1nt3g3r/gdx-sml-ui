package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Setup {@link CheckBox}
 */
public class CheckBoxProcessor extends TextButtonProcessor {
    private CheckBox checkBox;

    @Override
    public void process() {
        super.process();

        checkBox = (CheckBox) element.resultActor;
        setupStyle();
    }

    private void setupStyle() {
        CheckBox.CheckBoxStyle style = checkBox.getStyle();

        if (hasAttribute("checkboxOn")) {
            style.checkboxOn = getDrawableCopy("checkboxOn");
        }
        setupDrawableParams(style.checkboxOn, "checkboxOn");

        if (hasAttribute("checkboxOff")) {
            style.checkboxOff = getDrawableCopy("checkboxOff");
        }
        setupDrawableParams(style.checkboxOff, "checkboxOff");

        if (hasAttribute("checkboxOver")) {
            style.checkboxOver = getDrawableCopy("checkboxOver");
        }
        setupDrawableParams(style.checkboxOver, "checkboxOver");

        if (hasAttribute("checkboxOnDisabled")) {
            style.checkboxOnDisabled = getDrawableCopy("checkboxOnDisabled");
        }
        setupDrawableParams(style.checkboxOnDisabled, "checkboxOnDisabled");

        if (hasAttribute("checkboxOffDisabled")) {
            style.checkboxOffDisabled = getDrawableCopy("checkboxOffDisabled");
        }
        setupDrawableParams(style.checkboxOffDisabled, "checkboxOffDisabled");
    }
}
