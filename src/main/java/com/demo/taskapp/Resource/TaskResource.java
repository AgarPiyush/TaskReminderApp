package com.demo.taskapp.Resource;

import com.demo.taskapp.Dao.UserDao;
import com.demo.taskapp.Dao.TaskDao;
import com.demo.taskapp.Model.Task;
import io.dropwizard.jersey.PATCH;
import org.joda.time.DateTime;
import sun.rmi.runtime.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by piyush.agarwal on 26/12/16.
 */

@Path("/users/{userId}/tasks/")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private UserDao userDao;
    private TaskDao taskDao;

    public TaskResource(){

    }

    public TaskResource(UserDao userDao, TaskDao taskDao){
        this.userDao = userDao;
        this.taskDao = taskDao;
    }

    @POST
    public Response addTask(Task task, @PathParam("userId") String phoneNumber){

        if(!userDao.existsUser(phoneNumber)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        task.setId(UUID.randomUUID());

        taskDao.insertUserTask(phoneNumber, task);
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    @GET
    public Response getTask(@PathParam("userId") String phoneNumber){
        List<Task> tasks = taskDao.getTasksOfUser(phoneNumber);
        return Response.status(Response.Status.OK).entity(tasks).build();
    }

    @PATCH
    @Path("/{taskId}/")
    public Response modifyTask(Task task, @PathParam("userId") String phoneNumber, @PathParam("taskId") String taskId){
        if(!userDao.existsUser(phoneNumber)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Task> tasks = taskDao.getTasksOfUser(phoneNumber);

        Task userTaskToModify = null;
        for(Task existingTask: tasks){
            if(existingTask.getId().toString().equals(taskId)){
                userTaskToModify = existingTask;
            }
        }

        if(userTaskToModify == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        DateTime dueDate = (task.getDueDate() != null) ? task.getDueDate() : userTaskToModify.getDueDate();
        userTaskToModify.setDueDate(dueDate);

        String taskDetails = (task.getTaskDetails() != null) ? task.getTaskDetails() : userTaskToModify.getTaskDetails();
        userTaskToModify.setTaskDetails(taskDetails);

        Logger.getGlobal().info(userTaskToModify.getTaskDetails());
        return  Response.status(Response.Status.OK).entity(userTaskToModify).build();
    }
}