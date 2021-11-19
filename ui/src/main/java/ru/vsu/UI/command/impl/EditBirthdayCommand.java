package ru.vsu.UI.command.impl;

import ru.vsu.UI.command.Command;
import ru.vsu.UI.impl.DefaultConsoleUI;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Component
public class EditBirthdayCommand implements Command<Event, Event> {
    @InjectByType
    private DefaultConsoleUI ui;

    @InjectByType
    private Map<Integer, Command<Event, Boolean>> editCommands;

    @PostConstruct
    public void init() {
        editCommands.put(0, (event -> {
            ui.showMessage("Введите дату и время рождения в формате YYYY-MM-dd HH:mm");
            try {
                String input = ui.getInput();
                LocalDateTime ldt = LocalDateTime.parse(input.replace(' ', 'T'));
                event.setDateTime(ldt);
            } catch (DateTimeParseException | NullPointerException e) {
                ui.showException(e);
                editCommands.get(0).apply(event);
            }
            return true;
        }));
        editCommands.put(1, (event -> {
            ui.showMessage("Введите имя именинника");
            try {
                String input = ui.getInput();
                event.setName(input);
            } catch (Exception e) {
                ui.showException(e);
                editCommands.get(1).apply(event);
            }
            return true;
        }));
        editCommands.put(2, (event -> {
            ui.showMessage("Введите описание дня рождения");
            try {
                String input = ui.getInput();
                event.setDescription(input);
            } catch (Exception e) {
                ui.showException(e);
                editCommands.get(2).apply(event);
            }
            return true;
        }));
        editCommands.put(3, (event -> {
            ui.showMessage("Введите подарок на День Рождения");
            try {
                String input = ui.getInput();
                ((Birthday) event).setPresent(input);
            } catch (ClassCastException e) {
                ui.showException(e);
                editCommands.get(3).apply(event);
            }
            return true;
        }));
        editCommands.put(4, (event -> false));
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
                isEditing = editCommands.get(numberInput).apply(event);
            } catch (NumberFormatException | NullPointerException e) {
                ui.showException(e);
            }
        }
        return event;
    }
}
