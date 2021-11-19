package ru.vsu.UI.creator.impl;

import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.UI.creator.EntityCreator;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.Meeting;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class MeetingConsoleCreator implements EntityCreator<Event> {

    @InjectByType
    private DefaultConsoleUI ui;


    @Override
    public Event get() {
        Meeting entity = new Meeting();

        ui.showMessage("Добавление события: Важная встреча");
        entity.setId(getIntProperty("Введите Id для записи"));
        entity.setName(getStringProperty("Введите имя собеседника"));
        entity.setDescription(getStringProperty("Введите описание события"));
        entity.setDateTime(getDateTimeProperty("Введите дату и время события в формате YYYY-MM-dd HH:mm"));

        return entity;
    }

    private Integer getIntProperty(String message) {
        ui.showMessage(message);
        Integer res = null;
        String input = null;
        try {
            input = ui.getInput();
            res = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ui.showException(new RuntimeException("Invalid input: " + input, e));
            getIntProperty(message);
        }
        return res;
    }

    private LocalDateTime getDateTimeProperty(String message) {
        ui.showMessage(message);
        LocalDateTime res = null;
        String input = null;
        try {
            input = ui.getInput().replace(' ', 'T');
            res = LocalDateTime.parse(input);
        } catch (DateTimeParseException e) {
            ui.showException(new RuntimeException("Invalid input: " + input, e));
            getIntProperty(message);
        }
        return res;
    }

    private String getStringProperty(String message) {
        ui.showMessage(message);
        String res = "";
        try {
            res = ui.getInput();
            if (res.isBlank())
                throw new IllegalArgumentException("String property can't be blank");
        } catch (IllegalArgumentException e) {
            ui.showException(e);
            getIntProperty(message);
        }
        return res;
    }
}

