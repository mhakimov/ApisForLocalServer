package com.zetcode.apis;

import objects.CalculationRequest;
import objects.CalculationResponse;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("calc")
public class Calculation {

//@Path("yy")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResponse getMessage2(CalculationRequest cr) {
CalculationResponse toReturn = new CalculationResponse();
        switch (cr.operation){
            case "+":
                toReturn.result = cr.number1 + cr.number2;
                break;
            case "-":
                toReturn.result = cr.number1 - cr.number2;
                break;
            case "*":
                toReturn.result = cr.number1 * cr.number2;
                break;
            case "/":
                toReturn.result = cr.number1 / cr.number2;
                break;
                default:
                    toReturn.success = false;
                    toReturn.error = "ERROR! Operation is not recognised";

        }
        return toReturn;
    }
}
