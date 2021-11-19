package ru.vsu.di;

import ru.vsu.di.configuration.JavaConfiguration;
import ru.vsu.di.context.ApplicationContext;
import ru.vsu.di.parser.impl.YAMLParser;

import java.util.Map;
import java.util.Set;

public class Application {
    public static ApplicationContext run(String[] packagesToScan, Map<Class<?>, Set<Class<?>>> ifc2ImplClass, String properties, Class<? extends ApplicationRunner> classAppRunner) {
        JavaConfiguration config = new JavaConfiguration(packagesToScan, ifc2ImplClass, new YAMLParser(), properties);
        ApplicationContext context = new ApplicationContext(config);
        BeanFactory objectFactory = new BeanFactory(context);
        context.setFactory(objectFactory);
        context.getBean(classAppRunner).start();
        return context;
    }

}
