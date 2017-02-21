package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

public class TouchpadCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        Touchpad result;
        float deadzoneRadius = 10f;
        if (hasAttribute("skin")) {
            Skin skin = XUIAssetsAccess.getSkin(getAttribute("skin"));
            result = new Touchpad(deadzoneRadius, skin);
        } else {
            Touchpad.TouchpadStyle style = new Touchpad.TouchpadStyle();
            result = new Touchpad(deadzoneRadius, style);
        }

        result.setName("Touchpad");
        return result;
    }
}
