package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Creates new {@link TextButton} with <b>TextButton</b> name
 */
public class TextButtonCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        TextButton textButton = null;

        if (hasAttribute("skin")) {
            Skin skin = XUIAssetsAccess.getSkin(getAttribute("skin"));
            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
            textButton = new TextButton("Text Button", style);
        } else {
            BitmapFont font = XUIAssetsAccess.getFont(getAttribute("font"));
            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
            style.font = font;
            textButton = new TextButton("Text Button", style);
        }

        textButton.setName("TextButton");
        return textButton;
    }
}
