package com.demo.taskapp.Controller;

import com.demo.taskapp.Dao.UserDoa;
import com.demo.taskapp.Dao.UserTaskDao;
import com.demo.taskapp.Models.Task;
import com.demo.taskapp.Models.TaskUser;
import io.dropwizard.jersey.PATCH;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
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
    public Response addTask(TaskUser taskUser){

        String phoneNumber = taskUser.getPhoneNumber();
        if(!userDoa.existsUser(phoneNumber)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DateTimeFormatter dtf = DateTimeFormat.forPattern("DD/mm/yyyy HH:mm:ss");
        DateTime dateTime = dtf.parseDateTime(taskUser.getDueDate());

        Task task = new Task(taskUser.getTaskDetails(), dateTime, UUID.randomUUID());
        userTaskDao.insertUserTask(phoneNumber, task);
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    @GET
    @Path("/getTask")
    public Response getTask(@QueryParam("phoneNumber") String phoneNumber){
        List<Task> tasks = userTaskDao.getTasksOfUser(phoneNumber);
        return Response.status(Response.Status.OK).entity(tasks).build();
    }

    @PATCH
    @Path("/modifyTask/")
    public Response modifyTask(@QueryParam("id") String id, TaskUser taskUser){
        if(!userDoa.existsUser(taskUser.getPhoneNumber())){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Task> tasks = userTaskDao.getTasksOfUser(taskUser.getPhoneNumber());
        Logger.getGlobal().info("Number of tasks user has "+tasks.size());
        Logger.getGlobal().info("Phone number " + taskUser.getPhoneNumber());
        Logger.getGlobal().info("Given id " + id );

        Task userTaskToModify = null;
        for(Task task: tasks){
            if(task.getId().toString().equals(id)){
                userTaskToModify = task;
            }
        }

        if(userTaskToModify == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(taskUser.getDueDate() != null){
            DateTimeFormatter dtf = DateTimeFormat.forPattern("DD/mm/yyyy HH:mm:ss");
            DateTime dateTime = dtf.parseDateTime(taskUser.getDueDate());
            userTaskToModify.setDueDate(dateTime);
        }

        if(taskUser.getTaskDetails() != null)
            userTaskToModify.setTaskDetails(taskUser.getTaskDetails());

        return  Response.status(Response.Status.OK).entity(userTaskToModify).build();
    }
}