package com.demo.taskapp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class Task {

    @JsonProperty
    @NotEmpty
    private String taskDetails;

    @JsonProperty
    @JsonSerialize(using=DateTimeSerializer.class)
    private DateTime dueDate;

    private UUID id;

    public Task(){

    }

    public Task(String taskDetails, DateTime dueDate, UUID id){
        this.taskDetails = taskDetails;
        this.dueDate = dueDate;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

}
