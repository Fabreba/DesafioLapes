package com.example.DesafioLapes.repositories;

import com.example.DesafioLapes.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryByName(String name);
    Category findCategoryById(String id);

}
