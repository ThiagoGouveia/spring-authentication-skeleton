package org.thiago.authentication_skeleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thiago.authentication_skeleton.user.application.usecases.GetMyUserUseCase;
import org.thiago.authentication_skeleton.user.domain.UserRepositoryPort;
import org.thiago.authentication_skeleton.user.infrastructure.UserMapper;

@Configuration
public class UserConfig {
    @Bean
    public GetMyUserUseCase createUserUseCase(UserRepositoryPort userRepository) {
        return new GetMyUserUseCase(userRepository);
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
}
