package org.thiago.authentication_skeleton.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
