package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.utils.XmlReader;

public class PropertiesExtractor {
    public static void extractProperties(XmlReader.Element e, XUIElement def) {
        def.name = e.getName();

        if (e.getAttributes() == null) {
            return;
        }

        for(String attributeName : e.getAttributes().keys()) {
            def.setVar(attributeName, e.getAttribute(attributeName));
        }
    }
}
