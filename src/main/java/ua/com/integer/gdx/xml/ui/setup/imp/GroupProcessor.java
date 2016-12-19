package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class GroupProcessor extends XUIProcessor {
    @Override
    public void setup() {
        Group group = (Group) XUIElement.getActor();

        if (hasValue("transform")) {
            group.setTransform(bool("transform"));
        }

        if (hasValue("debugAll") && bool("debugAll")) {
            group.debugAll();
        }
    }
}
