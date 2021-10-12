package ru.vsu.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Birthday  extends Event{

    private String present;

    public Birthday() {
        eventType = EventType.BIRTHDAY;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Birthday {\n" +
                "id = " + getId() +
                ",\n dateTime = " + getDateTime() +
                ",\n name = '" + getName() + '\'' +
                ",\n description = '" + getDescription() + "'," +
                "\n present = " + getPresent() +
                "\n}";
    }
}
