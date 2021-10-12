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

@Component
public class EditFunction implements Function<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @InjectFromProperties
    private Map<EventType, Function<Event, Event>> editors;

    @Override
    public Boolean apply(EventType eventType) {
        try {
            ui.showMessage("Введите Id события, которое хотите отредактировать\n");
            String input = ui.getInput();
            int numberInput = Integer.parseInt(input);
            Event entity = service.findById(numberInput);
            entity = editors.get(entity.getEventType()).apply(entity);
            service.update(entity);
            ui.showMessage("Операция завершена\n");
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}
