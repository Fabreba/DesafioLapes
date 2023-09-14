package com.example.DesafioLapes.repositorios;

import com.example.DesafioLapes.dominio.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findCategoriaByNome(String nome);
    Categoria findCategoriaById(String id);

}
