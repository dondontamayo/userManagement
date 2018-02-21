package com.project.userManagement.rest;

import com.project.userManagement.service.UserService;
import com.project.userManagement.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", params = {"page", "size"})
    public Page<User> getUAllUsersResponse(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "2") int size) {

        return userService.getAllUsers(new PageRequest(page, size));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> restGetUser(@PathVariable(value = "id") int userId) {
        User user = userService.getUserById(userId);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> restCreateUser(@RequestBody User userDetails) {

        User user = userService.createUser(userDetails);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
    }

    @PostMapping(value="/users/{id}")
    public ResponseEntity<User> restUpdateUser(@PathVariable(value="id") int id,@RequestBody User userDetails) {
        User user = userService.updateUser(id, userDetails);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<User>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/users/{id}")
    public  ResponseEntity<User> restDeleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        if (userService.getUserById(id)==null) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
