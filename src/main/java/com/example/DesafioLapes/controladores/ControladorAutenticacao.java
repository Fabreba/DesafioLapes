package com.example.DesafioLapes.controladores;

import com.example.DesafioLapes.dominio.usario.AutenticacaoDTO;
import com.example.DesafioLapes.dominio.usario.RegistroDTO;
import com.example.DesafioLapes.dominio.usario.Usuario;
import com.example.DesafioLapes.repositorios.UsuarioRepositorio;
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
public class ControladorAutenticacao {
    @Autowired
    private AuthenticationManager authenticationManager;
    private UsuarioRepositorio repositorio;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data){
        var senhaUsuario = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(senhaUsuario);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistroDTO data){
        Optional<Boolean> usuarioExistente = this.repositorio.existsUsuarioByEmail(data.email());
        if (usuarioExistente.isPresent() && usuarioExistente.get()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
        String senhaEncriptada = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.nome(), data.email(), senhaEncriptada, data.cargo());
        this.repositorio.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
