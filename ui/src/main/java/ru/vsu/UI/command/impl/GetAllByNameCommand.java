package ru.vsu.UI.command.impl;

import ru.vsu.UI.EntityCollectionUI;
import ru.vsu.UI.command.Command;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;

@Component
public class GetAllByNameCommand implements Command<EventType, Boolean> {

    @InjectByType
    private EntityCollectionUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите имя:");
        String input = ui.getInput();
        try {
            ui.showEntityCollection(service.findByNameLike(input, EventType.toList(eventType)));
        } catch (Exception e) {
            ui.showException(e);
        }
        return true;
    }
}
