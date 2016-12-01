package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.ActorDef;
import ua.com.integer.gdx.xml.ui.ActorTransform;

public class PropertiesExtractor {
    public static void extractProperties(XmlReader.Element e, ActorDef def) {
        def.setName(e.getName());

        for(String attributeName : e.getAttributes().keys()) {
            def.setVar(attributeName, e.getAttribute(attributeName));
        }

        ActorTransform t = def.transform();
        t.x(e.get("x", "0"));
        t.y(e.get("y", "0"));
        t.width(e.get("width", "0"));
        t.height(e.get("height", "0"));
        t.scaleX(e.get("scaleX", "1"));
        t.scaleY(e.get("scaleY", "1"));
        t.rotation(e.get("rotation", "0"));
        t.originX(e.get("originX", "0"));
        t.originY(e.get("originY", "0"));
    }
}
