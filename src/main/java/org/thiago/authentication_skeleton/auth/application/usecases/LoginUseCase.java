package org.thiago.authentication_skeleton.auth.application.usecases;

import org.thiago.authentication_skeleton.auth.application.dtos.LoginRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.LoginResponseDto;
import org.thiago.authentication_skeleton.auth.application.dtos.mappers.LoginDtoMapper;
import org.thiago.authentication_skeleton.auth.domain.AuthUser;
import org.thiago.authentication_skeleton.auth.application.ports.out.AuthenticationChecker;
import org.thiago.authentication_skeleton.auth.domain.Token;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenGenerator;
import org.thiago.authentication_skeleton.user.domain.UserRepository;
import org.thiago.authentication_skeleton.user.domain.exceptions.EmailDoNotExistsException;

public class LoginUseCase {
    private final TokenGenerator tokenGenerator;
    private final AuthenticationChecker authenticationChecker;
    private final LoginDtoMapper loginDtoMapper;
    private final UserRepository userRepository;

    public LoginUseCase(TokenGenerator tokenGenerator, AuthenticationChecker authenticationChecker, LoginDtoMapper loginDtoMapper, UserRepository userRepository) {
        this.tokenGenerator = tokenGenerator;
        this.authenticationChecker = authenticationChecker;
        this.loginDtoMapper = loginDtoMapper;
        this.userRepository = userRepository;
    }

    public LoginResponseDto execute(LoginRequestDto dto) {
        if (userRepository.findByEmail(dto.email()).isEmpty()) {
            throw new EmailDoNotExistsException("Email do not exists");
        }

        AuthUser user = this.loginDtoMapper.toDomain(dto);
        this.authenticationChecker.check(user);
        user.setToken(new Token(this.tokenGenerator.generate(user.getEmail())));
        return this.loginDtoMapper.toResponse(user);
    }
}
