package com.sebasu.usersms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserRest extends BaseModelEntity {
    private String userName;
    private String password;
    private String email;
    private String name;
    private String lastName;

    public UserRest(){
        super();
    }

}
