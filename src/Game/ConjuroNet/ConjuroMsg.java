package Game.ConjuroNet;

import Game.Lib.Consts;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Hashtable;

public class ConjuroMsg implements Consts {
    private Hashtable<String, String> values;
    private MessageType type;
    public ConjuroMsg(String pData) {
        String[] msgValues = pData.split(MESSAGE_SEPARATOR);
        if (msgValues != null && msgValues.length > 0) {
            type = MessageType.valueOf(msgValues[0]);

            values = new Hashtable<>();
            for (int valuesIndex = 1; valuesIndex < msgValues.length; valuesIndex++) {
                String[] keyMap = msgValues[valuesIndex].split(MESSAGE_VALUES_SEPARATOR);
                values.put(keyMap[0], keyMap[1]);
            }
        }
    }

    public ConjuroMsg(MessageType pType) {
        this.type = pType;
        values = new Hashtable<>();
    }

    public String getValue(String pKey) {
        String result = "";
        result = values.contains(pKey) ? values.get(pKey) : result;
        return result;
    }

    public void addValue(String pKey, String pValue) {
        values.put(pKey, pValue);
    }

    public String getStringMsg() {
        String result = "";
        String comma = "";
        result = result.concat(this.type.toString()+MESSAGE_SEPARATOR);
        for (String key: this.values.keySet()) {
            result = result.concat(comma);
            result = result.concat(key + MESSAGE_VALUES_SEPARATOR + values.get(key));
            result = MESSAGE_SEPARATOR;
        }
        return result;
    }

    public MessageType getType () {
        return this.type;
    }
}
