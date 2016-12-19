package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

public class SimpleXUICreator extends XUICreator {
	@Override
	public Actor create(String packageName) {
		Actor result = new Actor();
		result.setName("Actor");
		return result;
	}
}
