package com.demo.taskapp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class User {


    @NotEmpty
    @JsonProperty
    private String userName;

    @NotEmpty
    @JsonProperty
    private String phoneNumber;

    public User(){

    }

    @JsonProperty
    public String getUserName() {
        return userName;
    }

    @JsonProperty
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
