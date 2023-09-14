package com.example.DesafioLapes.controladores;


import com.example.DesafioLapes.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
