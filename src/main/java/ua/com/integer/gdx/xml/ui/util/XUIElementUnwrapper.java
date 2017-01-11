package ua.com.integer.gdx.xml.ui.util;

import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.element.XUIElement;

/**
 * Element can be complex thing that includes links to another elements (defined in another files).
 * We should "unwrap" it before we can use it
 */
public class XUIElementUnwrapper {
    public static final String LINKED_ACTOR_TYPE = "linkedActor";

    public static void process(XUIElement element) {
        unwrapLinkedActors(element);
        applyDeepAttributes(element);
    }

    private static void unwrapLinkedActors(XUIElement def) {
        if (def.type.equals(LINKED_ACTOR_TYPE)) {
            XUIElement linked = XUI.get(def.attributes.get("path"));
            def.attributes.remove("path");

            ObjectMap<String, String> originalAttributes = new ObjectMap<>(def.attributes);

            def.type = linked.type;
            def.attributes.putAll(linked.attributes);
            def.attributes.putAll(originalAttributes);

            def.children.addAll(linked.children);
        }

        for (XUIElement child : def.children) {
            unwrapLinkedActors(child);
        }
    }

    private static void applyDeepAttributes(XUIElement element) {
        for (String attributeName : element.attributes.keys()) {
            if (attributeName.contains(".")) {
                applyProperty(element, attributeName);
            }
        }

        for (XUIElement child : element.children) {
            applyDeepAttributes(child);
        }
    }

    private static void applyProperty(XUIElement element, String name) {
        String value = element.attributes.get(name);
        String[] nameParts = name.split("\\.");

        XUIElement current = element;
        for (int i = 0; i < nameParts.length - 1; i++) {
            String childName = nameParts[i];
            current = current.findChildByName(childName);
        }

        current.attributes.put(nameParts[nameParts.length - 1], value);
    }
}
