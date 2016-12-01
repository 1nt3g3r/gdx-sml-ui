package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.integer.gdx.xml.ui.setup.ActorSetup;

public class GroupSetup extends ActorSetup {
    @Override
    public void setup() {
        Group group = (Group) actorDef.getActor();

        if (hasValue("transform")) {
            group.setTransform(bool("transform"));
        }

        if (hasValue("debugAll") && bool("debugAll")) {
            group.debugAll();
        }
    }
}
