package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.Post;
import com.example.socialMedia.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private PostService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post addPost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Post> getPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post getPostById(@PathVariable int id) {
        return service.getPostById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int getNumberOfPosts() {
        return service.getNumberOfPosts();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post updatePost(@RequestBody Post post) {
        return service.updatePost(post);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post deletePost(@PathVariable int id) {
        return service.deletePost(id);
    }
}
