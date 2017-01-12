package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.I18NBundle;

import ua.com.integer.gdx.xml.ui.XUI;

/**
 * Class for convenient access to most used assets (like Textures, Fonts, etc)
 */
public class XUIAssetsAccess {
    /**
     * Return texture by name
     */
    public static Texture getTexture(String textureName) {
        return XUI.assets().getAsset(textureName, Texture.class);
    }

    /**
     * Return texture atlas by name
     */
    public static TextureAtlas getAtlas(String atlasName) {
        return XUI.assets().getAsset(atlasName, TextureAtlas.class);
    }

    /**
     * Returns texture region from {@link TextureAtlas}.
     * Atlas name and region name should be separated "->". For example,
     * if we have atlas "atlas" and region "region", full name will be
     * "atlas->region"
     */
    public static TextureRegion getTextureRegion(String atlasAndRegionName) {
        String[] parts = atlasAndRegionName.split("->");
        String atlasName = parts[0];
        String regionName = parts[1];

        return getAtlas(atlasName).findRegion(regionName);
    }

    /**
     * Return bitmap font by name
     */
    public static BitmapFont getFont(String fontName) {
        return XUI.assets().getAsset(fontName, BitmapFont.class);
    }

    /**
     * Return music by name
     */
    public static Music getMusic(String musicName) {
        return XUI.assets().getAsset(musicName, Music.class);
    }

    /**
     * Return sound by name
     */
    public static Sound getSound(String soundName) {
        return XUI.assets().getAsset(soundName, Sound.class);
    }

    /**
     * Return user-defined String variable by name
     */
    public static String getVariable(String variableName) {
        return XUI.assets().getAsset(variableName, String.class);
    }

    /**
     * Return Localization I18NBundle by name
     */
    public static I18NBundle getI18NBundle(String bundleName) {
        return XUI.assets().getAsset(bundleName, I18NBundle.class);
    }

    /**
     * Return {@link Color} by name. Color can be predefined (like RED) or can be set by user
     */
    public static Color getColor(String colorName) {
        return XUI.assets().getAsset(colorName, Color.class);
    }
}
