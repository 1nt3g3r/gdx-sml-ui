package ua.com.integer.gdx.xml.ui.res.parser;

import com.badlogic.gdx.utils.XmlReader;

import ua.com.integer.gdx.xml.ui.res.XUIAssets;

public interface XUIAssetParser {
    void parse(XmlReader.Element element, XUIAssets assets);
}
