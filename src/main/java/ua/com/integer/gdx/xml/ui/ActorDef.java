package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.creator.ActorCreator;
import ua.com.integer.gdx.xml.ui.setup.ActorSetup;

public class ActorDef {
	private String name;
	private ActorTransform transform = new ActorTransform();
	
	private Array<ActorDef> children = new Array<ActorDef>();
	
	private ObjectMap<String, String> attributes = new ObjectMap<>();

	private transient Actor result;
	
	public static ActorDef start(Class<? extends Actor> cl) {
		return ActorDef.start(cl.getName());
	}

    public void setName(String name) {
        this.name = name;
    }
	
	public static ActorDef start(String packageName) {
		ActorDef result = new ActorDef();
		result.name = packageName;
		return result;
	}
	
	public ActorDef copy() {
		ActorDef result = new ActorDef();
		result.name = name;
		result.transform = transform.copy();
        result.attributes.putAll(attributes);
		for(int i = 0; i < children.size; i++) {
			result.children.add(children.get(i).copy());
		}
		return result;
	}
	
	public ActorDef setVar(String name, String value) {
		attributes.put(name, value);
		return this;
	}

	public ActorDef addChild(ActorDef actorDefinition) {
		children.add(actorDefinition);
		return this;
	}
	
	public ActorTransform transform() {
		return transform;
	}
	
	public ActorDef transform(ActorTransform transform) {
		this.transform = transform;
		return this;
	}
	
	public void inflate(Stage stage) {
		inflate(this, stage);
	}
	
	public static void inflate(ActorDef def, Stage stage) {
		inflate(def, stage.getRoot());
	}
	
	public void inflate(Group parent) {
		inflate(this, parent);
	}

	public static void inflate(ActorDef def, Group parent) {
		def.result = ActorCreator.createActor(def.name);
		if (parent instanceof ScrollPane) {
			((ScrollPane) parent).setWidget(def.result);
		} else if (parent instanceof Window) {
			Window window = (Window) parent;
			window.add(def.result).expand().fill();
		} else {
			parent.addActor(def.result);
		}
		def.transform.target(def.result).apply();
        ActorSetup.setup(def);

		if(def.result instanceof Group) {
			Group g = (Group) def.result;
			for(ActorDef child : def.children) {
				inflate(child, g);
			}
		}
	}
	
	public void updateTransform() {
		transform().target(result).apply();
		
		if (result instanceof Widget) {
			((Widget) result).invalidate();
		}
		
		if (result instanceof WidgetGroup) {
			((WidgetGroup) result).invalidate();
		}
		
		for(int i = 0; i < children.size; i++) {
			children.get(i).updateTransform();
		}
	}
	
	@Override
	public String toString() {
		Json json = new Json(OutputType.json);
		return json.toJson(this);
	}

	public static ActorDef load(String xml) {
		XmlReader reader = new XmlReader();
		XmlReader.Element rootXml = reader.parse(xml);

        ActorDef rootDef = createDef(rootXml);
        load(rootDef, rootXml);

        return rootDef;
	}

    private static void load(ActorDef actorDef, XmlReader.Element xml) {
        int childCount = xml.getChildCount();
        for(int i = 0; i < childCount; i++) {
            XmlReader.Element childXml = xml.getChild(i);
            ActorDef child = createDef(childXml);
            actorDef.addChild(child);

            load(child, childXml);
        }
    }

    private static ActorDef createDef(XmlReader.Element xml) {
        ActorDef actorDef = new ActorDef();
        PropertiesExtractor.extractProperties(xml, actorDef);
        return actorDef;
    }

	public static ActorDef load(FileHandle file) {
		return load(file.readString());
	}
	
	public Array<ActorDef> getChildren() {
		return children;
	}
	
	public void searchRecursiveByTargetActor(Actor target, Array<ActorDef> output) {
		if (result == target) {
			output.add(this);
			return;
			
		} else {
			for(int i = 0; i < children.size; i++) {
				children.get(i).searchRecursiveByTargetActor(target, output);
			}
		}
	}
	
	public void searchRecursiveByActorName(String name, Array<ActorDef> output) {
		if (name.equals(result.getName())) {
			output.add(this);
			return;
			
		} else {
			for(int i = 0; i < children.size; i++) {
				children.get(i).searchRecursiveByActorName(name, output);
			}
		}
	}
	
	public void collectAllActorsRecursive(Array<Actor> output) {
		output.add(result);
		for(int i = 0; i < children.size; i++) {
			children.get(i).collectAllActorsRecursive(output);
		}
	}
	
	public Actor getActor() {
		return result;
	}
	
	public ActorDef clearChildren() {
		children.clear();
		return this;
	}
	
	public ObjectMap<String, String> getAttributes() {
		return attributes;
	}

    public String getName() {
        return name;
    }
}
