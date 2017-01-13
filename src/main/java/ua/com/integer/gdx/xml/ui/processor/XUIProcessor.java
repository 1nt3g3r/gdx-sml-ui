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
import ua.com.integer.gdx.xml.ui.processor.imp.GroupProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.ImageProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.LabelProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.CommonPropertiesProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.LocalizeProcessor;
import ua.com.integer.gdx.xml.ui.processor.imp.common.TransformProcessor;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;
import ua.com.integer.gdx.xml.ui.util.ActorMathEval;

/**
 * Process created actor to setup it (like set {@link TextureRegion} for {@link com.badlogic.gdx.scenes.scene2d.ui.Image})
 */
public abstract class XUIProcessor {
    private static ObjectMap<String, Array<XUIProcessor>> processors = new ObjectMap<String, Array<XUIProcessor>>();

    /**
     * Clear all registered processors and register defaults processors.
     */
    public static void init() {
        processors.clear();
        registerDefaultProcessors();
    }

    private static void registerDefaultProcessors() {
        registerProcessors("common", new LocalizeProcessor(), new TransformProcessor(), new CommonPropertiesProcessor());

        registerProcessors("image", new ImageProcessor());
        registerProcessors("group", new GroupProcessor());
        registerProcessors("label", new LabelProcessor());
    }

    /**
     * Register processors for given actor type
     */
    public static void registerProcessors(String type, XUIProcessor ... processorImps) {
        if (!processors.containsKey(type)) {
            processors.put(type, new Array<XUIProcessor>());
        }

        for(XUIProcessor processor : processorImps) {
            processors.get(type).add(processor);
        }
    }

    /**
     * XMLElement with created actor
     */
    protected XUIElement element;

    /**
     * Set XMLElement for setup. Actor for setup is here - {@link XUIElement#resultActor}
     * @param element
     * @return
     */
    public XUIProcessor setElement(XUIElement element) {
        this.element = element;
        return this;
    }

    /**
     * Implementation of processing
     */
    public abstract void process();

    /**
     * Convenient method to get {@link Sound} by attributeName
     */
    public Sound getSound(String name) {
        return XUIAssetsAccess.getSound(getAttribute(name));
    }

    /**
     * Convenient method to get {@link Scaling} by attributeName
     */
    public Scaling getScaling(String name) {
        return Scaling.valueOf(getAttribute(name));
    }

    /**
     * Convenient method to get {@link Texture} by attributeName
     */
    public Texture getTexture(String name) {
        return XUIAssetsAccess.getTexture(getAttribute(name));
    }

    /**
     * Convenient method to get {@link TextureRegion} by attributeName
     */
    public TextureRegion getRegion(String name) {
        return XUIAssetsAccess.getTextureRegion(getAttribute(name));
    }

    /**
     * Convenient method to get align by attributeName. Align can be combined via <b>|</b> (i.e. "top|left" is mean top and left)
     *
     * @see Align
     */
    public int getAlign(String name) {
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

    /**
     * Convenient method to get {@link Color} by attributeName
     */
    public Color getColor(String name) {
        String colorName = getAttribute(name);
        Color color = XUIAssetsAccess.getColor(colorName);
        if (color == null) {
            return Color.valueOf(colorName);
        } else {
            return color;
        }
    }

    /**
     * Convenient method to get {@link Touchable} by attributeName
     */
    public Touchable touchable(String name) {
        return Touchable.valueOf(getAttribute(name));
    }

    /**
     * Convenient method to get value of attributeName as float evaluation
     */
    public float eval(String name) {
        return ActorMathEval.eval(element.resultActor, getAttribute(name));
    }

    /**
     * Convenient method to get value of attributeName as float
     */
    public float getFloat(String name) {
        return Float.parseFloat(getAttribute(name));
    }

    /**
     * Convenient method to get value of attributeName as int
     */
    public int getInt(String name) {
        return Integer.parseInt(getAttribute(name));
    }

    /**
     * Convenient method to get value of attributeName as bool
     */
    public boolean bool(String name) {
        return Boolean.parseBoolean(getAttribute(name));
    }

    /**
     * Is attribute with given name present
     */
    public boolean hasAttribute(String attributeName) {
        return element.attributes.containsKey(attributeName);
    }

    /**
     * Return string value of attribute
     */
    public String getAttribute(String attributeName) {
        return element.attributes.get(attributeName);
    }

    /**
     * Apply common processors for given element.
     *
     * If any type-specific processors are present apply them also
     */
    public static void process(XUIElement element) {
        apply("common", element);
        apply(element.type, element);
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
