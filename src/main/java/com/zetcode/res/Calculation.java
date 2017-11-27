package com.zetcode.res;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("calc")
public class Calculation {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationRequest getMessage2(CalculationRequest cr) {
cr.name += cr.name;
        return cr;//"Salam!\n" + cr.name;
    }
}
