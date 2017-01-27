package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

public class ButtonCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        Button result = new Button();

        if (hasAttribute("skin")) {
            Skin skin = XUIAssetsAccess.getSkin(getAttribute("skin"));
            Button.ButtonStyle style = new Button.ButtonStyle(skin.get(Button.ButtonStyle.class));
            result = new Button(style);
        } else {
            result = new Button();
            result.setStyle(new Button.ButtonStyle());
        }

        result.setName("Button");
        return result;
    }
}
