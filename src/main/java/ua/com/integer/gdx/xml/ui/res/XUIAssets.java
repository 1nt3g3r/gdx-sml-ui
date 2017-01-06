package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIColorParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIDrawableParser;
import ua.com.integer.gdx.xml.ui.res.parser.imp.XUIVariableParser;

public class XUIAssets {
    private ObjectMap<Class, AssetProvider> assetProviders = new ObjectMap<>();
    private ObjectMap<Object, ObjectMap<String, Object>> resources = new ObjectMap<>();

    private ObjectMap<String, XUIAssetParser> assetParsers = new ObjectMap<>();

    public XUIAssets() {
        registerDefaultAssetParsers();
    }

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
    }

    public void registerAssetParser(String name, XUIAssetParser parser) {
        assetParsers.put(name, parser);
    }

    public void parseAssets(FileHandle fileHandle) {
        parseAssets(fileHandle.readString("UTF-8"));
    }

    public void parseAssets(String xmlAssets) {
        XmlReader.Element root = new XmlReader().parse(xmlAssets);
        for(int i = 0; i < root.getChildCount(); i++) {
            parseAsset(root.getChild(i));
        }
    }

    private void parseAsset(XmlReader.Element assetElement) {
        XUIAssetParser parser = assetParsers.get(assetElement.getName());
        if (parser != null) {
            parser.parse(assetElement, this);
        }
    }

    public void putAsset(String name, Object asset, Class cl) {
        getResourceMap(cl).put(name, asset);
    }

    private ObjectMap<String, Object> getResourceMap(Class resourceClass) {
        if (!resources.containsKey(resourceClass)) {
            resources.put(resourceClass, new ObjectMap<String, Object>());
        }
        return resources.get(resourceClass);
    }

    public <T extends Object> T getAsset(String name, Class assetClass) {
        ObjectMap<String, Object> resMap = getResourceMap(assetClass);
        if (resMap.containsKey(name)) {
            return (T) resMap.get(name);
        }
        if (!assetProviders.containsKey(assetClass)) {
            return null;
        }
        return (T) assetProviders.get(assetClass).getAsset(name);
    }

    public void registerAssetProvider(Class cl, AssetProvider assetProvider) {
        assetProviders.put(cl, assetProvider);
    }
}
