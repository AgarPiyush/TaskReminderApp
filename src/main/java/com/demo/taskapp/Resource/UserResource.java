package com.demo.taskapp.Resource;

import com.demo.taskapp.Dao.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.demo.taskapp.Model.User;

/**
 * Created by piyush.agarwal on 23/12/16.
 */

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDao userDoa;

    public UserResource(){
    }

    public UserResource(UserDao userDoa){
        this.userDoa = userDoa;
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String phoneNumber) {
        if(userDoa.existsUser(phoneNumber)){
            User user = new User(userDoa.getUser(phoneNumber), phoneNumber);
            return Response.status(Response.Status.OK).entity(user).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    public Response addUser(User user){
        userDoa.insertUser(user.getPhoneNumber(), user.getUserName());
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}