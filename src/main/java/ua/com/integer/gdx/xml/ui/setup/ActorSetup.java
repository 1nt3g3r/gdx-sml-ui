package ua.com.integer.gdx.xml.ui.setup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;

import ua.com.integer.gdx.xml.ui.ActorDef;
import ua.com.integer.gdx.xml.ui.res.Assets;
import ua.com.integer.gdx.xml.ui.setup.imp.common.CommonPropertiesSetup;
import ua.com.integer.gdx.xml.ui.setup.imp.GroupSetup;
import ua.com.integer.gdx.xml.ui.setup.imp.ImageSetup;
import ua.com.integer.gdx.xml.ui.setup.imp.common.VariableSetup;
import ua.com.integer.gdx.xml.ui.setup.imp.common.visual.VisualEffectsSetup;

public abstract class ActorSetup {
    private static ObjectMap<String, Array<ActorSetup>> setupMap = new ObjectMap<String, Array<ActorSetup>>();

    static {
        register("common", new VariableSetup());
        register("common", new CommonPropertiesSetup());
        register("common", new VisualEffectsSetup());

        register("image", new ImageSetup());
        register("group", new GroupSetup());
    }

    public static void register(String name, ActorSetup setup) {
        if (!setupMap.containsKey(name)) {
            setupMap.put(name, new Array<ActorSetup>());
        }
        setupMap.get(name).add(setup);
    }

    protected ActorDef actorDef;

    public ActorSetup actorDef(ActorDef actorDef) {
        this.actorDef = actorDef;
        return this;
    }

    public abstract void setup();

    protected Scaling getScaling(String name) {
        return Scaling.valueOf(getValue(name));
    }

    protected TextureRegion getRegion(String name) {
        return (TextureRegion) Assets.getInstance().getAsset(getValue(name), TextureRegion.class);
    }

    protected int getAlign(String name) {
        String[] parts = getValue(name).replace(" ", "").split("\\|");
        int result = 0;

        for(String part : parts) {
            if (part.equals("center")) {
                result |= Align.center;
            } else if (part.equals("top")) {
                result |= Align.top;
            } else if (part.equals("bottom")) {
                result |= Align.bottom;
            } else if (part.equals("left")) {
                result |= Align.left;
            } else if (part.equals("right")) {
                result |= Align.right;
            } else if (part.equals("topLeft")) {
                result |= Align.topLeft;
            } else if (part.equals("topRight")) {
                result |= Align.topRight;
            } else if (part.equals("bottomLeft")) {
                result |= Align.bottomLeft;
            } else if (part.equals("bottomRight")) {
                result |= Align.bottomRight;
            }
        }
        return result;
    }

    protected Color color(String name) {
        return Color.valueOf(getValue(name));
    }

    protected Touchable touchable(String name) {
        return Touchable.valueOf(getValue(name));
    }

    protected float getFloat(String name) {
        return Float.parseFloat(getValue(name));
    }

    protected  int getInt(String name) {
        return Integer.parseInt(getValue(name));
    }

    protected boolean bool(String name) {
        return Boolean.parseBoolean(getValue(name));
    }

    protected boolean hasValue(String name) {
        return actorDef.getAttributes().containsKey(name);
    }

    protected String getValue(String name) {
        return actorDef.getAttributes().get(name);
    }

    public static void setup(ActorDef actorDef) {
        apply("common", actorDef);
        apply(actorDef.getName(), actorDef);
    }

    private static void apply(String name, ActorDef def) {
        if (!setupMap.containsKey(name)) {
            return;
        }

        for(ActorSetup setup : setupMap.get(name)) {
            setup.actorDef(def).setup();
        }
    }
}
