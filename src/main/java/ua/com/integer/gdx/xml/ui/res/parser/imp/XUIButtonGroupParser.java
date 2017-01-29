package ua.com.integer.gdx.xml.ui.res.parser.imp;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;

public class XUIButtonGroupParser implements XUIAssetParser {
    @Override
    public void parse(XmlReader.Element element, XUIAssets assets) {
        ButtonGroup buttonGroup = new ButtonGroup();

        if (element.getAttributes().containsKey("minCheckCount")) {
            buttonGroup.setMinCheckCount(Integer.parseInt(element.getAttributes().get("minCheckCount")));
        }

        if (element.getAttributes().containsKey("maxCheckCount")) {
            buttonGroup.setMaxCheckCount(Integer.parseInt(element.getAttributes().get("maxCheckCount")));
        }

        assets.putAsset(element.getAttribute("name"), buttonGroup, ButtonGroup.class);
    }
}
