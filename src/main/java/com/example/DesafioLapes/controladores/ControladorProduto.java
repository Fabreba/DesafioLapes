package com.example.DesafioLapes.controladores;

import com.example.DesafioLapes.dominio.categoria.Categoria;
import com.example.DesafioLapes.dominio.produto.Produto;
import com.example.DesafioLapes.dominio.produto.ProdutoDTO;
import com.example.DesafioLapes.repositorios.CategoriaRepositorio;
import com.example.DesafioLapes.repositorios.ProdutoRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("produto")
public class ControladorProduto {
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @PostMapping
    public ResponseEntity adicionarProduto(@RequestBody @Valid ProdutoDTO data){
        Optional<Produto> produtoExistente = this.produtoRepositorio.findByNome(data.nome());
        Optional<Categoria> categoriaExistente = this.categoriaRepositorio.findCategoriaByNome(data.nome_categoria());
        boolean produtoBool = produtoExistente.isPresent();
        boolean categoriaBool = categoriaExistente.isPresent();
        if( produtoBool || !categoriaBool){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        Produto novoProduto = new Produto(data.nome(),data.preco(), data.nome_categoria());
        produtoRepositorio.save(novoProduto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity removerProduto(@RequestBody @Valid ProdutoDTO data){
        Optional<Produto> produtoExiste =  this.produtoRepositorio.findByNome(data.nome());
        if (produtoExiste.isPresent()){
            Produto produto = produtoExiste.get();
            produtoRepositorio.delete(produto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }
}
