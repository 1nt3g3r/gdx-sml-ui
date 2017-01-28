package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Creates new {@link ScrollPane} with <b>ScrollPane</b> name
 */
public class ScrollPaneCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        ScrollPane result = null;

        if (hasAttribute("skin")) {
            Skin skin = XUIAssetsAccess.getSkin(getAttribute("skin"));
            ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle(skin.get(ScrollPane.ScrollPaneStyle.class));
            result = new ScrollPane(null, style);
        } else {
            result = new ScrollPane(null);
        }

        result.setName("ScrollPane");
        return result;
    }
}
