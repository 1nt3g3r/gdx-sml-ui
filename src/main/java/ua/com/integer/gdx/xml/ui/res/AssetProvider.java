package ua.com.integer.gdx.xml.ui.res;

/**
 * Provides access to asset by his name
 */
public interface AssetProvider {
    /**
     * Returns asset by his name
     */
    Object getAsset(String name);
}
