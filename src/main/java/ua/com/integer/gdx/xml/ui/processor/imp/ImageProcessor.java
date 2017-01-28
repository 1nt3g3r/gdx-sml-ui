package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;

/**
 * Setup {@link Image}
 */
public class ImageProcessor extends XUIProcessor {
    @Override
    public void process() {
        Image image = (Image) element.resultActor;

        if (hasAttribute("region")) {
            image.setDrawable(new TextureRegionDrawable(getRegion("region")));
        }

        if (hasAttribute("ninepatch")) {
            String atlasAndPatchPath = getAttribute("ninepatch");
            NinePatch ninePatch = XUIAssetsAccess.getNinePatch(atlasAndPatchPath);
            NinePatchDrawable drawable = new NinePatchDrawable(ninePatch);
            image.setDrawable(drawable);
        }

        if (hasAttribute("texture")) {
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(getTexture("texture"))));
        }

        if (hasAttribute("drawable")) {
            Drawable drawable = XUI.assets().getAsset(getAttribute("drawable"), Drawable.class);
            image.setDrawable(drawable);
        }

        if (hasAttribute("scaling")) {
            image.setScaling(getScaling("scaling"));
        }

        if (hasAttribute("align")) {
            image.setAlign(getAlign("align"));
        }
    }
}
