package org.thiago.authentication_skeleton.auth.application.dtos.mappers;

import org.thiago.authentication_skeleton.auth.application.dtos.LoginRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.LoginResponseDto;
import org.thiago.authentication_skeleton.auth.domain.AuthUser;

public class LoginDtoMapper {
    public AuthUser toDomain(LoginRequestDto dto) {
        return new AuthUser(dto.email(), dto.password());
    }
    public LoginResponseDto toResponse(AuthUser user) {
        return new LoginResponseDto(user.getToken());
    }
}
