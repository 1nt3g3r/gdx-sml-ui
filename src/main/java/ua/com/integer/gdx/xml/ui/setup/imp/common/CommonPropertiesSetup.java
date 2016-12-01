package ua.com.integer.gdx.xml.ui.setup.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.setup.ActorSetup;

public class CommonPropertiesSetup extends ActorSetup {
    @Override
    public void setup() {
        Actor a = actorDef.getActor();

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
            a.setColor(color("color"));
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

        if (hasValue("origin")) {
            a.setOrigin(getAlign("origin"));
        }

        if (hasValue("alignPos")) {
            a.setPosition(a.getX(), a.getY(), getAlign("alignPos"));
        }

        if (hasValue("position")) {
            setupPosition();
        }
    }

    private void setupPosition() {
        Actor a = actorDef.getActor();

        if (!a.hasParent()) {
            return;
        }

        String[] parts = getValue("position").replace(" ", "").split("\\|");
        float x = 0;
        float y = 0;
        for(String part : parts) {
            if (part.equals("left")) {
                x = 0;
            } else if (part.equals("right")) {
                x = a.getParent().getWidth() - a.getWidth();
            } else if (part.equals("centerHorizontal")) {
                x = (a.getParent().getWidth() - a.getWidth())/2f;
            }

            if (part.equals("bottom")) {
                y = 0;
            } else if (part.equals("top")) {
                y = a.getParent().getHeight() - a.getHeight();
            } else if (part.equals("centerVertical")) {
                y = (a.getParent().getHeight() - a.getHeight())/2f;
            }
        }

        a.setPosition(x, y);
    }
}
