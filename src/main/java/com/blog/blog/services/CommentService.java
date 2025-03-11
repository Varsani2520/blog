package com.blog.blog.services;

import com.blog.blog.payload.CommentDTO;

public interface CommentService {
    // create
    CommentDTO createComment(CommentDTO comment, Integer postId);

    // delete
    void deleteComment(Integer commentId);
}
