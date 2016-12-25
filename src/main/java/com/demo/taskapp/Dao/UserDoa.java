package com.demo.taskapp.Dao;

import com.demo.taskapp.Models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class UserDoa {
    public static Map<String, String> userMap = new HashMap<String, String>();

    private void insertUser(String phoneNumber, String userName){
        userMap.put(phoneNumber, userName);
    }

    private String getUser(String phoneNumber){
        return userMap.get(phoneNumber);
    }

    private boolean existsUser(String phoneNumber){
        return userMap.containsKey(phoneNumber);
    }
}
