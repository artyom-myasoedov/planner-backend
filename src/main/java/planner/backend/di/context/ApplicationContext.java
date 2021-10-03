package planner.backend.di.context;

import planner.backend.di.BeanFactory;
import planner.backend.di.annotation.Component;
import planner.backend.di.annotation.parameter.Scope;
import planner.backend.di.configuration.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ApplicationContext implements Context {
    private BeanFactory factory;
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();
    private final Configuration config;

    public ApplicationContext(Configuration config) {
        this.config = config;
    }

    public <T> T getBean(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            final Set<Class<? extends T>> implClasses = config.getImplClasses(type);
            if (implClasses.size() != 1)
                throw new RuntimeException("interface " + implClass.getName() + " has 0 or more than 1 implementations");

            implClass = implClasses.iterator().next();
        }
        final T t = factory.createBean(implClass);

        if (implClass.isAnnotationPresent(Component.class)) {
            if (implClass.getAnnotation(Component.class).scope().equals(Scope.SINGLETON))
                cache.put(type, t);
        } else {
            throw new RuntimeException("exception when creating bean, " + implClass.getName() + " is not a @Component");
        }

        return t;
    }

    @Override
    public <T> Collection<? extends T> getBeans(final Class<T> ifc) {
        if (ifc.isInterface()) {
            final Set<Class<? extends T>> implClasses = config.getImplClasses(ifc);
            return implClasses.stream()
                    .map(aClass -> factory.createBean(aClass))
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(factory.createBean(ifc));
    }

    public Configuration getConfig() {
        return config;
    }

    public Configuration getScanner() {
        return config;
    }

    public void setFactory(BeanFactory factory) {
        this.factory = factory;
    }

}
