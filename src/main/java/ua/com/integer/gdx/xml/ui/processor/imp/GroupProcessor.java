package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class GroupProcessor extends XUIProcessor {
    @Override
    public void process() {
        Group group = (Group) element.resultActor;

        if (hasValue("transform")) {
            group.setTransform(bool("transform"));
        }

        if (hasValue("debugAll") && bool("debugAll")) {
            group.debugAll();
        }
    }
}
