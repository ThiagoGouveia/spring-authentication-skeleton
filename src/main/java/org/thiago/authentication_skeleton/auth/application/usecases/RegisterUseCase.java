package org.thiago.authentication_skeleton.auth.application.usecases;

import org.thiago.authentication_skeleton.auth.application.dtos.mappers.RegisterDtoMapper;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterResponseDto;
import org.thiago.authentication_skeleton.auth.application.ports.out.TokenGenerator;
import org.thiago.authentication_skeleton.user.domain.exceptions.EmailAlreadyExistsException;
import org.thiago.authentication_skeleton.user.application.ports.out.PasswordHasher;
import org.thiago.authentication_skeleton.user.domain.User;
import org.thiago.authentication_skeleton.user.domain.UserRepository;


public class RegisterUseCase {
    private final UserRepository userRepository;
    private final RegisterDtoMapper userDtoMapper;
    private final PasswordHasher passwordEncoder;
    private final TokenGenerator tokenGenerator;


    public RegisterUseCase(
            UserRepository userRepository,
            RegisterDtoMapper userDtoMapper,
            PasswordHasher passwordEncoder,
            TokenGenerator tokenGenerator
    ) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public RegisterResponseDto execute(RegisterRequestDto userRequestDto) throws EmailAlreadyExistsException {
        User domainUser = this.userDtoMapper.toDomain(userRequestDto);
        domainUser.setPassword(this.passwordEncoder.encode(domainUser.getPassword()));

        if (userRepository.findByEmail(domainUser.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already taken");
        }

        this.userRepository.save(domainUser);

        String generatedToken = this.tokenGenerator.generate(domainUser.getEmail());
        return new RegisterResponseDto(generatedToken);
    }
}
