package planner.backend.context;

public interface Context {

    <T> Class<? extends T> getImplClass(Class<T> ifc);

}
