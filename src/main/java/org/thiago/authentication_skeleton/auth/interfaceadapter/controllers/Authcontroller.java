package org.thiago.authentication_skeleton.auth.interfaceadapter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thiago.authentication_skeleton.auth.application.dtos.LoginRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.LoginResponseDto;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterRequestDto;
import org.thiago.authentication_skeleton.auth.application.dtos.RegisterResponseDto;
import org.thiago.authentication_skeleton.auth.application.usecases.LoginUseCase;
import org.thiago.authentication_skeleton.auth.application.usecases.RegisterUseCase;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    public Authcontroller(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(this.loginUseCase.execute(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {
        return ResponseEntity.ok(this.registerUseCase.execute(dto));
    }
}
