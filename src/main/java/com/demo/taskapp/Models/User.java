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

    @JsonProperty
    public String getName() {
        return userName;
    }

    @JsonProperty
    public void setName(String userName) {
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
