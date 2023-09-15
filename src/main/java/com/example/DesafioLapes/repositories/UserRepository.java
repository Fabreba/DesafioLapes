package com.example.DesafioLapes.repositories;

import com.example.DesafioLapes.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    UserDetails findByEmail(String email);
    Optional<Boolean> existsUsuarioByEmail(String email);
}
