package com.demo.taskapp.Controller;

import com.demo.taskapp.Dao.UserDoa;
import com.demo.taskapp.Dao.UserTaskDao;
import com.demo.taskapp.Models.Task;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by piyush.agarwal on 26/12/16.
 */

@Path("/task/")
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {
    private UserDoa userDoa;
    private UserTaskDao userTaskDao;

    public TaskController(){

    }

    public TaskController(UserDoa userDoa, UserTaskDao userTaskDao){
        this.userDoa = userDoa;
        this.userTaskDao = userTaskDao;
    }

    @POST
    @Path("/addTask/")
    public Response addTask(String body){
//        Logger.getGlobal().info(body);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(body);
            String phoneNumber = jsonObject.getString("phoneNumber");
            if(!userDoa.existsUser(phoneNumber)){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            DateTimeFormatter dtf = DateTimeFormat.forPattern("DD/mm/yyyy HH:mm:ss");
            DateTime dateTime = dtf.parseDateTime(jsonObject.getString("dueDate"));

            Task task = new Task(jsonObject.get("taskDetails").toString(), dateTime);
            userTaskDao.insertUserTask(phoneNumber, task);
            return Response.status(Response.Status.CREATED).entity(task).build();
        } catch (JSONException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/getTask")
    public Response getTask(@QueryParam("phoneNumber") String phoneNumber){
        List<Task> tasks = userTaskDao.getTasksOfUser(phoneNumber);
        return Response.status(Response.Status.OK).entity(tasks).build();
    }
}