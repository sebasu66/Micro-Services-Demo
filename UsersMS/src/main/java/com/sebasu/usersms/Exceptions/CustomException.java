package com.sebasu.usersms.Exceptions;

public class CustomException extends RuntimeException {

    public static final String ERROR_CODE_INVALID_PARAMETERS = "INVALID_PARAMETERS";
    public static final String ERROR_CODE_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String ERROR_CODE_INVALID_USER = "INVALID_USER";
    public static final String ERROR_CODE_INVALID_USER_TYPE = "INVALID_USER_TYPE";
    public static final String ERROR_CODE_INVALID_VALUE = "INVALID_VALUE";
    public static final String ERROR_CODE_INVALID_USER_CREDENTIALS = "INVALID_USER_CREDENTIALS";


    private String errorCode;

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        super.initCause(new Throwable(errorCode) );
    }

    public CustomException(String message, String errorCode,String cause) {
        super(message);
        this.errorCode = errorCode;
        super.initCause(new Throwable(cause) );
    }

    public String getErrorCode() {
        return errorCode;
    }
}


