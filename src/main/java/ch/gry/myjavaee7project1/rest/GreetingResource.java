/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author yvesgross
 */
@Path("greeting/{name}")
public class GreetingResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GreetingResource
     */
    public GreetingResource() {
    }

    /**
     * Retrieves representation of an instance of ch.gry.myjavaee7project1.rest.GreetingResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getGreeting(@PathParam("name") String name) {
        //TODO return proper representation object
        // throw new UnsupportedOperationException();
        JsonObject jo = Json.createObjectBuilder().add("Hello", name).build();
        return jo.toString();
    }

    /**
     * PUT method for updating or creating an instance of GreetingResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}
