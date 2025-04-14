package org.thiago.authentication_skeleton.user.interfaceadapter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.thiago.authentication_skeleton.auth.infrastructure.CustomUserDetails;
import org.thiago.authentication_skeleton.user.application.dtos.GetMyUserResponseDto;
import org.thiago.authentication_skeleton.user.application.usecases.GetMyUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetMyUserUseCase getMyUserUseCase;

    public UserController(GetMyUserUseCase getMyUserUseCase) {
        this.getMyUserUseCase = getMyUserUseCase;
    }

    @GetMapping("/me")
    public ResponseEntity<GetMyUserResponseDto> getMyUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(this.getMyUserUseCase.execute(userDetails.getUser().getEmail()));
    }
}
