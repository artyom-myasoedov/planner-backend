package ru.vsu;

import ru.vsu.UI.PlannerLifeCycle;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.Event;
import ru.vsu.di.Application;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PlannerApplication {

    public static void main(String[] args) throws Throwable {
        String[] packagesToScan = {
                "ru.vsu",
                "ru.vsu.di",
                "ru.vsu.dao",
                "ru.vsu.service",
                "ru.vsu.UI",
        };
//        Application.run(packagesToScan, new HashMap<>() {{
//            put(Map.class, Set.of(HashMap.class));
//        }}, "application.yaml", PlannerLifeCycle.class);

    }
}

