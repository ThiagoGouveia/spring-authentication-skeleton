package org.thiago.authentication_skeleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.LoginDtoMapper;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.RegisterDtoMapper;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenValidator;
import org.thiago.authentication_skeleton.auth.application.usecases.LoginUseCase;
import org.thiago.authentication_skeleton.auth.application.usecases.RegisterUseCase;
import org.thiago.authentication_skeleton.auth.application.ports.out.AuthenticationChecker;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenGenerator;
import org.thiago.authentication_skeleton.auth.infrastructure.adapters.JwtValidator;
import org.thiago.authentication_skeleton.auth.infrastructure.adapters.SpringAuthenticationChecker;
import org.thiago.authentication_skeleton.user.application.ports.out.PasswordHasher;
import org.thiago.authentication_skeleton.user.domain.UserRepository;

@Configuration
public class AuthConfig {

    @Bean
    public RegisterUseCase registerUseCase(UserRepository userRepository, RegisterDtoMapper userDtoMapper, PasswordHasher passwordEncoder, TokenGenerator tokenGenerator) {
        return new RegisterUseCase(userRepository, userDtoMapper, passwordEncoder, tokenGenerator);
    }

    @Bean
    public LoginUseCase loginUseCase(TokenGenerator tokenGenerator, AuthenticationChecker authenticationChecker, LoginDtoMapper loginDtoMapper, UserRepository userRepository) {
        return new LoginUseCase(tokenGenerator, authenticationChecker, loginDtoMapper, userRepository);
    }

    @Bean
    public AuthenticationChecker authenticationChecker(AuthenticationManager authenticationManager) {
        return new SpringAuthenticationChecker(authenticationManager);
    }

    @Bean
    TokenValidator tokenValidator() {
        return new JwtValidator();
    }

    @Bean
    public RegisterDtoMapper registerDtoMapper() {
        return new RegisterDtoMapper();
    }

    @Bean
    public LoginDtoMapper loginDtoMapper() {
        return new LoginDtoMapper();
    }
}
