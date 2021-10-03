package planner.backend.di.annotation;

import planner.backend.di.annotation.parameter.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    Scope scope() default Scope.SINGLETON;
}
