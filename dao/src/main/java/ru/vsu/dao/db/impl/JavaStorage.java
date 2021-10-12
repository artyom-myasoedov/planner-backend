package ru.vsu.dao.db.impl;

import ru.vsu.dao.db.Storage;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.Meeting;
import ru.vsu.di.annotation.Component;
import ru.vsu.di.annotation.InjectByType;
import ru.vsu.di.annotation.PostConstruct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Map;

@Component
public class JavaStorage implements Storage {

    @InjectByType
    private Map<Integer, Event> events;

    @PostConstruct
    public void init() {
        Birthday birthday = new Birthday();
        birthday.setPresent("Present 1");
        birthday.setDescription("Test description 1");
        birthday.setId(1);
        birthday.setName("Artyom");
        birthday.setDateTime(LocalDateTime.of(2000, Month.OCTOBER, 1, 10, 0));


        events.put(1, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 2");
        birthday.setDescription("Test description 2");
        birthday.setId(2);
        birthday.setName("Peter");
        birthday.setDateTime(LocalDateTime.of(2010, Month.NOVEMBER, 3, 11, 0));

        events.put(2, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 3");
        birthday.setDescription("Test description 3");
        birthday.setId(3);
        birthday.setName("John");
        birthday.setDateTime(LocalDateTime.of(2003, Month.JANUARY, 5, 20, 0));

        events.put(3, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 4");
        birthday.setDescription("Test description 4");
        birthday.setId(4);
        birthday.setName("Vasya");
        birthday.setDateTime(LocalDateTime.of(2000, Month.NOVEMBER, 10, 15, 0));

        events.put(4, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 5");
        birthday.setDescription("Test description 5");
        birthday.setId(5);
        birthday.setName("Paul");
        birthday.setDateTime(LocalDateTime.of(2000, Month.OCTOBER, 1, 15, 0));

        events.put(5, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 6");
        birthday.setDescription("Test description 6");
        birthday.setId(6);
        birthday.setName("Artyom");
        birthday.setDateTime(LocalDateTime.of(2015, Month.DECEMBER, 5, 11, 0));

        events.put(6, birthday);
        birthday = new Birthday();
        birthday.setPresent("Present 7");
        birthday.setDescription("Test description 7");
        birthday.setId(7);
        birthday.setName("Peter");
        birthday.setDateTime(LocalDateTime.of(1990, Month.OCTOBER, 10, 0, 0));

        events.put(7, birthday);
        Meeting meeting = new Meeting();
        meeting.setDescription("Test description 17");
        meeting.setId(17);
        meeting.setName("Peter");
        meeting.setDateTime(LocalDateTime.of(2020, Month.OCTOBER, 10, 12, 0));

        events.put(17, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 16");
        meeting.setId(16);
        meeting.setName("Julia");
        meeting.setDateTime(LocalDateTime.of(2020, Month.OCTOBER, 3, 10, 30));

        events.put(16, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 15");
        meeting.setId(15);
        meeting.setName("Petter");
        meeting.setDateTime(LocalDateTime.of(2020, Month.JUNE, 3, 15, 0));
        events.put(15, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 14");
        meeting.setId(14);
        meeting.setName("Boris");
        meeting.setDateTime(LocalDateTime.of(2020, Month.OCTOBER, 9, 20, 0));

        events.put(14, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 13");
        meeting.setId(13);
        meeting.setName("Anna");
        meeting.setDateTime(LocalDateTime.of(2020, Month.NOVEMBER, 15, 12, 45));

        events.put(13, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 12");
        meeting.setId(12);
        meeting.setName("Stas");
        meeting.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 10, 16, 20));

        events.put(12, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 11");
        meeting.setId(11);
        meeting.setName("Stas");
        meeting.setDateTime(LocalDateTime.of(2021, Month.DECEMBER, 3, 10, 30));

        events.put(11, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 8");
        meeting.setId(8);
        meeting.setName("Igor");
        meeting.setDateTime(LocalDateTime.of(2022, Month.OCTOBER, 3, 10, 30));

        events.put(8, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 9");
        meeting.setId(9);
        meeting.setName("Julia");
        meeting.setDateTime(LocalDateTime.of(2021, Month.OCTOBER, 22, 10, 30));
        events.put(9, meeting);
        meeting = new Meeting();
        meeting.setDescription("Test description 10");
        meeting.setId(10);
        meeting.setName("Peter");
        meeting.setDateTime(LocalDateTime.of(2021, Month.DECEMBER, 3, 10, 30));
        events.put(10, meeting);
    }

    @Override
    public Map<Integer, Event> getEvents() {
        return events;
    }
}
