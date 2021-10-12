package ru.vsu.service.impl;

import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.repository.EventRepository;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.InjectFromProperties;
import ru.vsu.service.EventService;
import ru.vsu.service.TimeComparisonOperation;
import ru.vsu.service.function.TetraFunction;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class EventServiceImpl implements EventService {

    @InjectByType
    private EventRepository repository;

    @InjectFromProperties
    private Map<TimeComparisonOperation, TetraFunction<Integer, Integer, Integer, List<EventType>, Collection<Event>>> findByDateFunctions;

    @Override
    public Event findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with id: " + id + "doesn't exist"));
    }

    @Override
    public Event create(Event entity) {
        if (repository.findById(entity.getId()).isPresent())
            throw new RuntimeException("Event with id: " + entity.getId() + "has already existed");

        return repository.save(entity);
    }

    @Override
    public Event update(Event entity) {
        if (repository.findById(entity.getId()).isEmpty())
            throw new RuntimeException("Event with id: " + entity.getId() + "hasn't existed");

        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<Event> findByTypes(List<EventType> types) {
        return repository.findByTypes(types);
    }

    @Override
    public Collection<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Event> findByYear(Integer year, List<EventType> types) {
        return repository.findByYear(year, types);
    }

    @Override
    public Collection<Event> findByMonth(Integer month, List<EventType> types) {
        return repository.findByMonth(month, types);
    }

    @Override
    public Collection<Event> findByMonthAndDay(Integer month, Integer day, List<EventType> types) {
        return repository.findByDay(month, day, types);
    }

    @Override
    public Collection<Event> findByDate(Integer year, Integer month, Integer day, List<EventType> types, TimeComparisonOperation operation) {
        return findByDateFunctions.get(operation).apply(year, month, day, types);
    }
}
