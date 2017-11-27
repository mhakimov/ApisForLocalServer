package com.zetcode.res;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @Path("1")
    @POST
  //  @Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)

    public String getMessage2(int number ) {

        return "Salam !\n" + number;
    }
}
