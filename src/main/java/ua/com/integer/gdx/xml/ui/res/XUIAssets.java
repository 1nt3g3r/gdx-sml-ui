package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIButtonGroupParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIColorParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIDrawableParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIVariableParser;

/**
 * This class holds assets needed for create/setup actors from {@link ua.com.integer.gdx.xml.ui.element.XUIElement}.
 * For example, if we setup {@link com.badlogic.gdx.scenes.scene2d.ui.Image} we need some {@link com.badlogic.gdx.graphics.g2d.TextureRegion} or
 * {@link com.badlogic.gdx.scenes.scene2d.utils.Drawable}. So this class provide it
 *
 * This class is just proxy that called {@link AssetProvider} to get some resources. It mean you should implement your own set of
 * {@link AssetProvider} and pass it there.
 *
 * Also this class can parse user-predefined assets (like Colors, Variables, NinePatch Drawables) from external files. Look more for {@link XUIAssetParser}
 */
public class XUIAssets {
    private ObjectMap<Class, AssetProvider> assetProviders = new ObjectMap<>();
    private ObjectMap<Object, ObjectMap<String, Object>> resources = new ObjectMap<>();

    private ObjectMap<String, XUIAssetParser> assetParsers = new ObjectMap<>();

    /**
     * Initialize class and loads default set of {@link XUIAssetParser}
     */
    public XUIAssets() {
        registerDefaultAssetParsers();
    }

    /**
     * Search and load for file <b>assets.xml</b> in internal {@link XUI#getWorkingDirectory()}
     */
    public void loadDefaultAssets() {
        String path = XUI.getWorkingDirectory() + "/assets.xml";
        FileHandle assetFileHandle = Gdx.files.internal(path);
        if (assetFileHandle.exists()) {
            parseAssets(assetFileHandle);
        }
    }

    private void registerDefaultAssetParsers() {
        registerAssetParser("color", new XUIColorParser(this));
        registerAssetParser("drawable", new XUIDrawableParser());
        registerAssetParser("var", new XUIVariableParser());
        registerAssetParser("buttonGroup", new XUIButtonGroupParser());
    }

    /**
     * Register asset parser for given asset type
     */
    public void registerAssetParser(String assetType, XUIAssetParser parser) {
        assetParsers.put(assetType, parser);
    }

    /**
     * Parse assets from given XML {@link FileHandle}
     */
    public void parseAssets(FileHandle fileHandle) {
        parseAssets(fileHandle.readString("UTF-8"));
    }

    /**
     * Parse assets from given XML string
     */
    public void parseAssets(String xmlAssets) {
        XmlReader.Element root = new XmlReader().parse(xmlAssets);
        for(int i = 0; i < root.getChildCount(); i++) {
            parseAsset(root.getChild(i));
        }
    }

    private void parseAsset(XmlReader.Element assetElement) {
        String assetType = assetElement.getName();
        XUIAssetParser parser = assetParsers.get(assetType);
        if (parser != null) {
            parser.parse(assetElement, this);
        }
    }

    /**
     * Add asset with given name.
     *
     * For example, if you want add some {@link com.badlogic.gdx.graphics.Texture} with name <b>texture</b> you should call it in next way:
     * putAsset("texture", yourTexture, Texture.class)
     * @param name name of asset
     * @param asset asset
     * @param cl class of asset
     */
    public void putAsset(String name, Object asset, Class cl) {
        getResourceMap(cl).put(name, asset);
    }

    private ObjectMap<String, Object> getResourceMap(Class resourceClass) {
        if (!resources.containsKey(resourceClass)) {
            resources.put(resourceClass, new ObjectMap<String, Object>());
        }
        return resources.get(resourceClass);
    }

    /**
     * Returns asset by his name and type
     *
     * @param name assetName
     * @param assetClassType assetType
     */
    public <T extends Object> T getAsset(String name, Class assetClassType) {
        ObjectMap<String, Object> resMap = getResourceMap(assetClassType);
        if (resMap.containsKey(name)) {
            return (T) resMap.get(name);
        }
        if (!assetProviders.containsKey(assetClassType)) {
            return null;
        }
        return (T) assetProviders.get(assetClassType).getAsset(name);
    }

    /**
     * Registers {@link AssetProvider} for given asset type
     * @param assetType type of asset (examples - Texture, TextureRegion, BitmapFont)
     * @param assetProvider implementation of {@link AssetProvider} that can return asset of needed type by his name
     */
    public void registerAssetProvider(Class assetType, AssetProvider assetProvider) {
        assetProviders.put(assetType, assetProvider);
    }
}
