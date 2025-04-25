package org.thiago.authentication_skeleton.user.application.ports.out;

public interface PasswordHasher {
    String encode(String rawPassword);
}
