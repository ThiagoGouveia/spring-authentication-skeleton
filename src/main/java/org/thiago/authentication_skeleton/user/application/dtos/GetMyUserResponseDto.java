package org.thiago.authentication_skeleton.user.application.dtos;

import org.thiago.authentication_skeleton.user.domain.UserRole;

public record GetMyUserResponseDto(String uuid, String email, UserRole userRole) {
}
