package org.thiago.authentication_skeleton.user.infrastructure;

import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.infrastructure.persistence.UserEntity;

public class UserMapper {

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getEmail(), userEntity.getUuid().toString(), userEntity.getPassword(), userEntity.getUserRole());
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(user.getEmail(), user.getPassword(), user.getRole());
    }
}
