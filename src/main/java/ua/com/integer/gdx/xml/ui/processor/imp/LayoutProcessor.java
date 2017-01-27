package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.utils.Layout;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Setup {@link Layout}
 */
public class LayoutProcessor extends XUIProcessor {
    @Override
    public void process() {
        Layout layout = (Layout) element.resultActor;

        if (hasAttribute("layoutEnabled")) {
            layout.setLayoutEnabled(getBoolean("layoutEnabled"));
        }

        if (hasAttribute("fillParent")) {
            layout.setFillParent(getBoolean("fillParent"));
        }
    }
}
