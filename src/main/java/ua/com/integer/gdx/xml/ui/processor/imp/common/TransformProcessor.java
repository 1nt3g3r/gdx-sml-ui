package ua.com.integer.gdx.xml.ui.processor.imp.common;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Setup transform (position and size) for actor.
 */
public class TransformProcessor extends XUIProcessor {
    @Override
    public void process() {
        Actor a = element.resultActor;

        if (hasAttribute("width")) {
            a.setWidth(eval("width"));
        }

        if (hasAttribute("height")) {
            a.setHeight(eval("height"));
        }

        if (hasAttribute("size")) {
            float size = eval("size");
            a.setSize(size, size);
        }

        if (hasAttribute("x")) {
            a.setX(eval("x"));
        }

        if (hasAttribute("y")) {
            a.setY(eval("y"));
        }

        if (hasAttribute("scaleX")) {
            a.setScaleX(eval("scaleX"));
        }

        if (hasAttribute("scaleY")) {
            a.setScaleY(eval("scaleY"));
        }

        if (hasAttribute("scale")) {
            a.setScale(eval("scale"));
        }

        if (hasAttribute("rotation")) {
            a.setRotation(eval("rotation"));
        }

        if (hasAttribute("originX")) {
            a.setOriginX(eval("originX"));
        }

        if (hasAttribute("originY")) {
            a.setOriginY(eval("originY"));
        }

        if (hasAttribute("origin")) {
            a.setOrigin(getAlign("origin"));
        }

        if (hasAttribute("alignPos")) {
            a.setPosition(a.getX(), a.getY(), getAlign("alignPos"));
        }

        if (hasAttribute("position")) {
            setupPosition();
        }
    }

    private void setupPosition() {
        Actor a = element.resultActor;

        if (!a.hasParent()) {
            return;
        }

        String[] parts = getAttribute("position").replace(" ", "").split("\\|");
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

            if (part.equals("center")) {
                x = (a.getParent().getWidth() - a.getWidth())/2f;
                y = (a.getParent().getHeight() - a.getHeight())/2f;
            }
        }

        a.setPosition(x, y);
    }
}
