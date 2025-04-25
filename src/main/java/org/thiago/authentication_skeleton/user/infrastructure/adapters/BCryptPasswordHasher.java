package org.thiago.authentication_skeleton.user.infrastructure.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thiago.authentication_skeleton.user.application.ports.out.PasswordHasher;

@Component
public class BCryptPasswordHasher implements PasswordHasher {
    private final PasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
