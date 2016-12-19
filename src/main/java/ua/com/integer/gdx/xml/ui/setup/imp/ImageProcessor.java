package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class ImageProcessor extends XUIProcessor {
    @Override
    public void setup() {
        Image image = (Image) XUIElement.getActor();

        if (hasValue("region")) {
            image.setDrawable(new TextureRegionDrawable(getRegion("region")));
        }

        if (hasValue("texture")) {
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(getTexture("texture"))));
        }

        if (hasValue("drawable")) {
            Drawable drawable = XUI.getAssets().getAsset(getValue("drawable"), Drawable.class);
            image.setDrawable(drawable);
        }

        if (hasValue("scaling")) {
            image.setScaling(getScaling("scaling"));
        }

        if (hasValue("align")) {
            image.setAlign(getAlign("align"));
        }
    }
}
