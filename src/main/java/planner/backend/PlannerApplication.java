package planner.backend;

import planner.backend.di.annotation.Component;
import planner.backend.di.context.Context;
import planner.backend.di.reflection.Reflections;

public class PlannerApplication {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("planner.backend");
        reflections.getSubTypesAnnotatedBy(Context.class, Component.class).forEach(System.out::println);
    }
}
