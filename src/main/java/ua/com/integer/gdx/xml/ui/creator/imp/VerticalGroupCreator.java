package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

public class VerticalGroupCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        VerticalGroup result = new VerticalGroup();
        result.setName("VerticalGroup");
        return result;
    }
}
