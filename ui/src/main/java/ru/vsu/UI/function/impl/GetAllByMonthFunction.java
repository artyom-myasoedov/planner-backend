package ru.vsu.UI.function.impl;

import ru.vsu.UI.EntityCollectionUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;

import java.util.function.Function;

@Component
public class GetAllByMonthFunction implements Function<EventType, Boolean> {

    @InjectByType
    private EntityCollectionUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите номер месяца");
        String input = ui.getInput();
        int month;
        try {
            month = Integer.parseInt(input);
            if (month < 1 || month > 12) {
                throw new RuntimeException("Invalid month");
            }
            ui.showEntityCollection(service.findByMonth(month - 1, EventType.toList(eventType)));
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}
