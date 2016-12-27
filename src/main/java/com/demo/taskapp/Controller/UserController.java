package com.demo.taskapp.Controller;

import com.demo.taskapp.Dao.UserDoa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.demo.taskapp.Models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by piyush.agarwal on 23/12/16.
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserDoa userDoa;

    public  UserController(){
    }

    public UserController(UserDoa userDoa){
        this.userDoa = userDoa;
    }

    @GET
    public Response getUser(@QueryParam("number") String phoneNumber) {
        if(userDoa.existsUser(phoneNumber)){
            Map<String, String> hmap = new HashMap<String, String>();
            hmap.put(userDoa.getUser(phoneNumber), phoneNumber);
            return Response.status(Response.Status.OK).entity(hmap).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/add/")
    @Consumes({"application/json"})
    public Response addUser(User user){
        userDoa.insertUser(user.getPhoneNumber(), user.getUserName());
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}