package ua.com.integer.gdx.xml.ui.setup.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class TransformProcessor extends XUIProcessor {
    @Override
    public void setup() {
        Actor a = XUIElement.getActor();

        if (hasValue("width")) {
            a.setWidth(eval("width"));
        }

        if (hasValue("height")) {
            a.setHeight(eval("height"));
        }

        if (hasValue("size")) {
            float size = eval("size");
            a.setSize(size, size);
        }

        if (hasValue("x")) {
            a.setX(eval("x"));
        }

        if (hasValue("y")) {
            a.setY(eval("y"));
        }

        if (hasValue("scaleX")) {
            a.setScaleX(eval("scaleX"));
        }

        if (hasValue("scaleY")) {
            a.setScaleY(eval("scaleY"));
        }

        if (hasValue("scale")) {
            a.setScale(eval("scale"));
        }

        if (hasValue("rotation")) {
            a.setRotation(eval("rotation"));
        }

        if (hasValue("originX")) {
            a.setOriginX(eval("originX"));
        }

        if (hasValue("originY")) {
            a.setOriginY(eval("originY"));
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
        Actor a = XUIElement.getActor();

        if (!a.hasParent()) {
            return;
        }

        String[] parts = getValue("position").replace(" ", "").split("\\|");
        float x = a.getX();
        float y = a.getY();
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
