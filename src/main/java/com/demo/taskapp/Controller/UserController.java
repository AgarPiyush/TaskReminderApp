package com.demo.taskapp.Controller;

import com.demo.taskapp.Dao.UserDoa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.demo.taskapp.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
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
    public Response addUser(String body){

        if(!isJSONValid(body)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Logger.getGlobal().info(body);
        try{
            JSONObject jsonObj = new JSONObject(body);
            userDoa.insertUser(jsonObj.getString("phoneNumber"), jsonObj.getString("userName"));
            return Response.status(Response.Status.CREATED).entity(jsonObj.toString()).build();
        } catch (JSONException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
