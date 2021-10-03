package planner.backend.di.configurator;

import planner.backend.di.context.ApplicationContext;

public interface BeanConfigurator {
    void configure(Object t, ApplicationContext context);

}
