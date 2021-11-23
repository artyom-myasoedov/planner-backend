package ru.vsu.dao.db;

import ru.vsu.dao.db.executor.QueryExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class JPAInvocationHandler implements InvocationHandler {

    private Map<String, String> methodNamesToSQLTemplates;

    private QueryExecutor defaultExecutor;

    private QueryExecutor findByIdExecutor;

    private SQLQueryPlaceHolder placeHolder;

    private Class<?> modelClass;

    public JPAInvocationHandler(Map<String, String> methodNamesToSQLTemplates, QueryExecutor defaultExecutor, QueryExecutor findByIdExecutor, SQLQueryPlaceHolder placeHolder, Class<?> modelClass) {
        this.methodNamesToSQLTemplates = methodNamesToSQLTemplates;
        this.defaultExecutor = defaultExecutor;
        this.findByIdExecutor = findByIdExecutor;
        this.placeHolder = placeHolder;
        // TODO: 21.11.2021 SQLPlaceHolder - подставлять в шаблон запроса значения, генерировать шаблоны запросов, всё заинжектить
        this.modelClass = modelClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        QueryExecutor executor;
        if (method.getName().equals("findById")) executor = findByIdExecutor;
        else executor = defaultExecutor;

        return executor.execute(
                placeHolder.putValues(methodNamesToSQLTemplates.get(method.getName()), args, modelClass),
                method.getReturnType());
    }
}
