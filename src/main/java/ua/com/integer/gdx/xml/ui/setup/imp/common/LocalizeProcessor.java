package ua.com.integer.gdx.xml.ui.setup.imp.common;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.setup.XUIProcessor;

public class LocalizeProcessor extends XUIProcessor {
    @Override
    public void setup() {
        for(String key : XUIElement.attributes.keys()) {
            String value = XUIElement.attributes.get(key);

            if (value.contains("@")) {
                XUIElement.attributes.put(key, applyLocalization(value));
            }
        }
    }

    private static String applyLocalization(String value) {
        Array<String> tmpArray = new Array<>();
        for(int i = 0; i < value.length(); i++) {
            int index1 = value.indexOf("@", i);
            if (index1 != -1) {
                int index2 = value.indexOf("@", index1 + 1);

                i = index2;

                String var = value.substring(index1 + 1, index2);
                tmpArray.add(var);
            }
        }

        for(String var : tmpArray) {
            String bundleName = "default";
            String key = var;
            if (var.contains("->")) {
                String[] parts = var.split("->");
                bundleName = parts[0];
                key = parts[1];
            }

            I18NBundle bundle1 = XUI.getAssets().getAsset(bundleName, I18NBundle.class);
            String varValue = bundle1.get(key);
            value = value.replace("@" + var + "@", varValue);
        }

        return value;
    }
}
