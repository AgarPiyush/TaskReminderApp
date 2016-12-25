package com.demo.taskapp.Dao;

import com.demo.taskapp.Models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class UserTaskTao {
    private static Map<String, List<Task> > userTaskMap = new HashMap<String, List<Task>>();

    private void insertUserTask(String phoneNumber, Task task){
        List<Task> currentTask;

        if(userTaskMap.containsKey(phoneNumber)){
            currentTask = userTaskMap.get(phoneNumber);
        }
        else{
           currentTask = new ArrayList<Task>();
        }
        currentTask.add(task);
        userTaskMap.put(phoneNumber, currentTask);
    }

    private List<Task> getTasksOfUser(String phoneNumber){
        return userTaskMap.get(phoneNumber);
    }

}
