package planner.backend.di.reflection;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Reflections {

    private final String basePackage;
    private final ClassScanner scanner;
    private final Set<Class<?>> availableClasses;


    public Reflections(String basePackage) {
        this.basePackage = basePackage;
        scanner = new ClassScanner();
        availableClasses = scanner.findClasses(basePackage);
    }

    public String getBasePackage() {
        return basePackage;
    }

    public ClassScanner getScanner() {
        return scanner;
    }

    public Set<Class<?>> getAvailableClasses() {
        return availableClasses;
    }

    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> root) {
        return availableClasses.stream()
                .filter(root::isAssignableFrom)
                .filter(aClass -> !aClass.isInterface())
                .map(aClass -> (Class<? extends T>) aClass)
                .collect(Collectors.toSet());
    }

    public <T> Set<Class<? extends T>> getSubTypesAnnotatedBy(Class<T> root, Class<? extends Annotation> annotation) {
        return getSubTypesOf(root).stream()
                .filter(aClass -> aClass.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }
}
