package org.thiago.authentication_skeleton.auth.application.usecases;

import org.thiago.authentication_skeleton.auth.application.dtos.LoginRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.LoginResponseDto;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.LoginDtoMapper;
import org.thiago.authentication_skeleton.auth.domain.AuthUser;
import org.thiago.authentication_skeleton.auth.domain.AuthenticationChecker;
import org.thiago.authentication_skeleton.auth.domain.Token;
import org.thiago.authentication_skeleton.auth.domain.TokenGenerator;

public class LoginUseCase {
    private final TokenGenerator tokenGenerator;
    private final AuthenticationChecker authenticationChecker;
    private final LoginDtoMapper loginDtoMapper;

    public LoginUseCase(TokenGenerator tokenGenerator, AuthenticationChecker authenticationChecker, LoginDtoMapper loginDtoMapper) {
        this.tokenGenerator = tokenGenerator;
        this.authenticationChecker = authenticationChecker;
        this.loginDtoMapper = loginDtoMapper;
    }

    public LoginResponseDto execute(LoginRequestDto dto) {
        AuthUser user = this.loginDtoMapper.toDomain(dto);
        this.authenticationChecker.check(user);
        user.setToken(new Token(this.tokenGenerator.generateToken(user.getEmail())));
        return this.loginDtoMapper.toResponse(user);
    }
}
