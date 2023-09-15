package com.example.DesafioLapes.infra.seguranca;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.DesafioLapes.dominio.usario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServico {
    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-login")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
                throw new RuntimeException("Error while generating token", exception);
        }
    }
    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-login")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
    private Instant gerExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
