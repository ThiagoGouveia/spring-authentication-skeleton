package org.thiago.authentication_skeleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.LoginDtoMapper;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.RegisterDtoMapper;
import org.thiago.authentication_skeleton.auth.application.usecases.LoginUseCase;
import org.thiago.authentication_skeleton.auth.application.usecases.RegisterUseCase;
import org.thiago.authentication_skeleton.auth.domain.AuthenticationChecker;
import org.thiago.authentication_skeleton.auth.domain.TokenGenerator;
import org.thiago.authentication_skeleton.auth.infrastructure.AuthenticationCheckerImpl;
import org.thiago.authentication_skeleton.user.domain.PasswordEncoderPort;
import org.thiago.authentication_skeleton.user.domain.UserAlreadyExistsChecker;
import org.thiago.authentication_skeleton.user.domain.UserRepository;

@Configuration
public class AuthConfig {

    @Bean
    public RegisterUseCase registerUseCase(UserRepository userRepository, RegisterDtoMapper userDtoMapper, PasswordEncoderPort passwordEncoder, TokenGenerator tokenGenerator, UserAlreadyExistsChecker userAlreadyExistsChecker) {
        return new RegisterUseCase(userRepository, userDtoMapper, passwordEncoder, tokenGenerator, userAlreadyExistsChecker);
    }

    @Bean
    public LoginUseCase loginUseCase(TokenGenerator tokenGenerator, AuthenticationChecker authenticationChecker, LoginDtoMapper loginDtoMapper) {
        return new LoginUseCase(tokenGenerator, authenticationChecker, loginDtoMapper);
    }

    @Bean
    public AuthenticationChecker authenticationChecker(AuthenticationManager authenticationManager) {
        return new AuthenticationCheckerImpl(authenticationManager);
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
