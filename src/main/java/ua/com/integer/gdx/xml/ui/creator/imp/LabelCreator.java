package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ua.com.integer.gdx.xml.ui.creator.ActorCreator;
import ua.com.integer.gdx.xml.ui.res.Assets;

public class LabelCreator extends ActorCreator {
    @Override
    protected Actor create(String packageName) {
        String fontName = actorDef.getAttributes().get("font");
        BitmapFont font = Assets.getInstance().getAsset(fontName, BitmapFont.class);
        return new Label("", new Label.LabelStyle(font, Color.WHITE));
    }
}
