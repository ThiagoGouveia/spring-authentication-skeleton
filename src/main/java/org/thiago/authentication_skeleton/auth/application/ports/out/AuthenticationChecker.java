package org.thiago.authentication_skeleton.auth.application.ports.out;

import org.thiago.authentication_skeleton.auth.domain.AuthUser;

public interface AuthenticationChecker {
    void check(AuthUser user);
}
