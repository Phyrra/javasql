package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.exception.BadParameterException;

import java.util.HashMap;
import java.util.Map;

public enum DATA_TYPE {
        UNIQUEIDENTIFIER,
        VARCHAR,
        INT,
        DOUBLE,
        DATETIME;
        // to be expanded

        private static class KeyValuePair {
            public String key;
            public DATA_TYPE value;

            public KeyValuePair(String key, DATA_TYPE value) {
                this.key = key;
                this.value = value;
            }
        }

        private static Map<String, DATA_TYPE> getMap(KeyValuePair... kvps) {
            Map<String, DATA_TYPE> map = new HashMap<String, DATA_TYPE>();

            for (KeyValuePair kvp : kvps) {
                map.put(kvp.key, kvp.value);
            }

            return map;
        }

        private static Map<String, DATA_TYPE> map = getMap(
                new KeyValuePair("uniqueidntifier", UNIQUEIDENTIFIER),
                new KeyValuePair("nvarchar", VARCHAR),
                new KeyValuePair("varchar", VARCHAR),
                new KeyValuePair("int", INT),
                new KeyValuePair("float", DOUBLE),
                new KeyValuePair("double", DOUBLE),
                new KeyValuePair("datetime", DATETIME)
                // ...
        );

        public static DATA_TYPE fromString(String type) {
            if (!map.containsKey(type)) {
                throw new BadParameterException("Unknown type: " + type);
            }

            return map.get(type);
        }
    }