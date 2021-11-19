package ru.vsu.UI.command.impl;

import ru.vsu.UI.command.Command;
import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.UI.creator.EntityCreator;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.InjectFromProperties;
import ru.vsu.service.EventService;

import java.util.Map;

@Component
public class AddCommand implements Command<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @InjectFromProperties
    private Map<EventType, EntityCreator<Event>> constructors;

    @Override
    public Boolean apply(EventType eventType) {
        try {
            Event entity = constructors.get(eventType).get();
            service.create(entity);
        } catch (NullPointerException | ClassCastException e) {
            ui.showException(e);
        }
        return true;
    }
}
