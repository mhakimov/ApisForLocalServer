package com.zetcode;


import objects.Book;
import objects.BooksAddRequest;
import objects.BooksResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Assert;
import org.junit.Test;
import java.io.*;


public class BooksTests extends BaseTest {
    HttpGet request;
    HttpResponse response;
    BooksResponse booksResponse = new BooksResponse();
    Utilities utilities = new Utilities();


    @Test
    public void GetBooksValid_01() throws IOException {

        request = new HttpGet(BASE_URI + "books2/");

        // request.addHeader("User-Agent", USER_AGENT);
        response = client.execute(request);

        booksResponse = utilities.convertHttpResponseBodyToBooksResponse(response);

        Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

        Assert.assertEquals(response.getLocale().getDisplayLanguage(), "English");
        Assert.assertEquals(response.getLocale().getISO3Language(), "eng");
        Assert.assertEquals(response.getLocale().getLanguage(), "en");

        Assert.assertEquals(response.getLocale().getCountry(), "GB");
        Assert.assertEquals(response.getLocale().getDisplayCountry(), "United Kingdom");
        Assert.assertEquals(response.getLocale().getISO3Country(), "GBR");

        Assert.assertEquals(response.getLocale().getDisplayName(), "English (United Kingdom)");

        Assert.assertEquals(response.getLocale().getDisplayScript(), "");
        Assert.assertEquals(response.getLocale().getScript(), "");

        Assert.assertEquals(response.getLocale().getDisplayVariant(), "");
        Assert.assertEquals(response.getLocale().getVariant(), "");

        Assert.assertTrue(booksResponse.books.size() > 5);
        Assert.assertTrue(booksResponse.books.stream().anyMatch(b -> b.author.equals("Homer") && b.name.equals("Iliad")));
    }

    @Test
    public void GetBooks2_02_OneBook() throws IOException {

        request = new HttpGet(BASE_URI + "books2/?author=Marat%20Hakimov");
        response = client.execute(request);
        response = client.execute(request);

        booksResponse = utilities.convertHttpResponseBodyToBooksResponse(response);

        Assert.assertTrue(booksResponse.books.size() == 1);
        Assert.assertTrue(booksResponse.books.stream().anyMatch(b -> b.author.equals("Marat Hakimov")));
    }


    @Test
    public void PostBooks2_01_Smoke() throws IOException {

        int booksCount = 0;
        Book b = new Book("Ak ziemassvetki mani!", "Ozolinsh");
        BooksAddRequest booksAddRequest = new BooksAddRequest(b);
        HttpPost post = utilities.createCompletePost(BASE_URI + "books2/add/", booksAddRequest);
        response = client.execute(post);

        try {
            booksResponse = utilities.convertHttpResponseBodyToBooksResponse(response);
            booksCount = booksResponse.books.size();
            Assert.assertTrue(booksResponse.books.stream().anyMatch(bo -> bo.author.equals(b.author) && bo.name.equals(b.name)));
        } finally {
            HttpDelete delete = new HttpDelete(BASE_URI + "books2?name=Ak%20ziemassvetki%20mani!&&author=" + b.author);
            HttpResponse newResponse = client.execute(delete);
            booksResponse = utilities.convertHttpResponseBodyToBooksResponse(newResponse);
        }

        int newBooksCount = booksResponse.books.size();
        Assert.assertTrue(booksResponse.books.stream().noneMatch(bo -> bo.author.equals(b.author) && bo.name.equals(b.name)));
        Assert.assertEquals(booksCount, newBooksCount + 1);
    }
}