package com.sebasu.users.Exceptions;

import com.sebasu.usersms.Exceptions.RestResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class RestResponseTest {

    @Autowired
    RestResponse restResponse;

    @BeforeEach
    void setUp() {
        List<String> eList = new ArrayList<>();
        eList.add("Error");
        restResponse = RestResponse.builder().message("message").errors(eList).path("path").title("title").build();
    }

    @AfterEach
    void tearDown() {
    restResponse = null;
    }

    //test builder
    @Test
    void testBuilder() {
        assertThat(restResponse, is (notNullValue()));
    }
    //test all getters and setters
    @Test
    void testAllGettersForInitialValues() {
        assertThat(restResponse.getMessage(),is("message"));
        assertThat(restResponse.getPath(),is("path"));
        assertThat(restResponse.getTitle(),is("title"));
        assertThat(restResponse.getErrors().get(0),is("Error"));
    }

    @Test
    void testAllSetters() {
        restResponse.setMessage("newMessage");
        restResponse.setPath("newPath");
        restResponse.setTitle("newTitle");
        restResponse.getErrors().add("newError");
        assertThat(restResponse.getMessage(),is("newMessage"));
        assertThat(restResponse.getPath(),is("newPath"));
        assertThat(restResponse.getTitle(),is("newTitle"));
        assertThat(restResponse.getErrors().get(1),is("newError"));

    }

}