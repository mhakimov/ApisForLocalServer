package com.zetcode.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zetcode.app.Helper;
import objects.Book;
import objects.BooksAddRequest;
import objects.BooksResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Path("books")
public class Books {

    String location = "C:\\Users\\mhakimov\\ApisForLocalServer\\listOfBooks3.txt";
    Helper helper = new Helper();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@QueryParam("author") String author1 ) throws IOException {

        return helper.readTextFile(location);
    }


    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public BooksResponse getMessage2(BooksAddRequest bar) throws IOException {


        BooksResponse toReturn = new BooksResponse();

        helper.addTextToEndOfFile(location, "\n" + bar.book.name + " / " + bar.book.author + ";");
        String[] books =helper.readTextFile(location).split("\n");
        toReturn.books = helper.transformStringsIntoListOfBooks(books);

        return toReturn;
    }
}
