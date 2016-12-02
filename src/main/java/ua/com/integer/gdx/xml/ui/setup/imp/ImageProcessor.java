package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.setup.ActorProcessor;

public class ImageProcessor extends ActorProcessor {
    @Override
    public void setup() {
        Image image = (Image) actorDef.getActor();

        if (hasValue("region")) {
            image.setDrawable(new TextureRegionDrawable(getRegion("region")));
        }

        if (hasValue("scaling")) {
            image.setScaling(getScaling("scaling"));
        }

        if (hasValue("align")) {
            image.setAlign(getAlign("align"));
        }
    }
}
