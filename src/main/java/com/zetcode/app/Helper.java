package com.zetcode.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Book;
import objects.BooksResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public String readTextFile(String fileLocation) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        StringBuilder sb = new StringBuilder();

        try {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        finally {
            br.close();
        }
        return  sb.toString();
    }


    public void addTextToEndOfFile(String fileLocation, String textToAdd) throws IOException {

        Files.write(Paths.get(fileLocation),
                (textToAdd).getBytes(), StandardOpenOption.APPEND);

    }


    public List<Book> transformStringsIntoListOfBooks(String[] strings){
        List<Book> books = new ArrayList<>();
        for (String b :strings
                ) {
            Book boo = new Book();

            String[] props = b.split(" / ");
            boo.name = props[0];
            boo.author = props[1].replaceFirst(";\r", "");
            books.add(boo);
        }
        return books;
    }


    public List<Book> filterOutRequiredBooks(String author, String name, List<Book> books){

        if(author != null && name == null)
            return books.stream().filter(x -> x.author.equals(author)).collect(Collectors.toList());

        else if(author == null && name != null)
            return books.stream().filter(x -> x.name.equals(name)).collect(Collectors.toList());

        else if(author != null && name != null)
            return books.stream().filter(x-> x.author.equals(author) && x.name.equals(name)).collect(Collectors.toList());

        else
            return books;
    }

    public List<Book> readJsonFile(String fileLocation) throws IOException {

        return new ObjectMapper().readValue(new File(fileLocation), new TypeReference<List<Book>>() {});
    }


    public List<Book> deleteRequiredBook(String author, String name, List<Book> books){

        books.removeAll
                (books.stream().filter(x -> x.author.equals(author) && x.name.equals(name)).collect(Collectors.toList()));

        return books;


    }
}
