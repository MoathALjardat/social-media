package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.User;
import com.example.socialMedia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/count")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int getNumberOfUsers() {
        return userService.getNumberOfUsers();
    }

    @PutMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
