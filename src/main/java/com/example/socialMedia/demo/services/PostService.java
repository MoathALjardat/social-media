package com.example.socialMedia.demo.services;

import com.example.socialMedia.demo.models.Post;
import com.example.socialMedia.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public int getNumberOfPosts() {
        return (int) postRepository.count();
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post deletePost(int id) {
        Post deletedPost = postRepository.findById(id).orElse(null);
        postRepository.deleteById(id);
        return deletedPost;
    }

    public Post updatePost(Post post) {
        postRepository.save(post);
        return post;
    }
}
