package ua.com.integer.gdx.xml.ui.creator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.ActorDef;
import ua.com.integer.gdx.xml.ui.creator.imp.GroupCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.ImageCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.LabelCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.SimpleActorCreator;

public abstract class ActorCreator {
	private static ObjectMap<String, ActorCreator> creators = new ObjectMap<String, ActorCreator>();
	static {
		register("actor", new SimpleActorCreator());
		register("group", new GroupCreator());
		register("image", new ImageCreator());
        register("label", new LabelCreator());
	}

	public static void register(String name, ActorCreator creator) {
		creators.put(name, creator);
	}

	protected ActorDef actorDef;

	protected abstract Actor create(String packageName);

	public static Actor createActor(String name, ActorDef actorDef) {
		ActorCreator creator = creators.get(name);
		if (creator != null) {
			creator.actorDef = actorDef;
			return creator.create(name);
		}
		return null;
	}
}
