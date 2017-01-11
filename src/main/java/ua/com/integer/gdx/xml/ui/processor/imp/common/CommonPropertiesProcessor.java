package ua.com.integer.gdx.xml.ui.processor.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class CommonPropertiesProcessor extends XUIProcessor {
    @Override
    public void process() {
        Actor a = element.resultActor;

        if (hasValue("name")) {
            a.setName(getAttribute("name"));
        }

        if (hasValue("debug")) {
            a.setDebug(bool("debug"));
        }

        if (hasValue("touchable")) {
            a.setTouchable(touchable("touchable"));
        }

        if (hasValue("visible")) {
            a.setVisible(bool("visible"));
        }

        if (hasValue("color")) {
            a.setColor(getColor("color"));
        }

        if (hasValue("toBack")) {
            a.toBack();
        }

        if (hasValue("toFront")) {
            a.toFront();
        }

        if (hasValue("zIndex")) {
            a.setZIndex(getInt("zIndex"));
        }
    }
}
