package com.zetcode;


import objects.CalculationRequest;
import objects.CalculationResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class CalculationTests extends BaseTest
{
    HttpResponse response;
    CalculationResponse calculationResponse;
    Utilities utilities = new Utilities();
    String url = "http://localhost:8080/myapp/calc";

    @Test
    public void PostCalculation_01_Addition() throws IOException {

        CalculationRequest request = new CalculationRequest(5, 8, "+");
               HttpPost post = utilities.createCalculationPost(url, request);
response = client.execute(post);
calculationResponse = utilities.convertHttpResponseBodyToCalcResponse(response);
       Assert.assertTrue(calculationResponse.success);
        Assert.assertEquals(calculationResponse.result, 13, 0);
    }


    @Test
    public void PostCalculation_02_Subtraction() throws IOException {

        CalculationRequest request = new CalculationRequest(87, 79, "-");
        HttpPost post = utilities.createCalculationPost(url, request);
        response = client.execute(post);
        calculationResponse = utilities.convertHttpResponseBodyToCalcResponse(response);
        Assert.assertTrue(calculationResponse.success);
        Assert.assertEquals(calculationResponse.result, 8, 0);
    }


    @Test
    public void PostCalculation_03_Multiplication() throws IOException {

        CalculationRequest request = new CalculationRequest(999, -5.5, "*");
        HttpPost post = utilities.createCalculationPost(url, request);
        response = client.execute(post);
        calculationResponse = utilities.convertHttpResponseBodyToCalcResponse(response);
        Assert.assertTrue(calculationResponse.success);
        Assert.assertEquals(calculationResponse.result, 999*-5.5, 0);
    }


    @Test
    public void PostCalculation_04_Division() throws IOException {

        CalculationRequest request = new CalculationRequest(-9, 0, "/");
        HttpPost post = utilities.createCalculationPost(url, request);
        response = client.execute(post);
        calculationResponse = utilities.convertHttpResponseBodyToCalcResponse(response);
        Assert.assertTrue(calculationResponse.success);
        Assert.assertEquals(calculationResponse.result, Double.NEGATIVE_INFINITY, 0);
    }


}
