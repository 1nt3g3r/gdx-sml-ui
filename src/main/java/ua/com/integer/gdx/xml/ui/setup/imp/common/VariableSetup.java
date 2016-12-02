package ua.com.integer.gdx.xml.ui.setup.imp.common;

import com.badlogic.gdx.utils.Array;

import ua.com.integer.gdx.xml.ui.res.Assets;
import ua.com.integer.gdx.xml.ui.setup.ActorProcessor;

public class VariableSetup extends ActorProcessor {
    private Array<String> tmpArray = new Array<>();

    @Override
    public void setup() {
        for(String key : actorDef.getAttributes().keys()) {
            String value = actorDef.getAttributes().get(key);

            if (value.contains("$")) {
                actorDef.getAttributes().put(key, replaceVariables(value));
            }
        }
    }

    private String replaceVariables(String value) {
        tmpArray.clear();
        for(int i = 0; i < value.length(); i++) {
            int index1 = value.indexOf("$", i);
            if (index1 != -1) {
                int index2 = value.indexOf("$", index1 + 1);

                i = index2;

                String var = value.substring(index1 + 1, index2);
                tmpArray.add(var);
            }
        }

        for(String var : tmpArray) {
            String varValue = Assets.getInstance().getAsset(var, String.class);
            value = value.replace("$" + var + "$", varValue);
        }

        return value;
    }
}
