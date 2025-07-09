package br.com.secret.friend.service.security;

import br.com.secret.friend.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generatedToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Secret.friend")
                    .withSubject(user.getEmail())
                    .withClaim("user_id", user.getIdUser())
                    .withExpiresAt(dateExpired())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error generated token", exception);
        }
    }

    public String getSubject(String tokenJWt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Secret.friend")
                    .build()
                    .verify(tokenJWt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("tokenJWT invalid", exception);
        }
    }

    public Instant dateExpired(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
