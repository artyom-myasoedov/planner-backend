package ru.vsu.service.function.impl;

import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.repository.EventRepository;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.function.TetraFunction;

import java.util.Collection;
import java.util.List;

@Component
public class FindByDateAfterFunction implements TetraFunction<Integer, Integer, Integer, List<EventType>, Collection<Event>> {

    @InjectByType
    private EventRepository repository;

    @Override
    public Collection<Event> apply(Integer year, Integer month, Integer day, List<EventType> eventTypes) {
        return repository.findAfterDay(year, month, day, eventTypes);
    }
}
