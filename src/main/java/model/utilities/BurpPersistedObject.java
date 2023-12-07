package model.utilities;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.persistence.PersistedList;
import burp.api.montoya.persistence.PersistedObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BurpPersistedObject {
    public BurpPersistedObject(MontoyaApi api, String persistedChildObjKey) {
        _api                  = api;
        _persistedChildObjKey = persistedChildObjKey;
        childObject           = _api.persistence().extensionData().getChildObject(_persistedChildObjKey);
        _selfFields           = this.getClass().getDeclaredFields();
    }

    public void save() throws Exception {
        _api.logging().logToOutput("Saving: " + this.getClass().getName());

        for (Field field : _selfFields) {
            Type fieldType = field.getGenericType();

            if (fieldType instanceof Class<?> fieldClazz)
                _saveClassField(this, field, fieldClazz, childObject);
            else if (fieldType instanceof ParameterizedType fieldParamType)
                _saveGenericField(this, field, fieldParamType, childObject);
            else
                throw new Exception("Unsupported type");
        }

        _api.persistence().extensionData().setChildObject(_persistedChildObjKey, childObject); //this line may not be necessary
    }

    protected     PersistedObject childObject;
    private final MontoyaApi      _api;
    private final String          _persistedChildObjKey;
    private final Field[]         _selfFields;

    private void _retrieveClassField(BurpPersistedObject instance, Field field, Class<?> fieldClazz, PersistedObject savedSelf) throws Exception {
        Object val;
        if (fieldClazz.equals(int.class)) {
            val = savedSelf.getInteger(field.getName());
        } else if (fieldClazz.equals(String.class)) {
            val = savedSelf.getString(field.getName());
        } else if (fieldClazz.equals(boolean.class)) {
            val = savedSelf.getBoolean(field.getName());
        } else if (fieldClazz.equals(short.class)) {
            val = savedSelf.getShort(field.getName());
        } else {
            throw new Exception("Unsupported non-generic type");
        }
        _api.logging().logToOutput("value retrieved: " + val);
        _setVal(instance, field, val);
    }

    private void _retrieveGenericField(BurpPersistedObject instance, Field field, ParameterizedType fieldParamType, PersistedObject savedSelf) throws Exception {
        Type   fieldClazz     = fieldParamType.getRawType();
        Type[] actualTypeArgs = fieldParamType.getActualTypeArguments();

        if (!fieldClazz.equals(List.class)) throw new Exception("Unsupported generic raw type");
        if (actualTypeArgs.length != 1) throw new Exception("Unsupported generic type args");
        if (!actualTypeArgs[0].equals(String.class)) throw new Exception("Unsupported generic type args");

        PersistedList<String> strLst = savedSelf.getStringList(field.getName());
        List<String>          val    = strLst.stream().toList();
        _setVal(instance, field, val);
    }

    private void _setVal(Object instance, Field field, Object val) throws IllegalAccessException {
        _api.logging().logToOutput("_setVal: " + field.getName() + " = " + val.toString());
        field.setAccessible(true);
        field.set(instance, val);
    }

    private static <Any> Any _getVal(Object instance, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return (Any) (field.get(instance));
    }

    private static void _saveClassField(Object instance, Field field, Class<?> fieldClazz, PersistedObject savedSelf) throws Exception {
        if (fieldClazz.equals(int.class)) {
            int val = _getVal(instance, field);
            savedSelf.setInteger(field.getName(), val);
        } else if (fieldClazz.equals(String.class)) {
            String val = _getVal(instance, field);
            savedSelf.setString(field.getName(), val);
        } else if (fieldClazz.equals(boolean.class)) {
            boolean val = _getVal(instance, field);
            savedSelf.setBoolean(field.getName(), val);
        } else if (fieldClazz.equals(short.class)) {
            short val = _getVal(instance, field);
            savedSelf.setShort(field.getName(), val);
        } else {
            throw new Exception("Unsupported non-generic type");
        }
    }

    private static void _saveGenericField(Object instance, Field field, ParameterizedType fieldParamType, PersistedObject savedSelf) throws Exception {
        Type   fieldClazz     = fieldParamType.getRawType();
        Type[] actualTypeArgs = fieldParamType.getActualTypeArguments();

        if (!fieldClazz.equals(List.class)) throw new Exception("Unsupported generic raw type");
        if (actualTypeArgs.length != 1) throw new Exception("Unsupported generic type args");
        if (!actualTypeArgs[0].equals(String.class)) throw new Exception("Unsupported generic type args");

        List<String>          val    = _getVal(instance, field);
        PersistedList<String> strLst = PersistedList.persistedStringList();
        strLst.addAll(val);
        savedSelf.setStringList(field.getName(), strLst);
    }

    protected void _retrieve() throws Exception {
        _api.logging().logToOutput("Retrieving: " + this.getClass().getName());

        for (Field field : _selfFields) {
            // Check if the field is static or final
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue; // Skip static & final fields
            }
            _api.logging().logToOutput("Retrieving: " + field.getName());

            Type fieldType = field.getGenericType();
            if (fieldType instanceof Class<?> fieldClazz)
                _retrieveClassField(this, field, fieldClazz, childObject);
            else if (fieldType instanceof ParameterizedType fieldParamType)
                _retrieveGenericField(this, field, fieldParamType, childObject);
        }
    }
}