package ua.com.integer.gdx.xml.ui.processor.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Apply common for all actors properties. It's properties <b>name</b>, <b>visible</b>
 */
public class CommonPropertiesProcessor extends XUIProcessor {
    @Override
    public void process() {
        Actor a = element.resultActor;

        if (hasAttribute("name")) {
            a.setName(getAttribute("name"));
        }

        if (hasAttribute("debug")) {
            a.setDebug(getBoolean("debug"));
        }

        if (hasAttribute("touchable")) {
            a.setTouchable(touchable("touchable"));
        }

        if (hasAttribute("visible")) {
            a.setVisible(getBoolean("visible"));
        }

        if (hasAttribute("color")) {
            a.setColor(getColor("color"));
        }

        if (hasAttribute("toBack")) {
            a.toBack();
        }

        if (hasAttribute("toFront")) {
            a.toFront();
        }

        if (hasAttribute("zIndex")) {
            a.setZIndex(getInt("zIndex"));
        }
    }
}
