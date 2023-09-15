package com.example.DesafioLapes.controllers;

import com.example.DesafioLapes.domain.category.Category;
import com.example.DesafioLapes.domain.category.CategoriaDTO;
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
    private CategoryRepository repositorio;

    @PostMapping
    public ResponseEntity adicionarCategoria(@RequestBody @Valid CategoriaDTO data){
        Optional<Category> categoriaExistente = this.repositorio.findCategoryByName(data.nome());
        if (categoriaExistente.isPresent()){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
        Category novaCategory = new Category(data.nome());
        repositorio.save(novaCategory);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removerCategoria(@RequestBody @Valid CategoriaDTO data){
        Optional<Category> categoriaExiste =  this.repositorio.findCategoryByName(data.nome());
        if (categoriaExiste.isPresent()){
            Category category = categoriaExiste.get();
            repositorio.delete(category);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

}
