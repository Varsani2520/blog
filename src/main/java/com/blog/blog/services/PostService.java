package com.blog.blog.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.blog.payload.PostDTO;
import com.blog.blog.payload.PostResponse;

public interface PostService {

    // create
    PostDTO createPost(PostDTO post,Integer userId,Integer catId,MultipartFile imageName) throws IOException;

    // update
    PostDTO updatePost(PostDTO post, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);

    // get single post
    PostDTO getPostById(Integer id);

    // get all post by category id

    List<PostDTO> getPostByCategoryId(Integer cat_id);

    // get post by user
    List<PostDTO> getPostByUserId(Integer user_id);

    // search post
    List<PostDTO> searchPosts(String keyword);
}
