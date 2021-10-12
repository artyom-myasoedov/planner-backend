package ru.vsu.UI.function.impl;

import ru.vsu.UI.impl.ConsoleMenuUI;
import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;

import java.util.function.Function;

@Component
public class ExitFunction implements Function<EventType, Boolean> {

    @InjectByType
    private ConsoleMenuUI ui;

    @Override
    public Boolean apply(EventType eventType) {
        ui.showShutDownMessage();
        return false;
    }
}
