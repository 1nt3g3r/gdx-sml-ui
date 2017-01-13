package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.creator.XUICreator;

/**
 * Creates new {@link Label} with <b>Label</b> name
 */
public class LabelCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        String fontName = element.attributes.get("font");
        BitmapFont font = XUI.assets().getAsset(fontName, BitmapFont.class);
        Label result = new Label("", new Label.LabelStyle(font, Color.WHITE));
        result.setName("Label");
        return result;
    }
}
