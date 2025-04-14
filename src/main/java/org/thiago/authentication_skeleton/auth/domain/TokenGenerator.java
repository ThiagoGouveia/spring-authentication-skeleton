package org.thiago.authentication_skeleton.auth.domain;

import java.time.Instant;

public interface TokenGenerator {
    String generateToken(String email);
    String validateToken(String token);
    Instant genExpirationDate();
}
