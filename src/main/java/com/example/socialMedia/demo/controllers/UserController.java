package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.Comment;
import com.example.socialMedia.demo.models.GroupOfUsers;
import com.example.socialMedia.demo.models.Post;
import com.example.socialMedia.demo.models.User;
import com.example.socialMedia.demo.services.CommentService;
import com.example.socialMedia.demo.services.GroupService;
import com.example.socialMedia.demo.services.PostService;
import com.example.socialMedia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/normal")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CommentService commentService;


    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addPrivatePost/{groupId}")
    public Post addPrivatePost(@RequestBody Post post, @PathVariable int groupId) {

        GroupOfUsers group = groupService.getGroupById(groupId);
        User writer = userService.getUserById(1);

        if (post == null || writer == null || group == null)
            return null;

        if (!group.getUsers().contains(writer))
            return null;

        post.setWriter(writer);
        post.setPrivate(true);

        writer.getPosts().add(post);
        group.getPosts().add(post);

        postService.addPost(post);
        userService.updateUser(writer);
        groupService.updateGroup(group);

        return post;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addPublicPost")
    public Post addPublicPost(@RequestBody Post post) {
        User writer = userService.getUserById(1);

        if (post == null || writer == null)
            return null;

        post.setGroupOfUsers(null);
        post.setWriter(writer);
        post.setPrivate(true);

        writer.getPosts().add(post);

        postService.addPost(post);
        userService.updateUser(writer);

        return post;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @GetMapping("/joinToGroup/{groupId}")
    public boolean joinToGroup(@PathVariable int groupId) {
        User user = userService.getUserById(2);
        GroupOfUsers group = groupService.getGroupById(groupId);

        if (group == null || user == null)
            return false;

        group.getWaitingListOfUsers().add(user);

        userService.updateUser(user);
        groupService.updateGroup(group);

        return true;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addGroup")
    public boolean addGroup(@RequestBody GroupOfUsers group) {
        User groupAdmin = userService.getUserById(1);

        if (group == null || groupAdmin == null)
            return false;

        group.setGroupAdmin(groupAdmin);
        group.setAccepted(false);

        groupAdmin.getGroupsAdminInIt().add(group);

        groupService.addGroup(group);
        userService.updateUser(groupAdmin);

        return true;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addComment/{postId}")
    public boolean addComment(@RequestBody Comment comment, @PathVariable int postId) {
        User writer = userService.getUserById(1);
        Post post = postService.getPostById(postId);

        if (post == null || writer == null || comment == null)
            return false;

        comment.setWriter(writer);
        comment.setPost(post);
        writer.getCommentsWriteIt().add(comment);
        post.getComments().add(comment);

        commentService.addComment(comment);
        postService.updatePost(post);
        userService.updateUser(writer);

        return true;
    }


    @GetMapping("acceptPrivatePost/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public boolean acceptPrivatePost(@PathVariable int postId) {
        User adminGroup = userService.getUserById(1);

        Post post = postService.getPostById(postId);
        if (post == null)
            return false;

        if (post.getGroupOfUsers() == null || !post.getGroupOfUsers().getGroupAdmin().equals(adminGroup))
            return false;

        post.setAccepted(true);
        return true;
    }

    @GetMapping("acceptJoiningGroup/{groupId}/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public boolean acceptJoiningGroup(@PathVariable int groupId, @PathVariable int userId) {

        User groupAdmin = userService.getUserById(1);

        User waitingUser = userService.getUserById(userId);
        GroupOfUsers group = groupService.getGroupById(groupId);

        if (group == null || waitingUser == null)
            return false;

        if (!group.getWaitingListOfUsers().contains(waitingUser))
            return false;

        group.getWaitingListOfUsers().remove(waitingUser);
        group.getUsers().add(waitingUser);

        waitingUser.getGroups().add(group);

        groupService.updateGroup(group);
        userService.updateUser(waitingUser);
        return true;
    }

}