package ru.vsu.UI.command.impl;

import ru.vsu.UI.command.Command;
import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.InjectFromProperties;
import ru.vsu.service.EventService;

import java.util.Map;

@Component
public class EditCommand implements Command<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @InjectFromProperties
    private Map<EventType, Command<Event, Event>> editors;

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
        } catch (NumberFormatException | NullPointerException e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}
