package org.thiago.authentication_skeleton.auth.application.usecases;

import org.thiago.authentication_skeleton.auth.application.dtos.mappers.RegisterDtoMapper;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterResponseDto;
import org.thiago.authentication_skeleton.auth.domain.TokenGenerator;
import org.thiago.authentication_skeleton.user.domain.exceptions.EmailAlreadyExistsException;
import org.thiago.authentication_skeleton.user.domain.PasswordEncoderPort;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserAlreadyExistsChecker;
import org.thiago.authentication_skeleton.user.domain.UserRepository;


public class RegisterUseCase {
    private final UserRepository userRepository;
    private final RegisterDtoMapper userDtoMapper;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final UserAlreadyExistsChecker userAlreadyExistsChecker;


    public RegisterUseCase(
            UserRepository userRepository,
            RegisterDtoMapper userDtoMapper,
            PasswordEncoderPort passwordEncoder,
            TokenGenerator tokenGenerator,
            UserAlreadyExistsChecker userAlreadyExistsChecker
    ) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.userAlreadyExistsChecker = userAlreadyExistsChecker;
    }

    public RegisterResponseDto execute(RegisterRequestDto userRequestDto) throws EmailAlreadyExistsException {
        User domainUser = this.userDtoMapper.toDomain(userRequestDto);
        domainUser.setPassword(this.passwordEncoder.encode(domainUser.getPassword()));

        if (this.userAlreadyExistsChecker.check(domainUser)) {
            throw new EmailAlreadyExistsException("Email already taken");
        }

        this.userRepository.save(domainUser);

        String generatedToken = this.tokenGenerator.generateToken(domainUser.getEmail());
        return new RegisterResponseDto(generatedToken);
    }
}
