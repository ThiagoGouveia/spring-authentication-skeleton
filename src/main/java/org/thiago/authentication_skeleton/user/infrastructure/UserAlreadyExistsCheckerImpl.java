package org.thiago.authentication_skeleton.user.infrastructure;

import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserAlreadyExistsChecker;
import org.thiago.authentication_skeleton.user.infrastructure.persistence.UserRepositoryImpl;

public class UserAlreadyExistsCheckerImpl implements UserAlreadyExistsChecker {

    private final UserRepositoryImpl userRepository;

    public UserAlreadyExistsCheckerImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(User user) {
        return this.userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
