package ru.vsu.dao.db.sqlQueryCreator;

import ru.vsu.di.annotation.Component;

import java.lang.reflect.Method;

@Component
public class AnnotationSQLQueryCreator implements SQLQueryCreator {
    @Override
    public String create(Method method, Object[] args) {
        return null;
    }
}
