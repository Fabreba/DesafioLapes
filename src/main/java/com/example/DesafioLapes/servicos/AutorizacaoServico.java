package com.example.DesafioLapes.servicos;

import com.example.DesafioLapes.dominio.usario.Usuario;
import com.example.DesafioLapes.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorizacaoServico implements UserDetailsService {

    @Autowired
    UsuarioRepositorio repositorio;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repositorio.findByEmail(email);
    }
}
