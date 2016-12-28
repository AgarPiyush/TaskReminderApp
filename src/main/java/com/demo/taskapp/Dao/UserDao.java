package com.demo.taskapp.Dao;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by piyush.agarwal on 23/12/16.
 */
public class UserDao {
    public static Map<String, String> userMap = new HashMap<String, String>();

    public void insertUser(String phoneNumber, String userName){
        userMap.put(phoneNumber, userName);
    }

    public String getUser(String phoneNumber){
        return userMap.get(phoneNumber);
    }

    public boolean existsUser(String phoneNumber){
        return userMap.containsKey(phoneNumber);
    }
}
