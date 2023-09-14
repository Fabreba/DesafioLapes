package com.example.DesafioLapes.dominio.produto;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "produto")
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String nome;
    private Float preco;
    private String nome_categoria;

    public Produto(String nome, Float preco, String nome_categoria) {
        this.nome = nome;
        this.preco = preco;
        this.nome_categoria = nome_categoria;
    }
}
