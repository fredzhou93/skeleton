package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
    public class NetIDController {

    @Path("/netid")
    @GET
    public String getID() { return "yz2352"; }
}
