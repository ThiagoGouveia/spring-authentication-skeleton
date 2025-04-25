package org.thiago.authentication_skeleton.user.domain.exceptions;

public class EmailDoNotExistsException extends RuntimeException{
    public EmailDoNotExistsException(String message) {
        super(message);
    }
}
