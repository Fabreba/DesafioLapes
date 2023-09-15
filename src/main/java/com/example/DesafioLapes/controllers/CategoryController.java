package com.example.DesafioLapes.controllers;

import com.example.DesafioLapes.domain.category.Category;
import com.example.DesafioLapes.domain.category.CategoryDTO;
import com.example.DesafioLapes.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    public ResponseEntity addCategory(@RequestBody @Valid CategoryDTO data){
        Optional<Category> categoryExists = this.repository.findCategoryByName(data.name());
        if (categoryExists.isPresent()){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
        Category newCategory = new Category(data.name());
        repository.save(newCategory);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removeCategory(@RequestBody @Valid CategoryDTO data){
        Optional<Category> categoryExists =  this.repository.findCategoryByName(data.name());
        if (categoryExists.isPresent()){
            Category category = categoryExists.get();
            repository.delete(category);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

}
