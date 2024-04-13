package net.zidargs.api;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.core.ResteasyDeploymentImpl;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.weld.environment.servlet.Listener;

import static io.undertow.Handlers.resource;

public class Server {
    public static void main(String[] args) {
        int port = 18080;

        for (String arg : args) {
            if (arg.startsWith("-port=")) {
                port = Integer.parseInt(arg.substring(6));
            }
        }

        UndertowJaxrsServer server = new UndertowJaxrsServer();

        ResteasyDeployment deployment = new ResteasyDeploymentImpl();
        deployment.setApplicationClass(ServerApplication.class.getName());

        deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment, "/");
        deploymentInfo.setClassLoader(Server.class.getClassLoader());
        deploymentInfo.setDeploymentName("Undertow + Resteasy example");
        deploymentInfo.setContextPath("/api");

        deploymentInfo.addListener(Servlets.listener(Listener.class));

        server.deploy(deploymentInfo);

        // for testing serve html file
        server.addResourcePrefixPath("/",
                resource(new ClassPathResourceManager(Server.class.getClassLoader()))
                        .addWelcomeFiles("index.html"));

        Undertow.Builder builder = Undertow.builder()
                .addHttpListener(port, "localhost");

        server.start(builder);

    }

}
