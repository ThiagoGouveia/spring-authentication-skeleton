package org.thiago.authentication_skeleton.auth.infrastructure;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.thiago.authentication_skeleton.auth.domain.AuthUser;
import org.thiago.authentication_skeleton.auth.domain.AuthenticationChecker;


public class AuthenticationCheckerImpl implements AuthenticationChecker {
    private final AuthenticationManager authenticationManager;

    public AuthenticationCheckerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void check(AuthUser user)  {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }
}
