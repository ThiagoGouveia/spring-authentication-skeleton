package org.thiago.authentication_skeleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thiago.authentication_skeleton.user.application.usecases.GetMyUserUseCase;
import org.thiago.authentication_skeleton.user.domain.UserAlreadyExistsChecker;
import org.thiago.authentication_skeleton.user.domain.UserRepository;
import org.thiago.authentication_skeleton.user.infrastructure.UserAlreadyExistsCheckerImpl;
import org.thiago.authentication_skeleton.user.infrastructure.UserMapper;
import org.thiago.authentication_skeleton.user.infrastructure.persistence.JpaUserRepository;
import org.thiago.authentication_skeleton.user.infrastructure.persistence.UserRepositoryImpl;

@Configuration
public class UserConfig {
    @Bean
    public GetMyUserUseCase createUserUseCase(UserRepository userRepository) {
        return new GetMyUserUseCase(userRepository);
    }

    @Bean
    public UserRepositoryImpl userRepository(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        return new UserRepositoryImpl(jpaUserRepository, userMapper);
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserAlreadyExistsChecker userAlreadyExistsChecker(UserRepositoryImpl userRepository) {
        return new UserAlreadyExistsCheckerImpl(userRepository);
    }
}
