package com.example.DesafioLapes.controllers;


import com.example.DesafioLapes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repositorio;
    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUsers = repositorio.findAll();
        return ResponseEntity.ok(allUsers);
    }


}
