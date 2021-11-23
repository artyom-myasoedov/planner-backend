package ru.vsu.dao.repository.impl;

import ru.vsu.dao.db.ConnectionManager;
import ru.vsu.dao.db.SubClassSQLMapper;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.entity.Meeting;
import ru.vsu.dao.persistence.Condition;
import ru.vsu.dao.persistence.Extractor;
import ru.vsu.dao.persistence.ExtractorImpl;
import ru.vsu.dao.repository.EventRepository;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class EventRepositoryImpl implements EventRepository {

    @InjectByType
    private ConnectionManager connectionManager;

    private Extractor extractor;


    @PostConstruct
    public void init() {
        extractor = new ExtractorImpl("planner.events", new SubClassSQLMapper("date_time",
                Event.class,
                Map.of(0, Birthday.class, 1, Meeting.class),
                Map.of(),
                Map.of()
        ));
    }

    @Override
    public Collection<Event> findByEventTypeContains(List<EventType> types) {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(),
                List.of(new Condition("event_type", types, "ContainsIn")));
    }

    @Override
    public Collection<Event> findByYearAndByEventTypeContains(Integer year, List<EventType> types) {
        return null;
    }

    @Override
    public Collection<Event> findByMonthAndEventTypeContains(Integer month, List<EventType> types) {
        return null;
    }

    @Override
    public Collection<Event> findByNameLikeAndEventTypeContains(String name, List<EventType> types) {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(),
                List.of(new Condition("opponent_name", name, "Like"),
                        new Condition("event_type", types, "ContainsIn")));
    }

    @Override
    public Collection<Event> findByDateTimeAndEventTypeContains(LocalDateTime dateTime, List<EventType> types) {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(), List.of(
                new Condition("date_time", dateTime, "Equal"),
                new Condition("event_type", types, "ContainsIn")));
    }

    @Override
    public Collection<Event> findByDateTimeGreaterThanAndEventTypeContains(LocalDateTime dateTime, List<EventType> types) {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(), List.of(
                new Condition("date_time", dateTime, "GreaterThan"),
                new Condition("event_type", types, "ContainsIn")));
    }

    @Override
    public Collection<Event> findByDateTimeLessThanAndEventTypeContains(LocalDateTime dateTime, List<EventType> types) {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(), List.of(
                new Condition("date_time", dateTime, "LessThan"),
                new Condition("event_type", types, "ContainsIn")));
    }

    @Override
    public Optional<Event> findById(Integer id) {
        List<?> retVal = (List<?>) extractor.extract(connectionManager.getConnection(),
                List.of(new Condition("event_id", id, "Equal")));
        if (retVal.isEmpty()) {
            return Optional.empty();
        }
        return (Optional<Event>) Optional.ofNullable(retVal.get(0));
    }

    @Override
    public void add(Event entity) {

    }

    @Override
    public void update(Event entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Collection<Event> findAll() {
        return (Collection<Event>) extractor.extract(connectionManager.getConnection(), Collections.emptyList());
    }
}
