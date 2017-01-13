package ua.com.integer.gdx.xml.ui.res.parser.imp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;

/**
 * Parse {@link Color} from XML.
 *
 * Also this class registers all default colors defined as constants in {@link Color} (RED, BLUE, etc)
 */
public class XUIColorParser implements XUIAssetParser {
    private XUIAssets assets;

    public XUIColorParser(XUIAssets assets) {
        this.assets = assets;

        registerColor("CLEAR", com.badlogic.gdx.graphics.Color.CLEAR);
        registerColor("BLACK", com.badlogic.gdx.graphics.Color.BLACK);
        registerColor("WHITE", com.badlogic.gdx.graphics.Color.WHITE);
        registerColor("LIGHT_GRAY", com.badlogic.gdx.graphics.Color.LIGHT_GRAY);
        registerColor("GRAY", com.badlogic.gdx.graphics.Color.GRAY);
        registerColor("DARK_GRAY", com.badlogic.gdx.graphics.Color.DARK_GRAY);
        registerColor("BLUE", com.badlogic.gdx.graphics.Color.BLUE);
        registerColor("NAVY", com.badlogic.gdx.graphics.Color.NAVY);
        registerColor("ROYAL", com.badlogic.gdx.graphics.Color.ROYAL);
        registerColor("SLATE", com.badlogic.gdx.graphics.Color.SLATE);
        registerColor("SKY", com.badlogic.gdx.graphics.Color.SKY);
        registerColor("CYAN", com.badlogic.gdx.graphics.Color.CYAN);
        registerColor("TEAL", com.badlogic.gdx.graphics.Color.TEAL);
        registerColor("GREEN", com.badlogic.gdx.graphics.Color.GREEN);
        registerColor("CHARTREUSE", com.badlogic.gdx.graphics.Color.CHARTREUSE);
        registerColor("LIME", com.badlogic.gdx.graphics.Color.LIME);
        registerColor("FOREST", com.badlogic.gdx.graphics.Color.FOREST);
        registerColor("OLIVE", com.badlogic.gdx.graphics.Color.OLIVE);
        registerColor("YELLOW", com.badlogic.gdx.graphics.Color.YELLOW);
        registerColor("GOLD", com.badlogic.gdx.graphics.Color.GOLD);
        registerColor("GOLDENROD", com.badlogic.gdx.graphics.Color.GOLDENROD);
        registerColor("ORANGE", com.badlogic.gdx.graphics.Color.ORANGE);
        registerColor("BROWN", com.badlogic.gdx.graphics.Color.BROWN);
        registerColor("TAN", com.badlogic.gdx.graphics.Color.TAN);
        registerColor("FIREBRICK", com.badlogic.gdx.graphics.Color.FIREBRICK);
        registerColor("RED", com.badlogic.gdx.graphics.Color.RED);
        registerColor("SCARLET", com.badlogic.gdx.graphics.Color.SCARLET);
        registerColor("CORAL", com.badlogic.gdx.graphics.Color.CORAL);
        registerColor("SALMON", com.badlogic.gdx.graphics.Color.SALMON);
        registerColor("PINK", com.badlogic.gdx.graphics.Color.PINK);
        registerColor("MAGENTA", com.badlogic.gdx.graphics.Color.MAGENTA);
        registerColor("PURPLE", com.badlogic.gdx.graphics.Color.PURPLE);
        registerColor("VIOLET", com.badlogic.gdx.graphics.Color.VIOLET);
        registerColor("MAROON", com.badlogic.gdx.graphics.Color.MAROON);
    }

    private void registerColor(String name, Color color) {
        assets.putAsset(name, color, Color.class);
    }

    @Override
    public void parse(XmlReader.Element element, XUIAssets assets) {
        String colorValue = element.getAttribute("value");
        Color color = assets.getAsset(colorValue, Color.class);
        if (color == null) {
            color = Color.valueOf(colorValue);
        }

        String colorName = element.getAttribute("name");
        registerColor(colorName, color);
    }
}