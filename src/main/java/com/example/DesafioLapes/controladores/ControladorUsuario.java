package com.example.DesafioLapes.controladores;

import com.example.DesafioLapes.dominio.usario.Usuario;
import com.example.DesafioLapes.dominio.usario.UsuarioDTO;
import com.example.DesafioLapes.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {
    @Autowired
    private UsuarioRepositorio repositorio;
    @GetMapping
    public ResponseEntity listarTodosOsUsuarios(){
        var todosUsarios = repositorio.findAll();
        return ResponseEntity.ok(todosUsarios);
    }
    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody @Valid UsuarioDTO data){
        Usuario novoUsuario = new Usuario(data);
        repositorio.save(novoUsuario);
        return ResponseEntity.ok("Deubom");
    }

}
