package com.example.DesafioLapes.controllers;

import com.example.DesafioLapes.domain.user.AuthenticationDTO;
import com.example.DesafioLapes.domain.user.LoginResponseDTO;
import com.example.DesafioLapes.domain.user.RegisterDTO;
import com.example.DesafioLapes.domain.user.User;
import com.example.DesafioLapes.infra.security.TokenService;
import com.example.DesafioLapes.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(userPassword);
        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok( new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterDTO data){
        System.out.println(data.role());
        Optional<Boolean> userExists = this.repository.existsUsuarioByEmail(data.email());
        if (userExists.isPresent() && userExists.get()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
        String passwordEncrypt = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), passwordEncrypt, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
