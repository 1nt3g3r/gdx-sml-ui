package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
    private ObjectMap<Class, AssetProvider> assetProviders = new ObjectMap<>();
    
    private static final Assets instance = new Assets();

    public static Assets getInstance() {
        return instance;
    }

    private Assets() {
    }

    public <T extends Object> T getAsset(String name, Class assetClass) {
        return (T) assetProviders.get(assetClass).getAsset(name);
    }

    public void registerAssetProvider(Class cl, AssetProvider assetProvider) {
        assetProviders.put(cl, assetProvider);
    }

    public void registerAssetProviders(ObjectMap<Class, AssetProvider> assetProviders) {
        assetProviders.putAll(assetProviders);
    }
}
