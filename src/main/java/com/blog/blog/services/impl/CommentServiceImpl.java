package com.blog.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.Entities.Comment;
import com.blog.blog.Entities.Post;
import com.blog.blog.exceptions.ResourceNotFound;
import com.blog.blog.payload.CommentDTO;
import com.blog.blog.repositories.CommentRepo;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private CommentRepo commentrepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentdto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));

        Comment comment = this.modelMapper.map(commentdto, Comment.class);
        comment.setPost(post);
        Comment savedComent = this.commentrepo.save(comment);

        return this.modelMapper.map(savedComent, CommentDTO.class);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentrepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("comment", "comment id", commentId));
        this.commentrepo.delete(comment);
    }
}
