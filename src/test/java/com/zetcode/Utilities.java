package com.zetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import objects.BooksAddRequest;
import objects.BooksResponse;
import objects.CalculationRequest;
import objects.CalculationResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import static com.zetcode.BooksTests.BASE_URI;

public class Utilities {

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.zetcode.apis");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/myapp/"), rc);
    }


    public BooksResponse convertJsonStringToBooksResponse(String str) throws IOException {

        return new ObjectMapper().readValue(str, BooksResponse.class);
    }


    public CalculationResponse convertJsonStringToCalcResponse(String str) throws IOException {

        return new ObjectMapper().readValue(str, CalculationResponse.class);
    }

    public HttpResponse returnBooks2HttpResponse(String urlParameters) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(BASE_URI +urlParameters);
        return client.execute(request);
    }

    public BooksResponse convertHttpResponseBodyToBooksResponse(HttpResponse httpResponse) throws IOException {
       BufferedReader rd = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent()));
       return convertJsonStringToBooksResponse(rd.readLine());
    }

    public CalculationResponse convertHttpResponseBodyToCalcResponse(HttpResponse httpResponse) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent()));
        return convertJsonStringToCalcResponse(rd.readLine());
    }

    public String convertBooksAddRequestToString(BooksAddRequest booksAddRequest){
        Gson gson = new Gson();
        return gson.toJson(booksAddRequest);
    }

    public String convertCalculationRequestToString(CalculationRequest calculationRequest){
        Gson gson = new Gson();
        return gson.toJson(calculationRequest);
    }

    public void setEntityForPost(BooksAddRequest booksAddRequest, HttpPost httpPost) throws UnsupportedEncodingException {
        String json = convertBooksAddRequestToString(booksAddRequest);
        httpPost.setEntity(new StringEntity(json));
    }

    public HttpPost createCompletePost(String url, BooksAddRequest booksAddRequest) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        setEntityForPost(booksAddRequest, post);

        return post;
    }

    public HttpPost createCalculationPost(String url, CalculationRequest calculationRequest) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        String json = convertCalculationRequestToString(calculationRequest);
        post.setEntity(new StringEntity(json));
        return post;
    }



}
