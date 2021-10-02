package planner.backend.reflection;

import java.util.Set;

public class Reflections {

    private String basePackage;
    private ClassScanner scanner;


    public Reflections(String basePackage) {
        this.basePackage = basePackage;
    }


    public <T> Set<Class<? extends T>> getSubclasses(Class<T> root) {
        return null;
    }
}
