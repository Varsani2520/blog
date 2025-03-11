package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payload.PostDTO;
import com.blog.blog.payload.PostResponse;

public interface PostService {

    // create
    PostDTO createPost(PostDTO post,Integer userId,Integer catId);

    // update
    PostDTO updatePost(PostDTO post, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber,Integer pageSize);

    // get single post
    PostDTO getPostById(Integer id);

    // get all post by category id

    List<PostDTO> getPostByCategoryId(Integer cat_id);

    // get post by user
    List<PostDTO> getPostByUserId(Integer user_id);

    // search post
    List<PostDTO> searchPosts(String keyword);
}
