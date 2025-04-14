package org.thiago.authentication_skeleton.user.domain;

public interface UserAlreadyExistsChecker {
    boolean check(User user);
}
