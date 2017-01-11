package ua.com.integer.gdx.xml.ui.res.parser.imp;

import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.util.ActorMathEval;
import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;

public class XUIVariableParser implements XUIAssetParser {
    @Override
    public void parse(XmlReader.Element element, XUIAssets assets) {
        String name = element.getAttribute("name");
        String value = element.getAttribute("value");
        ActorMathEval.setVar(name, value);
    }
}
