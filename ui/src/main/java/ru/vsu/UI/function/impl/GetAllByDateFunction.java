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
public class GetAllByDateFunction implements Function<EventType, Boolean> {

    @InjectByType
    private EntityCollectionUI ui;

    @InjectByType
    private EventService service;

    private final String OPERATION_MENU = "Выберите период, за который хотите посмотреть события\n" +
            "0 - В ту же дату (Equal)\n" +
            "1 - До введённой даты (Before)\n" +
            "2 - После введённой даты (After)\n";

    @Override
    public Boolean apply(EventType eventType) {
        ui.showMessage("Введите дату в формате YYYY-MM-DD");
        String input = ui.getInput();
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(input);
        } catch (Exception e) {
            ui.showException(e);
            apply(eventType);
        }
        var isChoosingOperation = true;
        int numberInput;
        while (isChoosingOperation) {
            ui.showMessage(OPERATION_MENU);
            input = ui.getInput();
            try {
                numberInput = Integer.parseInt(input);
                TimeComparisonOperation operation = TimeComparisonOperation.fromInteger(numberInput)
                        .orElseThrow(() -> new RuntimeException("There isn't such operation"));
                isChoosingOperation = false;
                ui.showEntityCollection(service.findByDate(localDate.getYear(),
                        localDate.getMonthValue(),
                        localDate.getDayOfMonth(),
                        EventType.toList(eventType),
                        operation));
                break;
            } catch (Exception e) {
                ui.showException(e);
            }
        }
        return true;
    }
}