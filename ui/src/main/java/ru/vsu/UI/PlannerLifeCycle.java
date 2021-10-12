package ru.vsu.UI;

import ru.vsu.dao.entity.EventType;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.InjectFromProperties;
import ru.vsu.di.annotation.PostConstruct;

import java.util.Map;
import java.util.function.Function;

@Component
public class PlannerLifeCycle {

    private boolean isRunning;

    private EventType currEventType;

    @InjectByType
    private Map<EventType, String> menuMessages;

    @InjectByType
    private MenuUI ui;

    @InjectFromProperties
    private Map<Action, Function<EventType, Boolean>> mainMenuFunctions;


    @PostConstruct
    public void init() {
        isRunning = false;
        currEventType = EventType.ANY;

        menuMessages.put(EventType.BIRTHDAY, "-------------- Текущее событие: День Рождения -----------------\n" +
                " 0 - добавить день рождения\n" +
                " 1 - редактировать день рождения\n" +
                " 2 - удалить день рождения\n" +
                " 3 - информация о дне рождения\n" +
                " 4 - информация о всех днях рождения\n" +
                " 5 - информация о всех днях рождения за выбранный год\n" +
                " 6 - информация о всех днях рождения за выбранный месяц\n" +
                " 7 - информация о днях рождения по дате\n" +
                " 8 - изменить текущий тип события\n" +
                " 9 - выход");
        menuMessages.put(EventType.MEETING, "-------------- Текущее событие: Важная встреча -----------------\n" +
                " 0 - добавить важную встречу\n" +
                " 1 - редактировать важную встречу\n" +
                " 2 - удалить важную встречу\n" +
                " 3 - информация о важной встрече\n" +
                " 4 - информация о всех важных встречах\n" +
                " 5 - информация о всех важных встречах за выбранный год\n" +
                " 6 - информация о всех важных встречах за выбранный месяц\n" +
                " 7 - информация о важных встречах по дате\n" +
                " 8 - изменить текущий тип события\n" +
                " 9 - выход");
        menuMessages.put(EventType.ANY, "-------------- Текущее событие: Любое событие -----------------\n" +
                " 0 - удалить событие\n" +
                " 1 - информация о событии\n" +
                " 2 - информация о всех событиях\n" +
                " 3 - информация о всех событиях за выбранный год\n" +
                " 4 - информация о всех событиях за выбранный месяц\n" +
                " 5 - информация о событиях по дате\n" +
                " 6 - изменить текущий тип события\n" +
                " 7 - выход");

        ui.setMenuMessage(menuMessages.get(currEventType));

        mainMenuFunctions.put(Action.CHANGE_CURRENT_EVENT_TYPE, new ChangeEventTypeFunction());
    }

    public void start() {
        isRunning = true;
        String input;
        int numberInput;
        while (isRunning) {
            try {
                ui.showMenu();
                input = ui.getInput();

                try {
                    numberInput = Integer.parseInt(input);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid input");
                }

                if (currEventType.equals(EventType.ANY)) if (numberInput > 7)
                    throw new RuntimeException("Invalid operation");
                else numberInput += 2;

                Action action = Action.fromInteger(numberInput)
                        .orElseThrow(() -> new RuntimeException("There isn't such action"));
                isRunning = mainMenuFunctions.get(action).apply(currEventType);
            } catch (Exception e) {
                ui.showException(e);
            }
        }
    }

    @Component
    public class ChangeEventTypeFunction implements Function<EventType, Boolean> {

        private final String CHANGE_EVENT_TYPE_MESSAGE =
                "---------- Выберите тип события ----------\n" +
                        " 0 - День Рождения\n" +
                        " 1 - Важная встреча\n" +
                        " 2 - Любое событие\n";

        @Override
        public Boolean apply(EventType eventType) {
            String input;
            int numberInput;
            ui.showMessage(CHANGE_EVENT_TYPE_MESSAGE);
            try {
                input = ui.getInput();

                try {
                    numberInput = Integer.parseInt(input);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid input");
                }

                currEventType = EventType.fromInteger(numberInput)
                        .orElseThrow(() -> new RuntimeException("There isn't such EventType"));
                ui.setMenuMessage(menuMessages.get(currEventType));
            } catch (Exception e) {
                ui.showException(e);
                apply(eventType);
            }
            return true;
        }
    }
}
