package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class LabelProcessor extends XUIProcessor {
    @Override
    public void setup() {
        Label l = (Label) XUIElement.getActor();

        if (hasValue("text")) {
            l.setText(getValue("text"));
        }

        if (hasValue("align")) {
            l.setAlignment(getAlign("align"));
        }

        if (hasValue("fontScale")) {
            l.setFontScale(eval("fontScale"));
        }

        if (hasValue("fontScaleX")) {
            l.setFontScaleX(eval("fontScaleX"));
        }

        if (hasValue("fontScaleY")) {
            l.setFontScaleY(eval("fontScaleY"));
        }

        if (hasValue("fontColor")) {
            l.getStyle().fontColor = getColor("fontColor");
        }

        if (hasValue("wrap")) {
            l.setWrap(bool("wrap"));
        }
    }
}
