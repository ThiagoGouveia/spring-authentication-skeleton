package org.thiago.authentication_skeleton.auth.infrastructure.adapters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.thiago.authentication_skeleton.auth.domain.AuthUser;
import org.thiago.authentication_skeleton.auth.application.ports.out.AuthenticationChecker;


public class SpringAuthenticationChecker implements AuthenticationChecker {
    private final AuthenticationManager authenticationManager;

    public SpringAuthenticationChecker(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void check(AuthUser user)  {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }
}
