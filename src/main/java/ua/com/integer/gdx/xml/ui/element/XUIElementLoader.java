package ua.com.integer.gdx.xml.ui.element;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Helper class that loads {@link XUIElement} from String or {@link com.badlogic.gdx.files.FileHandle}
 */
public class XUIElementLoader {
    /**
     * Loads {@link XUIElement} from {@link FileHandle}. File should be valid XML
     */
    public static XUIElement load(FileHandle file) {
        return load(file.readString());
    }

    /**
     * Loads {@link XUIElement} from given XML string
     */
    public static XUIElement load(String xml) {
        XmlReader reader = new XmlReader();
        XmlReader.Element rootXml = reader.parse(xml);

        XUIElement rootDef = createXUIElement(rootXml);
        load(rootDef, rootXml);

        return rootDef;
    }

    private static void load(XUIElement element, XmlReader.Element xml) {
        int childCount = xml.getChildCount();
        for(int i = 0; i < childCount; i++) {
            XmlReader.Element childXml = xml.getChild(i);
            XUIElement child = createXUIElement(childXml);
            element.children.add(child);

            load(child, childXml);
        }
    }

    private static XUIElement createXUIElement(XmlReader.Element xml) {
        XUIElement result = new XUIElement();
        result.type = xml.getName();
        if (xml.getAttributes() != null) {
            result.attributes.putAll(xml.getAttributes());
        }
        return result;
    }
}
