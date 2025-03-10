package com.project.selfservice.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUserSeller(
        @NotNull
        User userAdmin,
        @NotNull
        User user,
        @NotBlank
        String name
) {
}
