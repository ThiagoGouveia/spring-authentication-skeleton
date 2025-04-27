package org.thiago.authentication_skeleton.auth.infrastructure.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thiago.authentication_skeleton.auth.infrastructure.CustomUserDetails;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepositoryPort;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryPort repository;

    public UserDetailsServiceImpl(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
