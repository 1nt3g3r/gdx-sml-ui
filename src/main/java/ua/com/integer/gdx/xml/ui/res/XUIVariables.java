package ua.com.integer.gdx.xml.ui.res;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Class for convenient work with user-predefined variables.
 */
public class XUIVariables {
    private ObjectMap<String, String> variables = new ObjectMap<>();

    /**
     * Set variable of any type. If you want clear variable, just pass Null value
     */
    public void set(String name, Object value) {
        if (value == null) {
            variables.remove(name);
        } else {
            variables.put(name, value.toString());
        }
    }

    /**
     * Return variable as String
     */
    public String getString(String name, String defValue) {
        return variables.get(name, defValue);
    }

    /**
     * Return variable as boolean
     */
    public boolean getBoolean(String name, boolean defValue) {
        return Boolean.parseBoolean(getString(name, Boolean.toString(defValue)));
    }

    /**
     * Return variable as byte
     */
    public byte getByte(String name, byte defValue) {
        return Byte.parseByte(getString(name, Byte.toString(defValue)));
    }

    /**
     * Return variable as int
     */
    public int getInt(String name, int defValue) {
        return Integer.parseInt(getString(name, Integer.toString(defValue)));
    }

    /**
     * Return variable as long
     */
    public long getLong(String name, long defValue) {
        return Long.parseLong(getString(name, Long.toString(defValue)));
    }

    /**
     * Return variable as float
     */
    public float getFloat(String name, float defValue) {
        return Float.parseFloat(getString(name, Float.toString(defValue)));
    }

    /**
     * Return variable as double
     */
    public double getDouble(String name, double defValue) {
        return Double.parseDouble(getString(name, Double.toString(defValue)));
    }

    /**
     * Removes all variables
     */
    public void clear() {
        variables.clear();
    }
}
