package ru.vsu.dao.db;

import ru.vsu.dao.entity.Event;

import java.util.Map;

public interface Storage {

    Map<Integer, Event> getEvents();
}
