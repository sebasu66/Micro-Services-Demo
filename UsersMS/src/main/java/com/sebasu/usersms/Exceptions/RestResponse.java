package com.sebasu.usersms.Exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RestResponse {
    @Builder.Default
    private String title = "Opps!, something went wrong";
    @NonNull
    private String message;
    private List<String> errors;
    private String path;

}
