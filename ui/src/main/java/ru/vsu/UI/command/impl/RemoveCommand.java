package ru.vsu.UI.command.impl;

import ru.vsu.UI.command.Command;
import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;

@Component
public class RemoveCommand implements Command<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите id события для удаления\n");
        try {
            String input = ui.getInput();
            int numberInput = Integer.parseInt(input);
            service.deleteById(numberInput);
            ui.showMessage("Операция завершена\n");
        } catch (NumberFormatException e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}