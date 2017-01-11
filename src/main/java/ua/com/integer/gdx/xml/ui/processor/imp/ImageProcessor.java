package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class ImageProcessor extends XUIProcessor {
    @Override
    public void process() {
        Image image = (Image) element.resultActor;

        if (hasValue("region")) {
            image.setDrawable(new TextureRegionDrawable(getRegion("region")));
        }

        if (hasValue("texture")) {
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(getTexture("texture"))));
        }

        if (hasValue("drawable")) {
            Drawable drawable = XUI.assets().getAsset(getAttribute("drawable"), Drawable.class);
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
