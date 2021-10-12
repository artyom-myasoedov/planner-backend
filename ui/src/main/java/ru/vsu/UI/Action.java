package ru.vsu.UI;

import java.util.Optional;

public enum Action {
    ADD(0),
    EDIT(1),
    REMOVE(2),
    GET(3),
    GET_ALL(4),
    GET_BY_YEAR(5),
    GET_BY_MONTH(6),
    GET_BY_DATE(7),
    CHANGE_CURRENT_EVENT_TYPE(8),
    EXIT(9);

    private final Integer number;

    Action(Integer number) {
        this.number = number;
    }


    public static Optional<Action> fromInteger(Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (var value : Action.values()) {
            if (value.number.equals(id)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }
}
