package planner.backend.di.configurator;

import planner.backend.di.annotation.Component;
import planner.backend.di.annotation.Inject;
import planner.backend.di.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

@Component
public class InjectAnnotationBeanConfigurator implements BeanConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object object;
                if (Collection.class.isAssignableFrom(field.getType())) {
                    final ParameterizedType type = (ParameterizedType) field.getGenericType();
                    final Type actualTypeArgument = type.getActualTypeArguments()[0];
                    object = context.getBeans((Class) actualTypeArgument);
                } else {
                    object = context.getBean(field.getType());
                }
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("no access to the field " + t.getClass().getName() + "." + field.getName(), e);
                }
            }
        }
    }
}
