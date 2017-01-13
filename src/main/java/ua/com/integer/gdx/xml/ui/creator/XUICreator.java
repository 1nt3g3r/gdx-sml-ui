package ua.com.integer.gdx.xml.ui.creator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.creator.imp.GroupCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.ImageCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.LabelCreator;
import ua.com.integer.gdx.xml.ui.creator.imp.ActorCreator;
import ua.com.integer.gdx.xml.ui.element.XUIElement;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Create actor by his type. Type of actor is just string (like <b>actor</b>, <b>group</b>)
 */
public abstract class XUICreator {
	private static ObjectMap<String, XUICreator> creators = new ObjectMap<String, XUICreator>();

    /**
     * Clear all creators and register creators for predefined actor types (like <b>actor</b>, <b>group</b>, etc)
     */
	public static void init() {
		creators.clear();

		register("actor", new ActorCreator());
		register("group", new GroupCreator());
		register("image", new ImageCreator());
		register("label", new LabelCreator());
	}

    /**
     * Register creator for given type
     */
	public static void register(String type, XUICreator creator) {
		creators.put(type, creator);
	}

	protected XUIElement element;

    /**
     * Implementation to create actor of specified type
     */
	protected abstract Actor create(String type);

    /**
     * Creates actor of given type from given XMLElement.
     *
     * Given XMLElement can contain some user-defined variables (like $myVar$). That variables will be replaced by his values before creating actor.
     *
     * Creation isn't recursive (i.e. if actor is group and has children, that children will not be created, only single actor).
     */
	public static Actor createActor(String type, XUIElement element) {
		XUICreator creator = creators.get(type);
		if (creator != null) {
			creator.element = element;
			replaceVariables(element);
			Actor result = creator.create(type);
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
