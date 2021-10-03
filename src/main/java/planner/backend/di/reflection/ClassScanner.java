package planner.backend.di.reflection;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ClassScanner {

    private static final char PKG_SEPARATOR = '.';

    private static final char DIR_SEPARATOR = '/';

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";


    public Set<Class<?>> findClasses(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL scannedUrl = ClassLoader.getSystemClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir;
        try {
            scannedDir = new File(scannedUrl.toURI());
        } catch (Exception e) {
            throw new RuntimeException("exception when converting URL to URI", e);
        }
        Set<Class<?>> classes = new HashSet<>();
        for (File file : Objects.requireNonNull(scannedDir.listFiles())) {
            classes.addAll(findClasses(file, scannedPackage));
        }
        return classes;
    }

    private Set<Class<?>> findClasses(File file, String scannedPackage) {
        Set<Class<?>> classes = new HashSet<>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                classes.addAll(findClasses(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("cannot get class from name " + className, e);
            }
        }
        return classes;
    }
}
