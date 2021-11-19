package ru.vsu.UI.command.impl;

import ru.vsu.UI.EntityCollectionUI;
import ru.vsu.UI.command.Command;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.service.EventService;

@Component
public class GetAllCommand implements Command<EventType, Boolean> {
    @InjectByType
    private EntityCollectionUI ui;

    @InjectByType
    private EventService service;

    @Override
    public Boolean apply(EventType eventType) {
        try {
            ui.showEntityCollection(service.findAll());
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        return true;
    }
}