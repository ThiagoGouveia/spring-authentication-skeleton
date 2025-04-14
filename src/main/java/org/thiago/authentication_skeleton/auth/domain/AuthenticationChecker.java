package org.thiago.authentication_skeleton.auth.domain;

public interface AuthenticationChecker {
    void check(AuthUser user);
}
