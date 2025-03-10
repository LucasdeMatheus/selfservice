package com.project.selfservice.domain.user.authentication;

import com.project.selfservice.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull User user,
        @NotBlank String login,
        @NotBlank String newPassword
) {}
