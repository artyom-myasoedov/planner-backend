package ru.vsu.UI.function.impl;

import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;

@Component
public class EditBirthdayFunction implements Function<Event, Event> {
    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private Map<Integer, Function<Event, Boolean>> editFunctions;

    @PostConstruct
    public void init() {
        editFunctions.put(0, (event -> {
            ui.showMessage("Введите дату и время рождения в формате YYYY-MM-dd HH:mm");
            try {
                String input = ui.getInput();
                LocalDateTime ldt = LocalDateTime.parse(input.replace(' ', 'T'));
                event.setDateTime(ldt);
            } catch (Exception e) {
                ui.showException(e);
                editFunctions.get(0).apply(event);
            }
            return true;
        }));
        editFunctions.put(1, (event -> {
            ui.showMessage("Введите имя именинника");
            try {
                String input = ui.getInput();
                event.setName(input);
            } catch (Exception e) {
                ui.showException(e);
                editFunctions.get(1).apply(event);
            }
            return true;
        }));
        editFunctions.put(2, (event -> {
            ui.showMessage("Введите описание дня рождения");
            try {
                String input = ui.getInput();
                event.setDescription(input);
            } catch (Exception e) {
                ui.showException(e);
                editFunctions.get(2).apply(event);
            }
            return true;
        }));
        editFunctions.put(3, (event -> {
            ui.showMessage("Введите подарок на День Рождения");
            try {
                String input = ui.getInput();
                ((Birthday) event).setPresent(input);
            } catch (Exception e) {
                ui.showException(e);
                editFunctions.get(3).apply(event);
            }
            return true;
        }));
        editFunctions.put(4, (event -> false));
    }

    private final String EDIT_MENU = "Редактирование события: День Рожения\n" +
            "0 - изменить дату и время рождения\n" +
            "1 - изменить именинника\n" +
            "2 - изменить описание\n" +
            "3 - изменить подарок\n" +
            "4 - главное меню";

    @Override
    public Event apply(Event event) {
        String input;
        int numberInput;
        boolean isEditing = true;
        while (isEditing) {
            ui.showMessage(EDIT_MENU);
            ui.showMessage(event.toString());
            try {
                input = ui.getInput();
                numberInput = Integer.parseInt(input);
                isEditing = editFunctions.get(numberInput).apply(event);
            } catch (Exception e) {
                ui.showException(e);
            }
        }
        return event;
    }
}
