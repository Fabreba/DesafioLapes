package com.example.DesafioLapes.repositorios;

import com.example.DesafioLapes.dominio.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepositorio extends JpaRepository<Produto,Long> {

    Optional<Produto> findByNome(String nome);
}
