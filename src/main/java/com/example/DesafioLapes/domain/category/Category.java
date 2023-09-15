package com.example.DesafioLapes.domain.category;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "category")
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
