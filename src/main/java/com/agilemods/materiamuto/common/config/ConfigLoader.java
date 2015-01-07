package com.agilemods.materiamuto.common.config;

import com.agilemods.materiamuto.MateriaMuto;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConfigLoader {

    public static void loadClass(Class<?> configClass) {
        String name = configClass.getSimpleName();
        for (Field field : configClass.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    handleField(name, field);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }

        MateriaMuto.configuration.save();
    }

    private static void handleField(String clazz, Field field) throws IllegalAccessException {
        Category category = field.getAnnotation(Category.class);
        Description description = field.getAnnotation(Description.class);
        Name name = field.getAnnotation(Name.class);

        if (category != null && !category.value().isEmpty()) {
            clazz = clazz + "." + category.value();
        }

        String fieldName = field.getName();
        if (name != null && !name.value().isEmpty()) {
            fieldName = name.value();
        }

        FieldType fieldType = FieldType.getType(field);
        Property property;
        if (description != null && !description.value().isEmpty()) {
            property = getProperty(
                    MateriaMuto.configuration,
                    clazz,
                    fieldName,
                    description.value(),
                    fieldType,
                    field.get(null)
            );
        } else {
            property = getProperty(
                    MateriaMuto.configuration,
                    clazz,
                    fieldName,
                    fieldType,
                    field.get(null)
            );
        }

        switch (fieldType) {
            case BYTE:
                field.setByte(null, (byte)property.getInt());
                break;
            case BOOLEAN:
                field.setBoolean(null, property.getBoolean());
                break;
            case SHORT:
                field.setShort(null, (short)property.getInt());
                break;
            case INTEGER:
                field.setInt(null, property.getInt());
                break;
            case LONG:
                field.setLong(null, property.getInt());
                break;
            case FLOAT:
                field.setFloat(null, (float)property.getDouble());
                break;
            case DOUBLE:
                field.setDouble(null, property.getDouble());
                break;
            case STRING:
                field.set(null, property.getString());
                break;
        }
    }

    private static Property getProperty(Configuration configuration, String category, String name, FieldType type, Object data) {
        switch (type) {
            case BYTE:
                return configuration.get(category, name, (Byte) data);
            case BOOLEAN:
                return configuration.get(category, name, (Boolean) data);
            case SHORT:
                return configuration.get(category, name, (Short) data);
            case INTEGER:
                return configuration.get(category, name, (Integer) data);
            case LONG:
                return configuration.get(category, name, (Long) data);
            case FLOAT:
                return configuration.get(category, name, (Float) data);
            case DOUBLE:
                return configuration.get(category, name, (Double) data);
            case STRING:
                return configuration.get(category, name, (String) data);
            default:
                return null;
        }
    }

    private static Property getProperty(Configuration configuration, String category, String name, String description, FieldType type, Object data) {
        switch (type) {
            case BYTE:
                return configuration.get(category, name, (Byte) data, description);
            case BOOLEAN:
                return configuration.get(category, name, (Boolean) data, description);
            case SHORT:
                return configuration.get(category, name, (Short) data, description);
            case INTEGER:
                return configuration.get(category, name, (Integer) data, description);
            case LONG:
                return configuration.get(category, name, (Long) data, description);
            case FLOAT:
                return configuration.get(category, name, (Float) data, description);
            case DOUBLE:
                return configuration.get(category, name, (Double) data, description);
            case STRING:
                return configuration.get(category, name, (String) data, description);
            default:
                return null;
        }
    }

    private static enum FieldType {
        BYTE,
        BOOLEAN,
        SHORT,
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        STRING;

        public static FieldType getType(Field field) {
            return FieldType.valueOf(field.getType().getSimpleName().toUpperCase());
        }
    }
}
