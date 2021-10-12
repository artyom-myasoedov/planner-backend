package ru.vsu.UI.function.impl;

import ru.vsu.UI.EntityCollectionUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;
import ru.vsu.service.TimeComparisonOperation;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class GetAllByYearFunction implements Function<EventType, Boolean> {
    @InjectByType
    private EntityCollectionUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите год в формате YYYY");
        String input = ui.getInput();
        int year;
        try {
            year = Integer.parseInt(input);
            if (year < 1 || year > LocalDate.now().getYear() + 100) {
                throw new RuntimeException("Invalid year");
            }
            ui.showEntityCollection(service.findByYear(year, EventType.toList(eventType)));
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}