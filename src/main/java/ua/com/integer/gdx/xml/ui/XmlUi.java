package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;

public class XmlUi {
    private String searchDirectory = "xml-ui";

    private ObjectMap<String, ActorDef> defs = new ObjectMap<>();
    
    private static final XmlUi instance = new XmlUi();

    public static XmlUi getInstance() {
        return instance;
    }

    private XmlUi() {
    }

    public static void setSearchDirectory(String searchDirectory) {
        getInstance().searchDirectory = searchDirectory;
    }

    public static ActorDef get(String name) {
        if (!getInstance().defs.containsKey(name)) {
            getInstance().loadDef(name);
        }

        return getInstance().defs.get(name).copy();
    }

    public static void inflate(String name, Group group) {
        getInstance().get(name).inflate(group);
    }

    public static void inflate(String name, Stage stage) {
        getInstance().get(name).inflate(stage);
    }

    private void loadDef(String name) {
        ActorDef def = ActorDef.load(Gdx.files.internal(searchDirectory + "/" + name + ".xml"));
        defs.put(name, def);
    }

    public static void reset() {
        getInstance().defs.clear();
    }
}
