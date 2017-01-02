package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.utils.ObjectMap;

public class XUIVariables {
    private ObjectMap<String, String> variables = new ObjectMap<>();

    public void set(String name, Object value) {
        variables.put(name, value.toString());
    }

    public String getString(String name, String defValue) {
        return variables.get(name, defValue);
    }

    public boolean getBoolean(String name, boolean defValue) {
        return Boolean.parseBoolean(getString(name, Boolean.toString(defValue)));
    }

    public byte getByte(String name, byte defValue) {
        return Byte.parseByte(getString(name, Byte.toString(defValue)));
    }

    public int getInt(String name, int defValue) {
        return Integer.parseInt(getString(name, Integer.toString(defValue)));
    }

    public long getLong(String name, long defValue) {
        return Long.parseLong(getString(name, Long.toString(defValue)));
    }

    public float getFloat(String name, float defValue) {
        return Float.parseFloat(getString(name, Float.toString(defValue)));
    }

    public double getDouble(String name, double defValue) {
        return Double.parseDouble(getString(name, Double.toString(defValue)));
    }

    public void clear() {
        variables.clear();
    }
}
