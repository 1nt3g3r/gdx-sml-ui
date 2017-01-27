package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Setup {@link Group}
 */
public class GroupProcessor extends XUIProcessor {
    @Override
    public void process() {
        Group group = (Group) element.resultActor;

        if (hasAttribute("transform")) {
            group.setTransform(getBoolean("transform"));
        }

        if (hasAttribute("debugAll") && getBoolean("debugAll")) {
            group.debugAll();
        }
    }
}
