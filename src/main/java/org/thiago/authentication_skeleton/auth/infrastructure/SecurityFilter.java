package org.thiago.authentication_skeleton.auth.infrastructure;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenGenerator;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenValidator;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepositoryPort;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    TokenGenerator tokenGenerator;
    TokenValidator tokenValidator;
    UserRepositoryPort userRepository;

    public SecurityFilter(TokenGenerator tokenGenerator, TokenValidator tokenValidator, UserRepositoryPort userRepository) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String email = tokenValidator.validate(token);
            User user = this.userRepository.findByEmail(email).orElseThrow();

            UserDetails authUser = new CustomUserDetails(user);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}