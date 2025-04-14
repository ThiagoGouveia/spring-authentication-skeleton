package org.thiago.authentication_skeleton.auth.application.dtos.mappers;

import org.thiago.authentication_skeleton.auth.application.dtos.RegisterRequestDto;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRole;

public class RegisterDtoMapper {
    public User toDomain(RegisterRequestDto dto) {
        return new User(dto.email(), dto.password(), UserRole.USER);
    }
}
