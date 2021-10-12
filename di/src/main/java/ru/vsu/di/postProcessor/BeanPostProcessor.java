package ru.vsu.di.postProcessor;

import ru.vsu.di.context.ApplicationContext;

public interface BeanPostProcessor {
    void configure(Object t, ApplicationContext context);

}
