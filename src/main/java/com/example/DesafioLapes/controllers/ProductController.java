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
    public ResponseEntity adicionarProduto(@RequestBody @Valid ProductDTO data){
        Optional<Product> produtoExistente = this.productRepository.findByName(data.name());
        Optional<Category> categoriaExistente = this.categoryRepository.findCategoryByName(data.name_category());
        boolean produtoBool = produtoExistente.isPresent();
        boolean categoriaBool = categoriaExistente.isPresent();
        if( produtoBool || !categoriaBool){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        Product novoProduct = new Product(data.name(),data.price(), data.name_category());
        productRepository.save(novoProduct);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removerProduto(@RequestBody @Valid ProductDTO data){
        Optional<Product> produtoExiste =  this.productRepository.findByName(data.name());
        if (produtoExiste.isPresent()){
            Product product = produtoExiste.get();
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }
    @GetMapping
    public ResponseEntity listarTodosOsProdutos(){
        List<Product> productLista = productRepository.findAll();
        List<ProductResponseDTO> produtoDTOs = productLista.stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtoDTOs);
    }
}
