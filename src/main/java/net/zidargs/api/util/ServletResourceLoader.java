package net.zidargs.api.util;

import net.zidargs.api.resource.ServletResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Resolves all classes annotated with {@link ServletResource}
 */
public class ServletResourceLoader {

    /**
     * Resolve all classes annotated with {@link ServletResource} recursively and return them as a {@link Set}
     * using "net.creativeconduit.website" as root package name
     *
     * @return a {@link Set} of all classes annotated with {@link ServletResource}
     */
    public Set<Class<?>> loadServletResources() {
        String pacakgeName = this.getClass().getPackageName();
        return loadServletResources(pacakgeName.substring(0, pacakgeName.lastIndexOf('.')));
    }

    /**
     * Resolve all classes annotated with {@link ServletResource} recursively and return them as a {@link Set}
     *
     * @param packageName the root package name to search for annotated classes
     * @return a {@link Set} of all classes annotated with {@link ServletResource}
     */
    public Set<Class<?>> loadServletResources(String packageName) {
        Set<Class<?>> servletSet = resolveAllPackageClasses(packageName, "");
        servletSet.forEach(servlet -> System.out.println("Resolved servlet: " + servlet.getName()));
        return servletSet;
    }

    private Set<Class<?>> resolveAllPackageClasses(String packagePath, String packageName) {
        if (packageName.endsWith(".class")) {
            Class<?> clazz = getClassFromString(packageName, packagePath);
            return isAnnotatedWithServletResource(clazz) ? Set.of(clazz) : Collections.emptySet();
        }

        String subPackagePath = packageName.isEmpty() ? packagePath : packagePath + "." + packageName;
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(subPackagePath.replaceAll("[.]", "/"));
        if (stream == null) {
            return Collections.emptySet();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .map(entry -> this.resolveAllPackageClasses(subPackagePath, entry))
                .reduce(new HashSet<>(), this::classSetReducer);
    }

    private Set<Class<?>> classSetReducer(Set<Class<?>> resultClassSet, Set<Class<?>> currentClassSet) {
        resultClassSet.addAll(currentClassSet);
        return resultClassSet;
    }

    private Class<?> getClassFromString(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // ignore the exception
        }
        return null;
    }

    private boolean isAnnotatedWithServletResource(Class<?> clazz) {
        return clazz != null && clazz.getAnnotation(ServletResource.class) != null;
    }

}
