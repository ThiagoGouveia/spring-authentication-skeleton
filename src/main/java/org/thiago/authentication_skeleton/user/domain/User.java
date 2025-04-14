package org.thiago.authentication_skeleton.user.domain;

public class User {
    private String uuid;
    private String email;
    private String password;
    private UserRole role;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String uuid, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }
}
