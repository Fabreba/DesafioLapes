package com.example.DesafioLapes.controllers;

import com.example.DesafioLapes.domain.category.Category;
import com.example.DesafioLapes.domain.product.Product;
import com.example.DesafioLapes.domain.product.ProductDTO;
import com.example.DesafioLapes.domain.product.ProductResponseDTO;
import com.example.DesafioLapes.repositories.CategoryRepository;
import com.example.DesafioLapes.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductDTO data){
        Optional<Product> productExists = this.productRepository.findByName(data.name());
        Optional<Category> categoryExists = this.categoryRepository.findCategoryByName(data.name_category());
        boolean productBool = productExists.isPresent();
        boolean categoryBool = categoryExists.isPresent();
        if( productBool || !categoryBool){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        Product newProduct = new Product(data.name(),data.price(), data.name_category());
        productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removeProduct(@RequestBody @Valid ProductDTO data){
        Optional<Product> productExists =  this.productRepository.findByName(data.name());
        if (productExists.isPresent()){
            Product product = productExists.get();
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }
    @GetMapping
    public ResponseEntity getAllProducts(){
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDTO> productDTOs = productList.stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDTOs);
    }
}
