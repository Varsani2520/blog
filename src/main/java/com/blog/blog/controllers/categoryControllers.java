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
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payload.ApiResponse;
import com.blog.blog.payload.CategoryDTO;
import com.blog.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
@Validated
public class categoryControllers {
    @Autowired
    private CategoryService categoryService;

    // create
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO catdto) {
        this.categoryService.createCategory(catdto);
        return new ResponseEntity<>(catdto, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO catdto, @PathVariable Integer id) {
        CategoryDTO cat=this.categoryService.updateCategory(catdto, id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted sucess", true), HttpStatus.OK);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCatgoryWithId(@PathVariable int id) {
        CategoryDTO cat = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategoryAll() {
        return new ResponseEntity<>(this.categoryService.getAllCcaltegory(), HttpStatus.OK);
    }
}