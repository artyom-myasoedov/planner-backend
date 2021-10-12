package ru.vsu.UI.function.impl;

import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;

import java.util.function.Function;

@Component
public class GetByIdFunction implements Function<EventType, Boolean> {

    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите id события для просмотра\n");
        try {
            String input = ui.getInput();
            int numberInput = Integer.parseInt(input);
            ui.showMessage(service.findById(numberInput).toString());
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}
