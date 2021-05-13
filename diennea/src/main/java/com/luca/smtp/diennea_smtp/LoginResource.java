package com.luca.smtp.diennea_smtp;

import com.luca.smtp.diennea_smtp.util.JsonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Path("/login")
public class LoginResource {
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@FormParam("email")String email, @FormParam("password") String password) {
        JsonResponse response = new JsonResponse();
        if (dummyValidation(email,password)){
            response.setToken(getToken(email));
            return Response.ok(response).build();
        }
        response.setToken("");
        return Response.status(401).entity(response).build();
    }

    private boolean dummyValidation (String email, String password){
        FakeDBSingleton fakeDBSingleton = FakeDBSingleton.getInstance();
        return fakeDBSingleton.authenticate(email, password);
    }

    private String getToken(String email){
        FakeDBSingleton fakeDBSingleton = FakeDBSingleton.getInstance();
        return fakeDBSingleton.getToken(email);
    }

}
