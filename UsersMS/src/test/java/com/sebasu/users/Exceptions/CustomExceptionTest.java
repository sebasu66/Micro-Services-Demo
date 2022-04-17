package com.sebasu.users.Exceptions;

import com.sebasu.usersms.Exceptions.CustomException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionTest {

    @Test
    void testConstructorErrorCodeAndMessage() {
        CustomException customException = new CustomException("Error",CustomException.ERROR_CODE_INVALID_REQUEST);
        assertEquals(CustomException.ERROR_CODE_INVALID_REQUEST, customException.getErrorCode());
        assertThat(customException.getMessage(),is("Error"));
    }

    @Test
    void testOtherConstructor() {
        CustomException customException = new CustomException("Error",CustomException.ERROR_CODE_INVALID_REQUEST, "cause message");
        assertThat(customException.getCause(),is(notNullValue()));
        assertThat(customException.getCause().getMessage(),is("cause message"));

    }
}