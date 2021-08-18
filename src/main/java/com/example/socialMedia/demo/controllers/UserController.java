package com.example.socialMedia.demo.controllers;

import com.example.socialMedia.demo.models.*;
import com.example.socialMedia.demo.services.CommentService;
import com.example.socialMedia.demo.services.GroupService;
import com.example.socialMedia.demo.services.PostService;
import com.example.socialMedia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping("/addPrivatePost/{groupId}") // for a user in the group
    public Post addPrivatePost(@RequestBody Post post, @PathVariable int groupId) {

        GroupOfUsers group = groupService.getGroupById(groupId);
        User writer = userService.getCurrentUser();

        if (post == null || writer == null || group == null)
            return null;

        if (!group.getUsers().contains(writer))
            return null;

        post.setWriter(writer);
        post.setType(PostType.PRIVATE);
        post.setStatus(Status.WAITING);

        writer.getPosts().add(post);
        group.getPosts().add(post);

        postService.addPost(post);
        userService.updateUser(writer);
        groupService.updateGroup(group);

        return post;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addPublicPost") // for any user
    public Post addPublicPost(@RequestBody Post post) {

        User writer = userService.getCurrentUser();

        if (post == null || writer == null)
            return null;

        post.setGroupOfUsers(null);
        post.setWriter(writer);
        post.setType(PostType.PUBLIC);
        post.setStatus(Status.WAITING);

        writer.getPosts().add(post);

        postService.addPost(post);
        userService.updateUser(writer);

        return post;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @GetMapping("/joinToGroup/{groupId}") // for any user
    public boolean joinToGroup(@PathVariable int groupId) {
        User user = userService.getCurrentUser();
        GroupOfUsers group = groupService.getGroupById(groupId);

        if (group == null || user == null)
            return false;

        group.getWaitingListOfUsers().add(user);

        userService.updateUser(user);
        groupService.updateGroup(group);

        return true;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addGroup") // for any user
    public boolean addGroup(@RequestBody GroupOfUsers group) {
        User groupAdmin = userService.getCurrentUser();

        if (group == null || groupAdmin == null)
            return false;

        group.setGroupAdmin(groupAdmin);
        group.setStatus(Status.WAITING);

        groupAdmin.getGroupsAdminInIt().add(group);

        groupService.addGroup(group);
        userService.updateUser(groupAdmin);

        return true;
    }

    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    @PostMapping("/addComment/{postId}") // a user can see the post
    public boolean addComment(@RequestBody Comment comment, @PathVariable int postId) {

        User writer = userService.getCurrentUser();

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
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')") // admin of the group
    public boolean acceptPrivatePost(@PathVariable int postId) {

        User groupAdmin = userService.getCurrentUser();

        Post post = postService.getPostById(postId);
        if (post == null)
            return false;

        if (post.getGroupOfUsers() == null || !post.getGroupOfUsers().getGroupAdmin().equals(groupAdmin))
            return false;

        post.setStatus(Status.ACCEPTED);
        postService.updatePost(post);

        return true;
    }

    @GetMapping("acceptJoiningGroup/{groupId}/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')") // admin of the group
    public boolean acceptJoiningGroup(@PathVariable int groupId, @PathVariable int userId) {

        User groupAdmin = userService.getCurrentUser();

        User waitingUser = userService.getUserById(userId);
        GroupOfUsers group = groupService.getGroupById(groupId);

        if (group == null || waitingUser == null || groupAdmin == null)
            return false;

        if (!groupAdmin.equals(group.getGroupAdmin()))
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

    @GetMapping("/publicPosts")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')") // a user int the group
    public List<Post> getAllPublicPosts() {

        List<Post> posts = postService.getPosts();

        posts = posts.stream().filter(post -> post.getType().equals(PostType.PUBLIC)).collect(Collectors.toList());

        return posts;
    }

    @GetMapping("/privatePosts/{groupId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')") // a user int the group
    public List<Post> getPrivatePostsForAGroup(@PathVariable int groupId) {

        GroupOfUsers group = groupService.getGroupById(groupId);

        if (!group.getUsers().contains(userService.getCurrentUser()))
            return null;

        List<Post> posts = group.getPosts();

        return posts;
    }

}