package com.blog.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.Entities.Category;
import com.blog.blog.exceptions.ResourceNotFound;
import com.blog.blog.payload.CategoryDTO;
import com.blog.blog.repositories.CategoryRepo;
import com.blog.blog.services.CategoryService;

@Service
public class categoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override

    public CategoryDTO createCategory(CategoryDTO category) {
        Category cat = this.DtoToCat(category);
        Category addedCat = this.categoryRepo.save(cat);
        return this.catToDTO(addedCat);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categorydto, Integer id) {
        Category cat = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("category", "category id", id));
        cat.setTitle(categorydto.getTitle());
        cat.setDescription(categorydto.getDescription());

        Category updatedCat = this.categoryRepo.save(cat);
        return this.catToDTO(updatedCat);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("category", "id", id));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("category", "id", id));
        return this.catToDTO(cat);
    }

    @Override
    public List<CategoryDTO> getAllCcaltegory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDTO> catDTOS = categories.stream().map(cat -> this.catToDTO(cat)).collect(Collectors.toList());

        return catDTOS;

    }

    private Category DtoToCat(CategoryDTO cat) {
        Category c = new Category();
        c.setTitle(cat.getTitle());
        c.setDescription(cat.getDescription());
        return c;
    }

    private CategoryDTO catToDTO(Category cat) {
        CategoryDTO c = new CategoryDTO();
        c.setTitle(cat.getTitle());
        c.setDescription(cat.getDescription());
        return c;
    }
}
