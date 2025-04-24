package org.thiago.authentication_skeleton.auth.domain.exceptions;

public class TokenGenerationFailedException extends RuntimeException {
    public TokenGenerationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
