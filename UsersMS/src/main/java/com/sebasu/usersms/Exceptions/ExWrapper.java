package com.sebasu.usersms.Exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.Optional;
@Getter
@Builder//Lombok generate builder
public class ExWrapper {
    @NonNull

    private Exception exception;
    @Builder.Default //optional parameter while building
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Obtain a human-readable message from an exception
     * @return
     */
    public String betterMsg() {
        Optional<Throwable> cause = Optional.ofNullable( exception.getCause());
        String causeString = cause.isPresent() ? " , the cause was: " + cause.get().getMessage(): "";
        return "An error occurred : " + exception.getMessage() + causeString;
    }

    /**
     * obtain an adequate http status code from an exception
     * @return
     */
    public HttpStatus getHTTPStatus() {
     if(exception instanceof CustomException){
             String code = ((CustomException) exception).getErrorCode();
             switch (code){
                 case CustomException.ERROR_CODE_INVALID_USER:
                        status = HttpStatus.UNAUTHORIZED;
                     break;
                 case CustomException.ERROR_CODE_INVALID_PARAMETERS:
                        status = HttpStatus.BAD_REQUEST;
                     break;
                 default:
             }
     }

        return status;
    }



}
