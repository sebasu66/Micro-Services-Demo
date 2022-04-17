package com.sebasu.usersms.repository;


import com.sebasu.usersms.Exceptions.CustomException;
import com.sebasu.usersms.model.UserRest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRestRepoMock {

    private static Map<String, UserRest> db;

    static {
        db = new HashMap<>();
        //dummy data
        UserRest user1 = new UserRest();
        user1.setName("Sebastian");
        user1.setLastName("Suarez");
        user1.setEmail("myMail@mail.com");
        user1.setUserName("sebasu01");
        db.put(user1.getId(), user1);
    }
    //find 1 user
    public UserRest findById(String id) {
        return db.get(id);
    }
    //find all users

    public Map<String, UserRest> findAll() {
        return db;
    }
    //save new user
    public UserRest create(UserRest user) {
        Optional<UserRest> usr = Optional.ofNullable(db.get(user.getId()));
        String result;
        if(!usr.isPresent()) {
            db.put(user.getId(), user);
                result = "ok";
        }else{
            throw new CustomException("User can't be created", CustomException.ERROR_CODE_INVALID_PARAMETERS, "User already exists");
        }
        return (UserRest) user.setRestStatus(result);
}

    //update user
    public UserRest update(UserRest user) {
       String result;
        Optional<UserRest> usr = Optional.ofNullable(db.get(user.getId()));
        if (usr.isPresent()) { //if user exists
            db.put(user.getId(), user);
            result="ok";
        }else{
            throw new CustomException("User can't be updated", CustomException.ERROR_CODE_INVALID_PARAMETERS, "User doesn't exist");
        }
        return (UserRest) user.setRestStatus(result);
    }

    //delete all users
    public String deleteAll() {
           db.clear();
           return "ok";
    }

    //delete user
    public String delete(String id) {
        Optional<UserRest> usr = Optional.ofNullable(db.get(id));
        String result;
        if (usr.isPresent()) { //if user exists
            db.remove(id);
            result = "ok";
        }else
            throw new CustomException("User can't be deleted", CustomException.ERROR_CODE_INVALID_PARAMETERS, "User doesn't exist");
        return result;
        }



}


