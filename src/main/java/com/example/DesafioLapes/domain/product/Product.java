package com.example.DesafioLapes.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String name;
    private Float price;
    private String name_category;

    public Product(String name, Float price, String name_category) {
        this.name = name;
        this.price = price;
        this.name_category = name_category;
    }
}
