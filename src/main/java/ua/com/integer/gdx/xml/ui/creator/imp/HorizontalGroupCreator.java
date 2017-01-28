package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

/**
 * Creates new {@link HorizontalGroup} with <b>HorizontalGroup</b> name
 */
public class HorizontalGroupCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        HorizontalGroup result = new HorizontalGroup();
        result.setName("HorizontalGroup");
        return result;
    }
}
