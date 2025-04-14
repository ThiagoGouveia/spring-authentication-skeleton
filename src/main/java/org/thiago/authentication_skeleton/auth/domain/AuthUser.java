package org.thiago.authentication_skeleton.auth.domain;

public class AuthUser {
    private String email;
    private String password;
    private Token token;

    public AuthUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token.getValue();
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
