package ua.com.integer.gdx.xml.ui.processor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;

import ua.com.integer.gdx.xml.ui.element.XUIElement;
import ua.com.integer.gdx.xml.ui.util.ActorMathEval;
import ua.com.integer.gdx.xml.ui.processor.imp.GroupProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.ImageProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.CommonPropertiesProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.LocalizeProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.TransformProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.effect.EffectsProcessor;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;
import ua.com.integer.gdx.xml.ui.processor.imp.LabelProcessor;

public abstract class XUIProcessor {
    private static ObjectMap<String, Array<XUIProcessor>> processors = new ObjectMap<String, Array<XUIProcessor>>();

    public static void init() {
        processors.clear();
        registerDefaultProcessors();
    }

    private static void registerDefaultProcessors() {
        registerProcessors("common", new LocalizeProcessor(), new TransformProcessor(), new CommonPropertiesProcessor(), new EffectsProcessor());

        registerProcessors("image", new ImageProcessor());
        registerProcessors("group", new GroupProcessor());
        registerProcessors("label", new LabelProcessor());
    }

    public static void registerProcessors(String name, XUIProcessor ... processorImps) {
        if (!processors.containsKey(name)) {
            processors.put(name, new Array<XUIProcessor>());
        }

        for(XUIProcessor processor : processorImps) {
            processors.get(name).add(processor);
        }
    }

    protected XUIElement element;

    public XUIProcessor setElement(XUIElement element) {
        this.element = element;
        return this;
    }

    public abstract void process();

    protected Sound getSound(String name) {
        return XUIAssetsAccess.getSound(getAttribute(name));
    }

    protected Scaling getScaling(String name) {
        return Scaling.valueOf(getAttribute(name));
    }

    protected Texture getTexture(String name) {
        return XUIAssetsAccess.getTexture(getAttribute(name));
    }

    protected TextureRegion getRegion(String name) {
        return XUIAssetsAccess.getTextureRegion(getAttribute(name));
    }

    protected int getAlign(String name) {
        String[] parts = getAttribute(name).replace(" ", "").split("\\|");
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
        String colorName = getAttribute(name);
        Color color = XUIAssetsAccess.getColor(colorName);
        if (color == null) {
            return Color.valueOf(colorName);
        } else {
            return color;
        }
    }

    protected Touchable touchable(String name) {
        return Touchable.valueOf(getAttribute(name));
    }

    protected float eval(String name) {
        return ActorMathEval.eval(element.resultActor, getAttribute(name));
    }

    protected float getFloat(String name) {
        return Float.parseFloat(getAttribute(name));
    }

    protected  int getInt(String name) {
        return Integer.parseInt(getAttribute(name));
    }

    protected boolean bool(String name) {
        return Boolean.parseBoolean(getAttribute(name));
    }

    protected boolean hasValue(String name) {
        return element.attributes.containsKey(name);
    }

    protected String getAttribute(String name) {
        return element.attributes.get(name);
    }

    public static void process(XUIElement XUIElement) {
        apply("common", XUIElement);
        apply(XUIElement.type, XUIElement);
    }

    private static void apply(String name, XUIElement xuiElement) {
        if (!processors.containsKey(name)) {
            return;
        }

        for(XUIProcessor processor : processors.get(name)) {
            processor.setElement(xuiElement).process();
        }
    }
}
