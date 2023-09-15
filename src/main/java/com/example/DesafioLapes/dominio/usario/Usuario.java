package com.example.DesafioLapes.dominio.usario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity(name="usuario")
@Table(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private Float saldo;

    private UsuarioCargo cargo;

    public Usuario(UsuarioDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.senha = data.senha();

    }

    public Usuario(String nome, String email, String senha, UsuarioCargo cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    @PrePersist
    public void persistencia() {
        if (this.saldo == null) {
            this.saldo = 0.0f;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.cargo == UsuarioCargo.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
