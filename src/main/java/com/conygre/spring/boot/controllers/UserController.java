package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.User;
import com.conygre.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() { return service.getUsers(); }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        return user == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") int id) { service.deleteUser(id); }

    @PostMapping()
    public void addUser(@RequestBody User user) { service.addToUser(user); }

    @PutMapping()
    public void updateUser(@RequestBody User user) { service.updateUser(user); }
}
