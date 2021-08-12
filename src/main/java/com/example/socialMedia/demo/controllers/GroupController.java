package com.example.socialMedia.demo.controllers;


import com.example.socialMedia.demo.models.GroupOfUsers;
import com.example.socialMedia.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {


    @Autowired
    private GroupService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GroupOfUsers addGroup(@RequestBody GroupOfUsers post) {
        return service.addGroup(post);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<GroupOfUsers> getGroups() {
        return service.getGroups();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GroupOfUsers findGroupById(@PathVariable int id) {
        return service.getGroupById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int getNumberOfGroups() {
        return service.getNumberOfGroups();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GroupOfUsers updateGroup(@RequestBody GroupOfUsers post) {
        return service.updateGroup(post);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GroupOfUsers deleteGroup(@PathVariable int id) {
        return service.deleteGroup(id);
    }
}
