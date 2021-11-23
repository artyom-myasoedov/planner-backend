package ru.vsu.dao.db.sqlQueryCreator;

import java.lang.reflect.Method;

@FunctionalInterface
public interface SQLQueryCreator {

    String create(Method method, Object[] args);
}
