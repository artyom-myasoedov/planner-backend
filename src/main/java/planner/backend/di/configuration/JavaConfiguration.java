package planner.backend.di.configuration;

import planner.backend.di.annotation.Component;
import planner.backend.di.reflection.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JavaConfiguration implements Configuration {

    private final Reflections scanner;
    private final Map<Class<?>, Set<Class<?>>> ifc2ImplClasses = new ConcurrentHashMap<>();

    public JavaConfiguration(Reflections scanner) {
        this.scanner = scanner;
    }

    @Override
    public <T> Set<Class<? extends T>> getImplClasses(Class<T> ifc) {
        return ifc2ImplClasses.computeIfAbsent(ifc, aClasses -> scanner.getSubTypesAnnotatedBy(ifc, Component.class).stream()
                        .map(aClass -> (Class<?>) aClass)
                        .collect(Collectors.toSet()))
                .stream()
                .map(item -> (Class<? extends T>) item)
                .collect(Collectors.toSet());
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
