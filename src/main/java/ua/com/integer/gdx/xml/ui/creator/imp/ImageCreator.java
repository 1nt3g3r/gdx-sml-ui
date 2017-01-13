package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;

/**
 * Creates new {@link Image} with <b>Image</b> name
 */
public class ImageCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        Image result = new Image();
        result.setName("Image");
        return result;
    }
}
