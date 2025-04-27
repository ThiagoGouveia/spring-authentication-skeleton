package org.thiago.authentication_skeleton.user.domain;

import java.util.Optional;

public interface UserRepositoryPort {
    void save(User user);
    Optional<User> findByEmail(String email);
}
