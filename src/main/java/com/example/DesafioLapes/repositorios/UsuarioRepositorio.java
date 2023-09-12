package com.example.DesafioLapes.repositorios;

import com.example.DesafioLapes.dominio.usario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {

    Optional<Usuario> findById(String id);
}
