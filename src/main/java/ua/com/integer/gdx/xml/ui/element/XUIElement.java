package ua.com.integer.gdx.xml.ui.element;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * This class is definition of actor (it's type and properties).
 *
 * You can
 */
public class XUIElement {
    /**
     * Type of actor. For example, type "actor" is simple Actor, type "image" is Image
     */
	public String type;
    /**
     * Children of this element. Each child can have more children.
     */
	public Array<XUIElement> children = new Array<>();
    /**
     * Attributes (properties) of this element. These attributes are used to setup actor propertis after actor creation.
     */
	public ObjectMap<String, String> attributes = new ObjectMap<>();
    /**
     * Result of inflating this {@link XUIElement} into group
     */
	public Actor resultActor;

    /**
     * Recursively makes copy of this element
     */
	public XUIElement copy() {
		XUIElement result = new XUIElement();
		result.type = type;
        result.attributes.putAll(attributes);
		for(int i = 0; i < children.size; i++) {
			result.children.add(children.get(i).copy());
		}
		return result;
	}

    /**
     * Returns the first child found with specified name
     */
    public XUIElement findChildByName(String name) {
        for(XUIElement child : children) {
            if (child.attributes.get("name", "").equals(name)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Returns a description of the element hierarchy, recursively.
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder(128);
        toString(buffer, 1);
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    void toString(StringBuilder buffer, int indent) {
        StringBuilder descriptionStringBuffer = new StringBuilder();
        descriptionStringBuffer.append("TYPE: ").append(type).append(", ATTRIBUTES: ").append(attributes);
        buffer.append(descriptionStringBuffer.toString());
        buffer.append('\n');

        for (int i = 0, n = children.size; i < n; i++) {
            for (int ii = 0; ii < indent; ii++)
                buffer.append("|  ");
            XUIElement actor = children.get(i);
            if (actor.children.size > 0)
                actor.toString(buffer, indent + 1);
            else {
                buffer.append(actor);
                buffer.append('\n');
            }
        }
    }
}