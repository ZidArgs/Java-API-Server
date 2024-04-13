package net.zidargs.api;

import jakarta.ws.rs.core.Application;
import net.zidargs.api.util.ServletResourceLoader;

import java.util.Set;

public class ServerApplication extends Application {

    private final ServletResourceLoader servletResourceLoader = new ServletResourceLoader();

    @Override
    public Set<Class<?>> getClasses() {
        return servletResourceLoader.loadServletResources();
    }

}
