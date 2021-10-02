package planner.backend.configuration;

public interface Configuration {

    <T> Class<? extends T> getImplClass(Class<T> ifc);



}
