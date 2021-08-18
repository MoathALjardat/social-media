package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.*;
import com.example.socialMedia.demo.services.GroupService;
import com.example.socialMedia.demo.services.PostService;
import com.example.socialMedia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


    @PostMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User addAdminUser(@RequestBody User user) {
        user.setUserType(UserType.ADMIN);
        return userService.addUser(user);
    }

    @PostMapping("/normal")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User addNormalUser(@RequestBody User user) {
        user.setUserType(UserType.NORMAL);
        return userService.addUser(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int getNumberOfUsers() {
        return userService.getNumberOfUsers();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
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

        group.setStatus(Status.ACCEPTED);

        groupService.updateGroup(group);
        return true;
    }

    @GetMapping("acceptPublicPost/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public boolean acceptPublicPost(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        if (post == null)
            return false;

        post.setStatus(Status.ACCEPTED);
        postService.updatePost(post);
        return true;
    }

}
