package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

/**
 * Creates simple {@link Group} with <b>Group</b> name
 */
public class GroupCreator extends XUICreator {
	@Override
	protected Actor create(String type) {
		Group group = new Group();
		group.setName("Group");
		return group;
	}
}
