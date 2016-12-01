package ua.com.integer.gdx.xml.ui.setup.imp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.res.Assets;
import ua.com.integer.gdx.xml.ui.setup.ActorSetup;

public class ImageSetup extends ActorSetup {
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
