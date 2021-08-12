package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.GroupOfUsers;
import com.example.socialMedia.demo.models.Post;
import com.example.socialMedia.demo.models.User;
import com.example.socialMedia.demo.services.GroupService;
import com.example.socialMedia.demo.services.PostService;
import com.example.socialMedia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private GroupService groupService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int getNumberOfUsers() {
        return userService.getNumberOfUsers();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("acceptGroup/{groupId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public boolean acceptGroup(@PathVariable int groupId) {
        GroupOfUsers group = groupService.getGroupById(groupId);
        if (group == null)
            return false;

        group.setAccepted(true);
        return true;
    }

    @GetMapping("acceptPublicPost/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public boolean acceptPublicPost(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        if (post == null)
            return false;

        post.setAccepted(true);
        return true;
    }
}
