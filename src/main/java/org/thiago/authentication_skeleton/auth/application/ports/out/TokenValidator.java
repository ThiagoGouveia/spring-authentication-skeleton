package org.thiago.authentication_skeleton.auth.application.ports.out;

public interface TokenValidator {
    String validate(String token);
}
