package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelProcessor extends ua.com.integer.gdx.xml.ui.processor.XUIProcessor {
    @Override
    public void process() {
        Label l = (Label) element.resultActor;

        if (hasValue("text")) {
            l.setText(getAttribute("text"));
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
