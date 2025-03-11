package com.blog.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

    java.util.Locale.Category save(java.util.Locale.Category cat);
    
}
