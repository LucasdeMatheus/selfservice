package com.project.selfservice.domain.user.authentication;

import com.project.selfservice.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteLogin(
        @NotNull User user,
        @NotBlank String login
) {
}
