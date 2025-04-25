package org.thiago.authentication_skeleton.auth.application.ports.out;

public interface TokenGenerator {
    String generate(String email);
}
