package planner.backend.di.configuration;

import planner.backend.di.reflection.Reflections;

import java.util.Set;

public interface Configuration {

    <T> Set<Class<? extends T>> getImplClasses(Class<T> ifc);

    Reflections getScanner();

}
