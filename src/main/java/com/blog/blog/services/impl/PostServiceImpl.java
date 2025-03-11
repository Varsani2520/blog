package com.blog.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.blog.blog.Entities.Category;
import com.blog.blog.Entities.Post;
import com.blog.blog.Entities.User;
import com.blog.blog.exceptions.ResourceNotFound;
import com.blog.blog.payload.PostDTO;
import com.blog.blog.payload.PostResponse;
import com.blog.blog.repositories.CategoryRepo;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postrepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo catRepo;

    @Override
    public PostDTO createPost(PostDTO postdto, Integer userId, Integer catId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "id", userId));

        Category cat = this.catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("cat", "id", catId));

        Post post = this.modelMapper.map(postdto, Post.class);
        post.setImageName("default.jpg");
        post.setAdded_Date(new Date());

        post.setUser(user);
        post.setCategory(cat);

        Post newPost = this.postrepo.save(post);
        return this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO post, Integer postId) {
        Post posts = this.postrepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        posts.setTitle(post.getTitle());
        posts.setContent(post.getContent());
        posts.setImageName(post.getImageName());
        Post updatedPost = this.postrepo.save(posts);
        return this.modelMapper.map(updatedPost, PostDTO.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postrepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        this.postrepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending());

        // Create a pageable request
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        // Use the pageable request in findAll()
        Page<Post> pagePost = this.postrepo.findAll(p);

        // Get the content from the page
        List<Post> allposts = pagePost.getContent();

        // Convert List<Post> to List<PostDTO>
        List<PostDTO> postdto = allposts.stream()
                .map((post) -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postdto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = this.postrepo.findById(id).orElseThrow(() -> new ResourceNotFound("post", "post id", id));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByUserId(Integer user_id) {
        User u = this.userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFound("user", "user id", user_id));

        List<Post> posts = this.postrepo.findByUser(u);
        List<PostDTO> postdto = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postdto;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

    @Override
    public List<PostDTO> getPostByCategoryId(Integer cat_id) {
        Category cat = this.catRepo.findById(cat_id)
                .orElseThrow(() -> new ResourceNotFound("category", "cat id", cat_id));

        List<Post> posts = this.postrepo.findByCategory(cat);
        List<PostDTO> postdto = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postdto;
    }

}
