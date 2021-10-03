package planner.backend.di.context;

import java.util.Collection;

public interface Context {

    <T> T getBean(Class<T> ifc);

    <T> Collection<? extends T> getBeans(Class<T> ifc);

}
