package com.example.DesafioLapes.dominio.categoria;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "categoria")
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }
}
