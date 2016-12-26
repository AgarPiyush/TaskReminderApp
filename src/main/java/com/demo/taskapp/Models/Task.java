package com.demo.taskapp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class Task {

    @JsonProperty
    @NotEmpty
    private String taskDetails;

    @JsonProperty
    private DateTime dueDate;

    private int id;

    public Task(String taskDetails, DateTime dueDate){
        this.taskDetails = taskDetails;
        this.dueDate = dueDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime taskDate) {
        this.dueDate = dueDate;
    }

}
