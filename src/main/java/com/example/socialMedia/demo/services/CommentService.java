package com.example.socialMedia.demo.services;

import com.example.socialMedia.demo.models.Comment;
import com.example.socialMedia.demo.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public int getNumberOfComments() {
        return (int) commentRepository.count();
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment deleteComment(int id) {
        Comment deletedComment = commentRepository.findById(id).orElse(null);
        commentRepository.deleteById(id);
        return deletedComment;
    }

    public Comment updateComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
}