package planner.backend.di;

import planner.backend.di.annotation.Component;
import planner.backend.di.annotation.PostConstruct;
import planner.backend.di.context.ApplicationContext;
import planner.backend.di.configurator.BeanConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanFactory {
    private final ApplicationContext context;
    private final List<BeanConfigurator> configurators = new ArrayList<>();

    public BeanFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanConfigurator> aClass : context.getConfig().getScanner().getSubTypesAnnotatedBy(BeanConfigurator.class, Component.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("exception when creating configurator " + aClass.getName(), e);
            }
        }
    }

    public <T> T createBean(Class<T> implClass) {

        T t;
        try {
            t = create(implClass);
        } catch (Exception e) {
            throw new RuntimeException("exception when creating bean " + implClass.getName(), e);
        }

        configure(t);

        invokeInit(implClass, t);

        return t;

    }

    private <T> void invokeInit(Class<T> implClass, T t) {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(t);
                } catch (Exception e) {
                    throw new RuntimeException("exception in PostConstruct method "
                            + implClass.getName() + "." + method.getName(), e);
                }
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
