package ru.vsu.dao.repository.impl;

import ru.vsu.dao.db.Storage;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.repository.EventRepository;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EventRepositoryImpl implements EventRepository {

    @InjectByType
    private Storage storage;


    private Stream<Event> getDataStreamFilteredByTypes(List<EventType> types) {
        return storage.getEvents().values().stream()
                .filter(event -> types.contains(event.getEventType()));
    }

    @Override
    public Collection<Event> findByTypes(List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findByYear(Integer year, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findByMonth(Integer month, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getMonthValue() == month)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findByDay(Integer month, Integer day, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getMonthValue() == month
                        && event.getDateTime().getDayOfMonth() == day)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findByDay(Integer year, Integer month, Integer day, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getMonthValue() == month
                        && event.getDateTime().getDayOfMonth() == day
                        && event.getDateTime().getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findAfterDay(Integer year, Integer month, Integer day, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getMonthValue() > month
                        && event.getDateTime().getDayOfMonth() > day
                        && event.getDateTime().getYear() > year)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Event> findBeforeDay(Integer year, Integer month, Integer day, List<EventType> types) {
        return getDataStreamFilteredByTypes(types)
                .filter(event -> event.getDateTime().getMonthValue() < month
                        && event.getDateTime().getDayOfMonth() < day
                        && event.getDateTime().getYear() < year)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return Optional.ofNullable(storage.getEvents().get(id));
    }

    @Override
    public Event save(Event entity) {
        return storage.getEvents().put(entity.getId(), entity);
    }

    @Override
    public void deleteById(Integer id) {
        storage.getEvents().remove(id);
    }

    @Override
    public Collection<Event> findAll() {
        return storage.getEvents().values();
    }
}
