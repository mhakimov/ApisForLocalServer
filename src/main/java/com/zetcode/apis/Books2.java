package com.zetcode.apis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zetcode.app.Helper;
import objects.Book;
import objects.BooksAddRequest;
import objects.BooksResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("books2")
public class Books2 {

    String location = "C:\\Users\\mhakimov\\ApisForLocalServer\\listOfBooks2.txt";
    Helper helper = new Helper();
    ObjectMapper mapper = new ObjectMapper();



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BooksResponse getMessage(@QueryParam("author") String author1, @QueryParam("name") String name1 ) throws IOException {

        BooksResponse toReturn = new BooksResponse();
//        List<Book> lb = helper.readJsonFile(location);
        List<Book> lb = mapper.readValue(new File(location), new TypeReference<List<Book>>() {});

        toReturn.books = helper.filterOutRequiredBooks(author1, name1, lb);
        return toReturn;
    }


    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public BooksResponse getMessage2(BooksAddRequest bar) throws IOException {
        BooksResponse toReturn = new BooksResponse();

        List<Book> lb = mapper.readValue(new File(location), new TypeReference<List<Book>>() {});
lb.add(bar.book);
        mapper.writeValue(new File(location), lb);
toReturn.books = lb;

        return toReturn;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public BooksResponse deleteBook(@QueryParam("author") String author1, @QueryParam("name") String name1 ) throws IOException {

        BooksResponse toReturn = new BooksResponse();
        List<Book> lb = mapper.readValue(new File(location), new TypeReference<List<Book>>() {});

        lb = helper.deleteRequiredBook(author1, name1, lb);

        mapper.writeValue(new File(location), lb);

        toReturn.books = lb;
        return toReturn;
    }
}
