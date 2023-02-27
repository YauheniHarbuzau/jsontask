package ru.clevertec.util.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

/**
 * JSON-Object-JSON Converter
 */
public class Converter {

    /**
     * Конвертирование объекта в JSON
     *
     * @param object объект/сущность
     * @return JSON (String)
     * @see #objToFieldValueMap(Object)
     */
    public static String objToJson(Object object) {
        Map<String, String> fieldValueMap = objToFieldValueMap(object);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\"").append(":").append(entry.getValue()).append(",");
        }
        return sb.delete(sb.length() - 1, sb.length()).append("}").toString();
    }

    /**
     * Получение полей объекта и их значений
     *
     * @param object объект/сущность
     * @return пары поле-значение (Map<String, String>)
     * @see #valueToString(Object, Field)
     */
    private static Map<String, String> objToFieldValueMap(Object object) {
        return stream(object.getClass().getDeclaredFields())
                .collect(toMap(
                        Field::getName,
                        field -> {
                            field.setAccessible(true);
                            return valueToString(object, field);
                        },
                        (k, v) -> k, LinkedHashMap::new
                ));
    }

    /**
     * Приведение значения поля объекта с формату строки
     *
     * @param object объект/сущность
     * @param field  поле объекта/сущности
     * @return значение поля в формате строки (String)
     */
    private static String valueToString(Object object, Field field) {
        try {
            return field.getType() == String.class || field.getType() == Character.class ?
                    "\"" + field.get(object) + "\"" : String.valueOf(field.get(object));
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    /**
     * Конвертирование JSON в объект
     *
     * @param object объект/сущность в которую происходит конвертирование
     * @param source строка JSON (String)
     * @return объект/сущность
     * @see #jsonToFieldValueMap(String)
     */
    public static Object jsonToObj(Object object, String source) {
        Map<String, Object> fieldValueMap = jsonToFieldValueMap(source);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.getType() == String.class) {
                    field.set(object, fieldValueMap.get(field.getName()));
                } else if (field.getType() == Byte.class) {
                    field.set(object, Byte.parseByte((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Short.class) {
                    field.set(object, Short.parseShort((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Integer.class) {
                    field.set(object, Integer.parseInt((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Long.class) {
                    field.set(object, Long.parseLong((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Float.class) {
                    field.set(object, Float.parseFloat((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Double.class) {
                    field.set(object, Double.parseDouble((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Boolean.class) {
                    field.set(object, Boolean.parseBoolean((String) fieldValueMap.get(field.getName())));
                } else if (field.getType() == Character.class) {
                    field.set(object, ((String) fieldValueMap.get(field.getName())).charAt(0));
                }
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * Получение полей объекта и их значений
     *
     * @param source строка JSON (String)
     * @return пары поле-значение (Map<String, Object>)
     * @see #isJsonValid(String)
     */
    private static Map<String, Object> jsonToFieldValueMap(String source) {
        source = source.replaceAll(" ", "");
        if (isJsonValid(source)) {
            return stream(source.substring(1, source.length() - 1).split(","))
                    .map(i -> i.replaceAll("\"", "").split(":"))
                    .collect(toMap(i -> i[0], i -> i[1]));
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * Проверка валидности JSON
     *
     * @param source строка JSON (String)
     * @return true/false
     */
    private static boolean isJsonValid(String source) {
        return source.matches("[{](([A-Za-z0-9\"]+:.+),)*([A-Za-z0-9\"]+:.+)[}]");
    }
}
