package org.thiago.authentication_skeleton.user.infrastructure.persistence;

import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepository;
import org.thiago.authentication_skeleton.user.infrastructure.UserMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.userRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        this.userMapper.toDomain(this.userRepository.save(this.userMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);

        if (userEntity == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.userMapper.toDomain(userEntity));
    }
}
