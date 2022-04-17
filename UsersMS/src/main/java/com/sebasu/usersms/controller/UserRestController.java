package com.sebasu.usersms.controller;

import com.sebasu.usersms.Exceptions.RestResponse;
import com.sebasu.usersms.model.UserRest;
import com.sebasu.usersms.model.UserRestByRequest;
import com.sebasu.usersms.service.UserRestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private static final Logger log = LogManager.getLogger(UserRestController.class);

    @Autowired
    UserRestService srvc;

    @GetMapping
    public Iterable<UserRest> getUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page, //user?page=1&limit=50&sort=asc
                                       @RequestParam(name = "limit", defaultValue = "50", required = false) int limit,
                                       @RequestParam(name = "sort", defaultValue = "asc", required = false) String sort) {
        StringBuilder b = new StringBuilder("users called with ");
        b.append(" page: ").append(page).append(", limit: ").append(limit).append(" sort: ").append(sort);
        log.debug(b.toString());
        return srvc.getAllUsers();
    }


    /**
     * get user by id
     *
     * @param userId
     * @return List<UserRest>
     */
    @GetMapping(path = "/{userId}")// /users/{userId} (required id)
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        Optional<UserRest> usr = Optional.ofNullable(srvc.getUser(userId));
        log.debug("method getUsers called with id {}, will return {}", userId, usr);
        return usr.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * updates user
     *
     * @param user
     * @return
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity updateUser(@Valid @RequestBody UserRestByRequest user) {
        log.debug("put user called, received user: {}", user.toString());
        UserRest userRest = srvc.updateUser(user);
        return ResponseEntity.ok(userRest);
    }

    /**
     * creates new usr
     *
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE}
            , produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity addUser(@Valid @RequestBody UserRestByRequest user) {
        log.debug("post user called, received user: {}", user);
        UserRest userRest = srvc.createUser(user);
        return new ResponseEntity(userRest.getRestStatus(), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable String userId) {
        String result = "";
        result = srvc.deleteUser(userId);
        log.debug("delete user called for id: {}, response: {}", userId, result);
        return result;
    }

    @DeleteMapping
    public String deleteAllUser(@RequestParam(name = "confirmed", required = false, defaultValue = "false") Boolean confirmed) {
        String result = srvc.deleteAllUsers(confirmed);
        log.debug("delete all users called, response: {}", result);
        return result;
    }

//manipulate Arguments not valid Exception response
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>(ex.getBindingResult().getFieldErrors().size());

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        RestResponse restResponse= RestResponse.builder().errors(errors).message("Data validation exception").build();
        return restResponse;
    }


}
