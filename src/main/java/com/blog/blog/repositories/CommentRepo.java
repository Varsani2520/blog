package com.blog.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
