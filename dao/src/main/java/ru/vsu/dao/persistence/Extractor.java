package ru.vsu.dao.persistence;

import ru.vsu.dao.db.ConnectionManager;

import java.sql.Connection;
import java.util.List;


@FunctionalInterface
public interface Extractor {

    Object extract(Connection connection, List<Condition> conditions);
}
