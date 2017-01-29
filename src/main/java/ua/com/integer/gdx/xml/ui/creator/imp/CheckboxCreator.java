package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Creates new {@link CheckBox} with <b>CheckBox</b> name
 */
public class CheckboxCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        CheckBox result = null;
        CheckBox.CheckBoxStyle style = null;

        if (hasAttribute("skin")) {
            Skin skin = XUIAssetsAccess.getSkin(getAttribute("skin"));
            CheckBox.CheckBoxStyle originalStyle = skin.get(CheckBox.CheckBoxStyle.class);
            style = new CheckBox.CheckBoxStyle(originalStyle);
        } else {
            style = new CheckBox.CheckBoxStyle();
            style.checkboxOn = XUIAssetsAccess.getDrawableCopy(getAttribute("checkboxOn"));
            style.checkboxOff = XUIAssetsAccess.getDrawableCopy(getAttribute("checkboxOff"));
        }

        result = new CheckBox("", style);
        result.setName("CheckBox");
        return result;
    }
}
