package com.demo.taskapp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

/**
 * Created by piyush.agarwal on 27/12/16.
 */
public class TaskUser {

    @NotEmpty
    @JsonProperty
    private String phoneNumber;

    @NotEmpty
    @JsonProperty
    private String taskDetails;

    @JsonProperty
    private String dueDate;

    public TaskUser(){

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
