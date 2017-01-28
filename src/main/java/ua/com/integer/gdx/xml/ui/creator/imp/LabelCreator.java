package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Creates new {@link Label} with <b>Label</b> name
 */
public class LabelCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        Label result = null;

        if (element.attributes.containsKey("skin")) {
            Label.LabelStyle labelStyle = new Label.LabelStyle(XUIAssetsAccess.getSkin(element.attributes.get("skin")).get(Label.LabelStyle.class));
            result = new Label("", labelStyle);
        } else {
            String fontName = element.attributes.get("font");
            BitmapFont font = XUI.assets().getAsset(fontName, BitmapFont.class);
            result = new Label("", new Label.LabelStyle(font, Color.WHITE));
            return result;
        }

        result.setName("Label");
        return result;
    }
}
