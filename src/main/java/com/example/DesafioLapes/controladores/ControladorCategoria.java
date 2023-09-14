package com.example.DesafioLapes.controladores;

import com.example.DesafioLapes.dominio.categoria.Categoria;
import com.example.DesafioLapes.dominio.categoria.CategoriaDTO;
import com.example.DesafioLapes.repositorios.CategoriaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("categoria")
public class ControladorCategoria {

    @Autowired
    private CategoriaRepositorio repositorio;

    @PostMapping
    public ResponseEntity adicionarCategoria(@RequestBody @Valid CategoriaDTO data){
        Optional<Categoria> categoriaExistente = this.repositorio.findCategoriaByNome(data.nome());
        if (categoriaExistente.isPresent()){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
        Categoria novaCategoria = new Categoria(data.nome());
        repositorio.save(novaCategoria);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removerCategoria(@RequestBody @Valid CategoriaDTO data){
        Optional<Categoria> categoriaExiste =  this.repositorio.findCategoriaByNome(data.nome());
        if (categoriaExiste.isPresent()){
            Categoria categoria = categoriaExiste.get();
            repositorio.delete(categoria);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

}
