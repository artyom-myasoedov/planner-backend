package ru.vsu.UI.function.impl;

import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.InjectFromProperties;
import ru.vsu.service.EventService;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class AddFunction implements Function<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @InjectFromProperties
    private Map<EventType, Supplier<Event>> constructors;

    @Override
    public Boolean apply(EventType eventType) {
        try {
            Event entity = constructors.get(eventType).get();
            service.create(entity);
        } catch (Exception e) {
            ui.showException(e);
        }
        return true;
    }
}
