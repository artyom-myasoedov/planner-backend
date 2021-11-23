package ru.vsu.dao.db;

import ru.vsu.dao.entity.EventType;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class SubClassSQLMapper {

    private String predicateColumn;

    private Class<?> modelClass;

    private Map<?, Class<?>> classesViewToClasses;

    private Map<String, String> columnsToGeneralFields;

    private Map<Class<?>, Map<String, String>> subClassesColumnsToFields;

    public SubClassSQLMapper(String predicateColumn, Class<?> modelClass, Map<?, Class<?>> classesViewToClasses, Map<String, String> columnsToGeneralFields, Map<Class<?>, Map<String, String>> subClassesColumnsToFields) {
        this.predicateColumn = predicateColumn;
        this.modelClass = modelClass;
        this.classesViewToClasses = classesViewToClasses;
        this.columnsToGeneralFields = columnsToGeneralFields;
        this.subClassesColumnsToFields = subClassesColumnsToFields;
    }

    public Object map(ResultSet rs) {
        try {
            var classView = rs.getObject(predicateColumn);
            Class<?> classToMapping = classesViewToClasses.getOrDefault(classView, modelClass);
            Object retVal = classToMapping.getDeclaredConstructor().newInstance();
            setFields(rs, classToMapping, retVal, columnsToGeneralFields);
            if (!classToMapping.equals(modelClass)) {
                setFields(rs, classToMapping, retVal, subClassesColumnsToFields.get(classToMapping));
            }
            return retVal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setFields(ResultSet rs, Class<?> classToMapping, Object retVal, Map<String, String> columnsToFields) throws NoSuchFieldException, SQLException, IllegalAccessException {
        for (String key : columnsToFields.keySet()) {
            Field declaredField = classToMapping.getDeclaredField(key);
            declaredField.setAccessible(true);
            var val = rs.getObject(columnsToFields.get(key));
            if (LocalDateTime.class.isAssignableFrom(declaredField.getType())) {
                val = ((Timestamp) val).toLocalDateTime();
            }
            if (EventType.class.isAssignableFrom(declaredField.getType())) {
                val = EventType.fromInteger((Integer) val);
            }
            declaredField.set(retVal, val);
        }
    }
}
