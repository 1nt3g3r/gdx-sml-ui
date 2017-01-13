package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Setup {@link Label}.
 */
public class LabelProcessor extends ua.com.integer.gdx.xml.ui.processor.XUIProcessor {
    @Override
    public void process() {
        Label l = (Label) element.resultActor;

        if (hasAttribute("text")) {
            l.setText(getAttribute("text"));
        }

        if (hasAttribute("align")) {
            l.setAlignment(getAlign("align"));
        }

        if (hasAttribute("fontScale")) {
            l.setFontScale(eval("fontScale"));
        }

        if (hasAttribute("fontScaleX")) {
            l.setFontScaleX(eval("fontScaleX"));
        }

        if (hasAttribute("fontScaleY")) {
            l.setFontScaleY(eval("fontScaleY"));
        }

        if (hasAttribute("fontColor")) {
            l.getStyle().fontColor = getColor("fontColor");
        }

        if (hasAttribute("wrap")) {
            l.setWrap(bool("wrap"));
        }
    }
}
