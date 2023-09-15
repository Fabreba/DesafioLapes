package com.example.DesafioLapes.domain.product;

public record ProductResponseDTO(String id, String name, Float price, String name_category) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getName_category());
    }

}
