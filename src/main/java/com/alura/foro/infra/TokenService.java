package com.alura.foro.infra;

import com.alura.foro.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class TokenService {

    @Value("${api.security.expiration}")
    private Integer apiExpiration;

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create().withIssuer("foro")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if(token == null) {
            throw new RuntimeException("Token nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro")
                    .build()
                    .verify(token);
            String subject = verifier.getSubject();
            if(subject == null) {
                throw new RuntimeException("Verificador Invalido");
            }
            return subject;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Token Invalido", e);
        }
    }
    private Instant generarFechaExpiracion() {
        return ZonedDateTime.now(ZoneOffset.of("+03:00"))
                .plusHours(apiExpiration)
                .toInstant();
    }
}
