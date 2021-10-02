package planner.backend;

import planner.backend.context.ApplicationContext;
import planner.backend.configurator.Configurator;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private List<Configurator> configurators = new ArrayList<>();

    public ObjectFactory(ApplicationContext context) {
        this.context = context;
//        for (Class<? extends Configurator> aClass : context.getConfig().getScanner().getSubTypesOf(Configurator.class)) {
//            try {
//                configurators.add(aClass.getDeclaredConstructor().newInstance());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
