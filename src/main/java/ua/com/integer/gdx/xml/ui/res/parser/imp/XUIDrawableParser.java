package ua.com.integer.gdx.xml.ui.res.parser.imp;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;

public class XUIDrawableParser implements XUIAssetParser {
    @Override
    public void parse(XmlReader.Element element, XUIAssets assets) {
        String drawableName = element.getAttribute("name", "drawable");
        String type = element.getAttribute("type", "region");
        TextureRegion region = XUIAssetsAccess.getTextureRegion(element.getAttribute("region"));

        if (type.equals("region")) {
            assets.putAsset(drawableName, new TextureRegionDrawable(region), Drawable.class);
        } else if (type.equals("nine-patch")) {
            int left = Integer.parseInt(element.getAttribute("left", "0"));
            int right = Integer.parseInt(element.getAttribute("right", "0"));
            int top = Integer.parseInt(element.getAttribute("top", "0"));
            int bottom = Integer.parseInt(element.getAttribute("bottom", "0"));
            assets.putAsset(drawableName, new NinePatchDrawable(new NinePatch(region, left, right, top, bottom)), Drawable.class);
        }
    }
}
