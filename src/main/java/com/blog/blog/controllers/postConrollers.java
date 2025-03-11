package com.blog.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payload.ApiResponse;
import com.blog.blog.payload.PostDTO;
import com.blog.blog.payload.PostResponse;
import com.blog.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
@Validated
public class postConrollers {

    @Autowired
    private PostService postservice;

    // create
    @PostMapping("/userId/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestBody PostDTO postdto,
            @PathVariable Integer userId,
            @PathVariable Integer catId) {

        PostDTO savepost = this.postservice.createPost(postdto, userId, catId);
        return new ResponseEntity<>(savepost, HttpStatus.CREATED);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Integer userId) {

        List<PostDTO> posts = this.postservice.getPostByUserId(userId);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCatId(@PathVariable Integer catId) {

        List<PostDTO> posts = this.postservice.getPostByCategoryId(catId);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    // get post all
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value="sortDirection",defaultValue = "asc",required=false) String sortDirection) {
        PostResponse postResponse = this.postservice.getAllPost(pageNumber, pageSize,sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostByPostID(@PathVariable Integer postId) {
        PostDTO allpost = this.postservice.getPostById(postId);
        return new ResponseEntity<PostDTO>(allpost, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postservice.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post successfully deleted", true), HttpStatus.OK);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatepost(@RequestBody PostDTO postdto, @PathVariable Integer postId) {
        PostDTO updatepost = this.postservice.updatePost(postdto, postId);
        return new ResponseEntity<>(updatepost, HttpStatus.OK);

    }
}
