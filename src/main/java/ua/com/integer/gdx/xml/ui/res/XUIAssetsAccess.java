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

public class XUIAssetsAccess {
    public static Texture getTexture(String textureName) {
        return XUI.assets().getAsset(textureName, Texture.class);
    }

    public static TextureAtlas getAtlas(String atlasName) {
        return XUI.assets().getAsset(atlasName, TextureAtlas.class);
    }

    public static TextureRegion getTextureRegion(String atlasAndRegionName) {
        String[] parts = atlasAndRegionName.split("->");
        String atlasName = parts[0];
        String regionName = parts[1];

        return getAtlas(atlasName).findRegion(regionName);
    }

    public static BitmapFont getFont(String fontName) {
        return XUI.assets().getAsset(fontName, BitmapFont.class);
    }

    public static Music getMusic(String musicName) {
        return XUI.assets().getAsset(musicName, Music.class);
    }

    public static Sound getSound(String soundName) {
        return XUI.assets().getAsset(soundName, Sound.class);
    }

    public static String getVariable(String variableName) {
        return XUI.assets().getAsset(variableName, String.class);
    }

    public static I18NBundle getI18NBundle(String bundleName) {
        return XUI.assets().getAsset(bundleName, I18NBundle.class);
    }

    public static Color getColor(String colorName) {
        return XUI.assets().getAsset(colorName, Color.class);
    }
}
