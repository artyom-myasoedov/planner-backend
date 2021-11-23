package ru.vsu.dao.persistence;


import ru.vsu.dao.persistence.Condition;

import java.sql.Connection;
import java.util.List;

public interface Executor {


    Object execute(String queryTemplate, List<?> args, List<Condition> conditions, Connection connection);
}
