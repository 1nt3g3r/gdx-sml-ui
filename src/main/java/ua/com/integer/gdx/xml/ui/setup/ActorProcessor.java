package ua.com.integer.gdx.xml.ui.setup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;

import ua.com.integer.gdx.xml.ui.ActorDef;
import ua.com.integer.gdx.xml.ui.eval.Eval;
import ua.com.integer.gdx.xml.ui.res.Assets;
import ua.com.integer.gdx.xml.ui.setup.imp.LabelProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.CommonPropertiesProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.GroupProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.ImageProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.VariableSetup;
import ua.com.integer.gdx.xml.ui.setup.imp.common.visual.VisualEffectsSetup;

public abstract class ActorProcessor {
    private static ObjectMap<String, Array<ActorProcessor>> setupMap = new ObjectMap<String, Array<ActorProcessor>>();

    public static ObjectMap<String, Color> predefinedColors = new ObjectMap<>();

    static {
        registerActorProcessors();
        registerColors();
    }

    private static void registerActorProcessors() {
        registerActorProcessor("common", new VariableSetup());
        registerActorProcessor("common", new CommonPropertiesProcessor());
        registerActorProcessor("common", new VisualEffectsSetup());

        registerActorProcessor("image", new ImageProcessor());
        registerActorProcessor("group", new GroupProcessor());
        registerActorProcessor("label", new LabelProcessor());
    }

    private static void registerColors() {
        registerColor("CLEAR", Color.CLEAR);
        registerColor("BLACK", Color.BLACK);
        registerColor("WHITE", Color.WHITE);
        registerColor("LIGHT_GRAY", Color.LIGHT_GRAY);
        registerColor("GRAY", Color.GRAY);
        registerColor("DARK_GRAY", Color.DARK_GRAY);
        registerColor("BLUE", Color.BLUE);
        registerColor("NAVY", Color.NAVY);
        registerColor("ROYAL", Color.ROYAL);
        registerColor("SLATE", Color.SLATE);
        registerColor("SKY", Color.SKY);
        registerColor("CYAN", Color.CYAN);
        registerColor("TEAL", Color.TEAL);
        registerColor("GREEN", Color.GREEN);
        registerColor("CHARTREUSE", Color.CHARTREUSE);
        registerColor("LIME", Color.LIME);
        registerColor("FOREST", Color.FOREST);
        registerColor("OLIVE", Color.OLIVE);
        registerColor("YELLOW", Color.YELLOW);
        registerColor("GOLD", Color.GOLD);
        registerColor("GOLDENROD", Color.GOLDENROD);
        registerColor("ORANGE", Color.ORANGE);
        registerColor("BROWN", Color.BROWN);
        registerColor("TAN", Color.TAN);
        registerColor("FIREBRICK", Color.FIREBRICK);
        registerColor("RED", Color.RED);
        registerColor("SCARLET", Color.SCARLET);
        registerColor("CORAL", Color.CORAL);
        registerColor("SALMON", Color.SALMON);
        registerColor("PINK", Color.PINK);
        registerColor("MAGENTA", Color.MAGENTA);
        registerColor("PURPLE", Color.PURPLE);
        registerColor("VIOLET", Color.VIOLET);
        registerColor("MAROON", Color.MAROON);
    }

    public static void registerColor(String name, Color value) {
        predefinedColors.put(name, value);
    }

    public static void registerActorProcessor(String name, ActorProcessor setup) {
        if (!setupMap.containsKey(name)) {
            setupMap.put(name, new Array<ActorProcessor>());
        }
        setupMap.get(name).add(setup);
    }

    protected ActorDef actorDef;

    public ActorProcessor actorDef(ActorDef actorDef) {
        this.actorDef = actorDef;
        return this;
    }

    public abstract void setup();

    protected Scaling getScaling(String name) {
        return Scaling.valueOf(getValue(name));
    }

    protected TextureRegion getRegion(String name) {
        return Assets.getInstance().getAsset(getValue(name), TextureRegion.class);
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

    protected Color getColor(String name) {
        String colorName = getValue(name);
        if (predefinedColors.containsKey(colorName)) {
            return predefinedColors.get(colorName);
        }
        return Color.valueOf(colorName);
    }

    protected Touchable touchable(String name) {
        return Touchable.valueOf(getValue(name));
    }

    protected float eval(String name) {
        return Eval.eval(actorDef.getActor(), getValue(name));
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

        for(ActorProcessor setup : setupMap.get(name)) {
            setup.actorDef(def).setup();
        }
    }
}
