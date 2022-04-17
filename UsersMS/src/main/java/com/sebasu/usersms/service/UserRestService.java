package com.sebasu.usersms.service;


import com.sebasu.usersms.model.UserRest;
import com.sebasu.usersms.model.UserRestByRequest;
import com.sebasu.usersms.repository.UserRestRepoMock;
import com.sebasu.usersms.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserRestService {
    @Autowired
    private UserRestRepoMock mockRepo;

    public UserRest getUser(String id) {
        return mockRepo.findById(id);
    }

    //get a list of all users
    public Iterable<UserRest> getAllUsers() {
        Map<String, UserRest> all = mockRepo.findAll();
        List usrList =new ArrayList<UserRest>(all.values());
        return usrList;
    }

    public UserRest createUser(UserRestByRequest user) {
        return mockRepo.create(Utils.getUserRest(user));
    }

    public UserRest updateUser(UserRestByRequest user) {
        return mockRepo.update(Utils.getUserRest(user));
    }

    //delete all users, return success or failure
    public String deleteAllUsers(Boolean confirmed) {
        //if true ==confirmed, delete all users, otherwise confirmation url
        if (Boolean.TRUE.equals(confirmed)) {
            return mockRepo.deleteAll();
        } else {
           return " confirm ? http://localhost:8080/users?confirmed=true";
        }
    }


    public String deleteUser(String userId) {
        return mockRepo.delete(userId);
    }
}
