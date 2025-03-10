package com.project.selfservice.domain.user.authentication;

import jakarta.validation.constraints.NotBlank;

public record DataAuthentication(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
