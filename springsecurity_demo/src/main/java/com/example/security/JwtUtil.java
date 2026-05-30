package com.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String jwtSecret = "my-secrete-key";
    private final long jwtExpirationMs = 864000000;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpirationMs);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm());
    }

    public String extractUsername(String token) {
        DecodedJWT decoded = JWT.require(algorithm()).build().verify(token);
        return decoded.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm()).build().verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
