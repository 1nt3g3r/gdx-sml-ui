package ua.com.integer.gdx.xml.ui.setup;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;

import ua.com.integer.gdx.xml.ui.XUIElement;
import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.eval.Eval;
import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.setup.imp.LabelProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.CommonPropertiesProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.GroupProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.ImageProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.LocalizeProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.TransformProcessor;
import ua.com.integer.gdx.xml.ui.setup.imp.common.effect.EffectsProcessor;

public abstract class XUIProcessor {
    private static ObjectMap<String, Array<XUIProcessor>> processors = new ObjectMap<String, Array<XUIProcessor>>();

    public static void init() {
        processors.clear();
        registerDefaultProcessors();
    }

    private static void registerDefaultProcessors() {
        registerProcessor("common", new LocalizeProcessor());
        registerProcessor("common", new TransformProcessor());
        registerProcessor("common", new CommonPropertiesProcessor());
        registerProcessor("common", new EffectsProcessor());

        registerProcessor("image", new ImageProcessor());
        registerProcessor("group", new GroupProcessor());
        registerProcessor("label", new LabelProcessor());
    }

    public static void registerProcessor(String name, XUIProcessor processor) {
        if (!processors.containsKey(name)) {
            processors.put(name, new Array<XUIProcessor>());
        }
        processors.get(name).add(processor);
    }

    protected XUIElement XUIElement;

    public XUIProcessor setXUIElement(XUIElement XUIElement) {
        this.XUIElement = XUIElement;
        return this;
    }

    public abstract void setup();

    protected Sound getSound(String name) {
        return XUI.getAssets().getAsset(getValue(name), Sound.class);
    }

    protected Scaling getScaling(String name) {
        return Scaling.valueOf(getValue(name));
    }

    protected Texture getTexture(String name) {
        return XUI.getAssets().getAsset(getValue(name), Texture.class);
    }

    protected TextureRegion getRegion(String name) {
        return XUI.getAssets().getAsset(getValue(name), TextureRegion.class);
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
        XUIAssets assets = XUI.getAssets();
        Color color = assets.getAsset(colorName, Color.class);
        if (color == null) {
            return Color.valueOf(colorName);
        } else {
            return color;
        }
    }

    protected Touchable touchable(String name) {
        return Touchable.valueOf(getValue(name));
    }

    protected float eval(String name) {
        return Eval.eval(XUIElement.getActor(), getValue(name));
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
        return XUIElement.attributes.containsKey(name);
    }

    protected String getValue(String name) {
        return XUIElement.attributes.get(name);
    }

    public static void setup(XUIElement XUIElement) {
        apply("common", XUIElement);
        apply(XUIElement.name, XUIElement);
    }

    private static void apply(String name, XUIElement xuiElement) {
        if (!processors.containsKey(name)) {
            return;
        }

        for(XUIProcessor processor : processors.get(name)) {
            processor.setXUIElement(xuiElement).setup();
        }
    }
}
