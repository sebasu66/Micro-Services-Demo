package com.sebasu.users.model;

import com.sebasu.usersms.model.UserRest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String EMAIL = "test@email.com";
    private final String NAME = "name";
    private final String LASTNAME = "surname";

    private UserRest user;

    @BeforeEach
    void setUp() {
        user = new UserRest();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }


    @Test
    void getName() {
        user.setName("Juan");
        assertThat(user.getName(), is("Juan"));
    }

    @Test
    void getEmail() {
        user.setEmail("test@mail.com");
        assertThat(user.getEmail(), is("test@mail.com"));
    }

    @Test
    void getPassword() {
        user.setPassword("12345");
        assertThat(user.getPassword(), is("12345"));
    }

    @Test
    void getUsername() {
        user.setUserName("username");
        assertThat(user.getUserName(), is("username"));
    }

    @Test
    void getLastName() {
        user.setLastName("surname");
        assertThat(user.getLastName(), is("surname"));
    }

    @Test
    void getId() {
        user.setId("test");
        assertThat(user.getId(), is("test"));
    }


    @Test
    void setId() {
        user.setId("test");
        assertThat(user.getId(), is("test"));
    }

    @Test
    void setRestStatus() {
        user.setRestStatus("status");
        assertEquals("status", user.getRestStatus());
    }

    @Test
    void getRestStatus() {
        user.setRestStatus("status");
        assertEquals("status", user.getRestStatus());
    }

    @Test
    void constructorTest(){
        UserRest user = new UserRest(USERNAME, PASSWORD, EMAIL, NAME, LASTNAME);
        assertThat(user.getUserName(), is(USERNAME));
        assertThat(user.getPassword(), is(PASSWORD));
        assertThat(user.getEmail(), is(EMAIL));
        assertThat(user.getName(), is(NAME));
        assertThat(user.getLastName(), is(LASTNAME));
    }

    @Test
    void setEmail() {
        user.setEmail(EMAIL);
        assertThat(user.getEmail(), is(EMAIL));
    }

    @Test
    void setPassword() {
        user.setPassword(PASSWORD);
        assertThat(user.getPassword(), is(PASSWORD));
    }

    @Test
    void setUsername() {
        user.setUserName(USERNAME);
        assertThat(user.getUserName(), is(USERNAME));
    }

    @Test
    void setNameAndLastname() {
        user.setName(NAME);
        user.setLastName(LASTNAME);
        assertThat(user.getName(), is(NAME));
        assertThat(user.getLastName(), is(LASTNAME));
    }


}