package ua.com.integer.gdx.xml.ui.creator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.creator.imp.GroupCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.ImageCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.LabelCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.SimpleXUICreator;
import ua.com.integer.gdx.xml.ui.element.XUIElement;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

public abstract class XUICreator {
	private static ObjectMap<String, XUICreator> creators = new ObjectMap<String, XUICreator>();

	public static void init() {
		creators.clear();

		//register("linkedActor", new LinkedXUICreator());
		register("actor", new SimpleXUICreator());
		register("group", new GroupCreator());
		register("image", new ImageCreator());
		register("label", new LabelCreator());
	}

	public static void register(String name, XUICreator creator) {
		creators.put(name, creator);
	}

	protected XUIElement element;

	protected abstract Actor create(String packageName);

	public static Actor createActor(String name, XUIElement element) {
		XUICreator creator = creators.get(name);
		if (creator != null) {
			creator.element = element;
			replaceVariables(element);
			Actor result = creator.create(name);
			result.setUserObject(element);
			return result;
		}
		return null;
	}

	private static void replaceVariables(XUIElement element) {
		for(String key : element.attributes.keys()) {
			String value = element.attributes.get(key);

			if (value.contains("$")) {
				element.attributes.put(key, replaceVariables(value));
			}
		}
	}

	private static String replaceVariables(String value) {
		Array<String> tmpArray = new Array<>();
		for(int i = 0; i < value.length(); i++) {
			int index1 = value.indexOf("$", i);
			if (index1 != -1) {
				int index2 = value.indexOf("$", index1 + 1);

				i = index2;

				String var = value.substring(index1 + 1, index2);
				tmpArray.add(var);
			}
		}

		for(String var : tmpArray) {
			String varValue = XUIAssetsAccess.getVariable(var);
			value = value.replace("$" + var + "$", varValue);
		}

		return value;
	}
}
