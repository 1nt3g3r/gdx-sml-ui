package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.XUIVariables;
import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class XUI {
    private static final XUI instance = new XUI();

    private String workingDirectory = "xui";
    private ObjectMap<String, XUIElement> defs = new ObjectMap<>();

    private XUIVariables variables = new XUIVariables();

    private XUIAssets assets;

    public static XUI getInstance() {
        return instance;
    }

    private XUI() {
    }

    public static void setWorkingDirectory(String workingDirectory) {
        getInstance().workingDirectory = workingDirectory;
    }

    public static String getWorkingDirectory() {
        return getInstance().workingDirectory;
    }

    public static XUIElement get(String name) {
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
        XUIElement def = XUIElement.load(Gdx.files.internal(workingDirectory + "/" + name + ".xml"));
        unwrapLinkedActors(def);
        applyDeepProperties(def);
        defs.put(name, def);
    }

    private void unwrapLinkedActors(XUIElement def) {
        if (def.name.equals("linkedActor")) {
            XUIElement linked = get(def.attributes.get("path"));
            def.attributes.remove("path");

            ObjectMap<String, String> originalAttributes = new ObjectMap<>(def.attributes);

            def.name = linked.name;
            def.attributes.putAll(linked.attributes);
            def.attributes.putAll(originalAttributes);

            def.children.addAll(linked.children);
        }

        for(XUIElement child: def.children) {
            unwrapLinkedActors(child);
        }
    }

    private void applyDeepProperties(XUIElement def) {
        for(String attributeName: def.attributes.keys()) {
            if (attributeName.contains(".")) {
                def.setAttribute(attributeName, def.attributes.get(attributeName));
            }
        }

        for(XUIElement child : def.children) {
            applyDeepProperties(child);
        }
    }

    public static final XUIAssets getAssets() {
        return getInstance().assets;
    }

    public static void registerXUICreator(String name, XUICreator creator) {
        XUICreator.register(name, creator);
    }

    public static void registerXUIProcessor(String name, XUIProcessor processor) {
        XUIProcessor.registerProcessor(name, processor);
    }

    public static void init() {
        variables().clear();
        XUICreator.init();
        XUIProcessor.init();

        getInstance().assets = new XUIAssets();
        getInstance().defs.clear();
    }

    public static XUIVariables variables() {
        return getInstance().variables;
    }
}
