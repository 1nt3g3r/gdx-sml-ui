package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Setup {@link TextButton}
 */
public class TextButtonProcessor extends ButtonProcessor {
    private TextButton textButton;

    @Override
    public void process() {
        super.process();

        textButton = (TextButton) element.resultActor;

        if (hasAttribute("text")) {
            textButton.setText(getAttribute("text"));
        }

        if (hasAttribute("font")) {
            textButton.getStyle().font = getFont("font");
        }

        setupStyle();
    }

    private void setupStyle() {
        TextButton.TextButtonStyle style = textButton.getStyle();
        if (hasAttribute("fontColor")) {
            style.fontColor = getColor("fontColor");
        }

        if (hasAttribute("downFontColor")) {
            style.downFontColor = getColor("downFontColor");
        }

        if (hasAttribute("overFontColor")) {
            style.overFontColor = getColor("overFontColor");
        }

        if (hasAttribute("checkedFontColor")) {
            style.checkedFontColor = getColor("checkedFontColor");
        }

        if (hasAttribute("checkedOverFontColor")) {
            style.checkedOverFontColor = getColor("checkedOverFontColor");
        }

        if (hasAttribute("disabledFontColor")) {
            style.disabledFontColor = getColor("disabledFontColor");
        }
    }
}
