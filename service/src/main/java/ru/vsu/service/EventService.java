package ru.vsu.service;

import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;

import java.util.Collection;
import java.util.List;

public interface EventService {

    Event findById(Integer id);

    Event create(Event entity);

    Event update(Event entity);

    void deleteById(Integer id);

    Collection<Event> findByTypes(List<EventType> types);

    Collection<Event> findAll();

    Collection<Event> findByYear(Integer year, List<EventType> types);

    Collection<Event> findByMonth(Integer month, List<EventType> types);

    Collection<Event> findByMonthAndDay(Integer month, Integer day, List<EventType> types);

    Collection<Event> findByDate(Integer year, Integer month, Integer day, List<EventType> types, TimeComparisonOperation operation);
}
