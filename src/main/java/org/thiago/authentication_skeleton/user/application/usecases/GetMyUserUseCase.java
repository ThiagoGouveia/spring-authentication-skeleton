package org.thiago.authentication_skeleton.user.application.usecases;

import org.thiago.authentication_skeleton.user.application.dtos.GetMyUserResponseDto;
import org.thiago.authentication_skeleton.user.application.exceptions.UserNotFoundException;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepositoryPort;

import java.util.Optional;

public class GetMyUserUseCase {

    private final UserRepositoryPort userRepository;

    public GetMyUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public GetMyUserResponseDto execute(String email) {
        Optional<User> domainUser = this.userRepository.findByEmail(email);

        if (domainUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = domainUser.get();
        return new GetMyUserResponseDto(user.getUuid(), user.getEmail(), user.getRole());
    }
}
