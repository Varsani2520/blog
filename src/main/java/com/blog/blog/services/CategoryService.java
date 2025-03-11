package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payload.CategoryDTO;

public interface CategoryService {
    // create 
    CategoryDTO createCategory(CategoryDTO category);

    // update
    CategoryDTO updateCategory(CategoryDTO category,Integer id);

    // delete
    void deleteCategory(Integer id);

    // get by id 
    CategoryDTO getCategoryById(Integer id);

    // get all
    List<CategoryDTO> getAllCcaltegory();
}
