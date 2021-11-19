package ru.vsu.UI.command.impl;

import ru.vsu.UI.command.Command;
import ru.vsu.UI.impl.ConsoleMenuUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;

@Component
public class ExitCommand implements Command<EventType, Boolean> {

    @InjectByType
    private ConsoleMenuUI ui;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showShutDownMessage();
        return false;
    }
}
