package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.eval.Eval;
import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class XUIElement {
	public String name;
	public Array<XUIElement> children = new Array<XUIElement>();
	public ObjectMap<String, String> attributes = new ObjectMap<>();

	private transient Actor result;

	public XUIElement copy() {
		XUIElement result = new XUIElement();
		result.name = name;
        result.attributes.putAll(attributes);
		for(int i = 0; i < children.size; i++) {
			result.children.add(children.get(i).copy());
		}
		return result;
	}

    public void copyTo(XUIElement another) {
        another.name = name;
        another.attributes.putAll(attributes);
        for(int i = 0; i < children.size; i++) {
            another.children.add(children.get(i).copy());
        }
    }
	
	public XUIElement setVar(String name, String value) {
		attributes.put(name, value);
		return this;
	}

	public XUIElement addChild(XUIElement actorDefinition) {
		children.add(actorDefinition);
		return this;
	}
	
	public void inflate(Stage stage) {
		inflate(this, stage);
	}
	
	public static void inflate(XUIElement def, Stage stage) {
		inflate(def, stage.getRoot());
	}
	
	public void inflate(Group parent) {
		inflate(this, parent);
	}

	public static void inflate(XUIElement def, Group parent) {
        if (def.name.equals("var")) {
            Eval.setVar(def.attributes.get("name"), def.attributes.get("value"));
            return;
        }

		def.result = XUICreator.createActor(def.name, def);
		if (parent instanceof ScrollPane) {
			((ScrollPane) parent).setWidget(def.result);
		} else if (parent instanceof Window) {
			Window window = (Window) parent;
			window.add(def.result).expand().fill();
		} else {
			parent.addActor(def.result);
		}

        XUIProcessor.setup(def);

		if(def.result instanceof Group) {
			Group g = (Group) def.result;
			for(XUIElement child : def.children) {
				inflate(child, g);
			}
		}
	}

	public static XUIElement load(String xml) {
		XmlReader reader = new XmlReader();
		XmlReader.Element rootXml = reader.parse(xml);

        XUIElement rootDef = createDef(rootXml);
        load(rootDef, rootXml);

        return rootDef;
	}

    private static void load(XUIElement XUIElement, XmlReader.Element xml) {
        int childCount = xml.getChildCount();
        for(int i = 0; i < childCount; i++) {
            XmlReader.Element childXml = xml.getChild(i);
            XUIElement child = createDef(childXml);
            XUIElement.addChild(child);

            load(child, childXml);
        }
    }

    private static XUIElement createDef(XmlReader.Element xml) {
        XUIElement XUIElement = new XUIElement();
        PropertiesExtractor.extractProperties(xml, XUIElement);
        return XUIElement;
    }

	public static XUIElement load(FileHandle file) {
		return load(file.readString());
	}
	
	public Actor getActor() {
		return result;
	}

}
