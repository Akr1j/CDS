package cz.esnhk.cds.util;

import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;

public class TokenHandler {

    public static int getExpirationTime(String token) {
        Instant expiresAt = JWT.decode(token).getExpiresAtAsInstant();
        Duration duration = Duration.between(Instant.now(), expiresAt);
        return (int) duration.toSeconds();
    }
}
