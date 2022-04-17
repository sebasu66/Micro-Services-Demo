package com.sebasu.usersms.util;

import com.google.gson.GsonBuilder;
import com.sebasu.usersms.model.UserRest;
import com.sebasu.usersms.model.UserRestByRequest;

import static org.springframework.beans.BeanUtils.copyProperties;


public class Utils {

    //get pretty json using Gson
    public static String getPrettyJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public static String generateId() {
        return java.util.UUID.randomUUID().toString();
    }

    public static UserRest getUserRest(UserRestByRequest user) {
        UserRest userRest = new UserRest();
        copyProperties(user, userRest);
        /*userRest.setId(user.getId());
        userRest.setName(user.getName());
        userRest.setLastName(user.getLastname());
        userRest.setEmail(user.getEmail());
        userRest.setPassword(user.getPassword());
        */
        return userRest;

    }
}
