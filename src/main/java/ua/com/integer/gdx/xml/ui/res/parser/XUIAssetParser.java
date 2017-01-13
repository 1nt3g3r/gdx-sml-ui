package ua.com.integer.gdx.xml.ui.res.parser;

import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.res.XUIAssets;

/**
 * Parse assets from XML and stores them in {@link XUIAssets}.
 */
public interface XUIAssetParser {
    /**
     * Parse assets from given XML element and stores them in {@link XUIAssets}
     */
    void parse(XmlReader.Element element, XUIAssets assets);
}
