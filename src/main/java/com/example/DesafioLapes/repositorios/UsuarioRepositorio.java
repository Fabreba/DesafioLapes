package com.example.DesafioLapes.repositorios;

import com.example.DesafioLapes.dominio.usario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {

    UserDetails findByEmail(String email);
    Optional<Boolean> existsUsuarioByEmail(String email);
}
