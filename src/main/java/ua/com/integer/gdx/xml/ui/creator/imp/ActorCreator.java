package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

/**
 * Creates new {@link Actor} with <b>Actor</b> name
 */
public class ActorCreator extends XUICreator {
	@Override
	public Actor create(String type) {
		Actor result = new Actor();
		result.setName("Actor");
		return result;
	}
}
