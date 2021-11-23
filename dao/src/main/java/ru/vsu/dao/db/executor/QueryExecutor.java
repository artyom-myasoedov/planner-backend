package ru.vsu.dao.db.executor;

@FunctionalInterface
public interface QueryExecutor {

    Object execute(String query, Class<?> returnType);
}
