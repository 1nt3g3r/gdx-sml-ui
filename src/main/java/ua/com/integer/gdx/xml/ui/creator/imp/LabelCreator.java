package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.creator.XUICreator;

public class LabelCreator extends XUICreator {
    @Override
    protected Actor create(String packageName) {
        String fontName = element.attributes.get("font");
        BitmapFont font = XUI.getAssets().getAsset(fontName, BitmapFont.class);
        return new Label("", new Label.LabelStyle(font, Color.WHITE));
    }
}
