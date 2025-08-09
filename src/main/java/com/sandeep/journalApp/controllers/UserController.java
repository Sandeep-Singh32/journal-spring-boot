package com.sandeep.journalApp.controllers;

import com.sandeep.journalApp.entity.User;
import com.sandeep.journalApp.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        try{
            User newUser = this.userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        User u = this.userService.getUserByName(name);
        return new ResponseEntity<>(u,HttpStatus.OK );
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getUserByName(){
        return new ResponseEntity<>(this.userService.getUsers(),HttpStatus.OK );
    }

    @PutMapping("{name}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.updateUser(user),HttpStatus.OK );
    }
}
