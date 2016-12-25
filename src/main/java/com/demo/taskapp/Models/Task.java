package com.demo.taskapp.Models;

import org.joda.time.DateTime;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class Task {

    private String taskDetails;
    private DateTime taskDate;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public DateTime getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(DateTime taskDate) {
        this.taskDate = taskDate;
    }

}
