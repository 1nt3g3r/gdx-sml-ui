package ua.com.integer.gdx.xml.ui.setup.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class CommonPropertiesProcessor extends XUIProcessor {
    @Override
    public void setup() {
        Actor a = XUIElement.getActor();

        if (hasValue("name")) {
            a.setName(getValue("name"));
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
