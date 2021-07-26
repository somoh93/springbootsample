package com.somlabs.springbootsample.web;

import com.somlabs.springbootsample.model.User;
import com.somlabs.springbootsample.services.UserService;
import com.somlabs.springbootsample.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<?> errorMap = validationService.validateRequestBody(bindingResult);
        if(errorMap != null) return errorMap;

        User newUser = userService.createOrUpdateUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username){
        User user = userService.findByUsername(username.toLowerCase());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        userService.deleteUserByUsername(username.toLowerCase());
        return new ResponseEntity<String>("Username "+username+" deleted", HttpStatus.OK);
    }
}
