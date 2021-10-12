package ru.vsu.UI.suppliers.impl;

import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
public class BirthdayCreationSupplier implements Supplier<Event> {

    @InjectByType
    private DefaultConsoleUI ui;


    @Override
    public Event get() {
        Birthday entity = new Birthday();
        ui.showMessage("Добавление события: День Рождения");
        entity.setId(getIntProperty("Введите Id для записи"));
        entity.setName(getStringProperty("Введите имя именинника"));
        entity.setPresent(getStringProperty("Введите подарок для именинника"));
        entity.setDescription(getStringProperty("Введите описание события"));
        entity.setDateTime(getDateTimeProperty("Введите дату и время Дня Рождения в формате YYYY-MM-dd HH:mm"));
        return entity;
    }

    private Integer getIntProperty(String message) {
        ui.showMessage(message);
        Integer res = null;
        try {
            res = Integer.parseInt(ui.getInput());
        } catch (Exception e) {
            ui.showException(new RuntimeException("Invalid input"));
            getIntProperty(message);
        }
        return res;
    }

    private LocalDateTime getDateTimeProperty(String message) {
        ui.showMessage(message);
        LocalDateTime res = null;
        try {
            String input = ui.getInput().replace(' ', 'T');
            res = LocalDateTime.parse(input);
        } catch (Exception e) {
            ui.showException(new RuntimeException("Invalid input"));
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
                throw new RuntimeException("String property can't be blank");
        } catch (Exception e) {
            ui.showException(e);
            getIntProperty(message);
        }
        return res;
    }
}
