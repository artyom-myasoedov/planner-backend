package ru.vsu;

import ru.vsu.UI.PlannerLifeCycle;
import ru.vsu.dao.entity.Birthday;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.repository.EventRepository;
import ru.vsu.di.Application;
import ru.vsu.di.context.ApplicationContext;

import java.time.LocalDateTime;
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

        ApplicationContext context = Application.run(packagesToScan, new HashMap<>() {{
            put(Map.class, Set.of(HashMap.class));
        }}, "application.yaml", PlannerLifeCycle.class);

    }
}

