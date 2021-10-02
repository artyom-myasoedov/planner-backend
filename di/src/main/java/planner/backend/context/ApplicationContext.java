package planner.backend.context;

import planner.backend.ObjectFactory;
import planner.backend.configuration.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private Configuration config;

    public ApplicationContext(Configuration config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
//        if (cache.containsKey(type)) {
//            return (T) cache.get(type);
//        }
//
//        Class<? extends T> implClass = type;
//
//        if (type.isInterface()) {
//            implClass = config.getImplClass(type);
//        }
//        T t = factory.createObject(implClass);
//
//        if (implClass.isAnnotationPresent(Singleton.class)) {
//            cache.put(type, t);
//        }
//
//        return t;
        return null;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }
}
