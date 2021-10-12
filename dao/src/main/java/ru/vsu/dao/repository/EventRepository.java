package ru.vsu.dao.repository;

import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;

import java.util.Collection;
import java.util.List;

public interface EventRepository extends Repository<Event, Integer> {

    Collection<Event> findByTypes(List<EventType> types);

    Collection<Event> findByYear(Integer year, List<EventType> types);

    Collection<Event> findByMonth(Integer month, List<EventType> types);

    Collection<Event> findByDay(Integer month, Integer day, List<EventType> types);

    Collection<Event> findByDay(Integer year, Integer month, Integer day, List<EventType> types);

    Collection<Event> findAfterDay(Integer year, Integer month, Integer day, List<EventType> types);

    Collection<Event> findBeforeDay(Integer year, Integer month, Integer day, List<EventType> types);

}
