package com.sebasu.usersms.model;

import com.sebasu.usersms.util.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModelEntity {

    private String id;
    private String restStatus;


    public BaseModelEntity setRestStatus(String restStatus) {
        this.restStatus = restStatus;
        return this;
    }
//generate unique id

    public BaseModelEntity() {
        this.id = Utils.generateId();
    }

    public String toString() {
        return Utils.getPrettyJson(this);
    }

}
