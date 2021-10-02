package planner.backend;

import planner.backend.reflection.ClassScanner;

import java.net.URISyntaxException;
import java.util.List;

public class PlannerApplication {

    public static void main(String[] args) throws URISyntaxException {
        List<Class<?>> classes = ClassScanner.find("planner.backend.annotation");
        classes.forEach(System.out::println);
    }
}
