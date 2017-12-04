package com.zetcode.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("msg")
public class MyMessage {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {

        return "Salam donya!\n";
    }

//    @Path("yy")
//    @POST
//    @Produces("application/json")
//    public CalculationRequest getMessage2(CalculationRequest cr) {
//        cr.name += cr.name;
//        return cr;//"Salam!\n" + cr.name;
//    }
}
