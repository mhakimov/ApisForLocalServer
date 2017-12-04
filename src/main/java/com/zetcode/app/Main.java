package com.zetcode.app;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;


public class Main {

    public static final String BASE_URI;
    public static final String protocol;
    public static final Optional<String> host;
    public static final String path;
    public static final Optional<String> port;

    static{
        protocol = "https://";
        host = Optional.ofNullable(System.getenv("localhost"));
        port = Optional.ofNullable(System.getenv("PORT"));
        path = "myapp";
        BASE_URI = protocol + host.orElse("localhost") + ":" + port.orElse("8080") + "/" + path + "/";
    }
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.zetcode.apis");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        //server.shutdown();
    }
}
