package com.example.DesafioLapes.dominio.usario;

import jakarta.persistence.*;
import lombok.*;


@Entity(name="usuario")
@Table(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private Float saldo;

    @Enumerated(EnumType.STRING)
    private UsuarioCargo cargo;

    public Usuario(UsuarioDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.senha = data.senha();

    }

    @PrePersist
    public void persistencia() {
        if (this.cargo == null) {
            this.cargo = UsuarioCargo.COMUM;
        }
        if (this.saldo == null) {
            this.saldo = 0.0f;
        }
    }
}
