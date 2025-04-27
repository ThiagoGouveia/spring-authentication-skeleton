package org.thiago.authentication_skeleton.user.infrastructure.adapters;

import org.springframework.stereotype.Component;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepositoryPort;
import org.thiago.authentication_skeleton.user.infrastructure.UserMapper;
import org.thiago.authentication_skeleton.user.infrastructure.persistence.JpaUserRepository;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        this.mapper.toDomain(this.jpaUserRepository.save(this.mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jpaUserRepository.findByEmail(email))
                .map(mapper::toDomain);
    }
}
