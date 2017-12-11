package com.zetcode;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.net.URI;

public class BaseTest {

    static HttpServer server;
    static String BASE_URI = "http://localhost:8080/myapp/";
    static HttpClient client;


    @BeforeClass
    public static void OneTimeSetup(){
        final ResourceConfig rc = new ResourceConfig().packages("com.zetcode.apis");
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/myapp/"), rc);

        //server = Utilities.startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        client = HttpClientBuilder.create().build();

    }


    @AfterClass
    public static void OneTimeTearDown(){

        server.shutdown();
    }
}
