package org.thiago.authentication_skeleton.auth.domain;

public class Token {
    private String value;

    public Token(String token) {
        this.value = token;
    }

    public String getValue() {
        return value;
    }
}
