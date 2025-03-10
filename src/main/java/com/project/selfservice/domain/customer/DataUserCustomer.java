package com.project.selfservice.domain.customer;

import com.project.selfservice.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataUserCustomer(
        @NotNull
        User user,

        @NotBlank
        String name,

        @NotBlank(message = "O telefone não pode estar em branco")
        @Pattern(regexp = "^\\d{11}$", message = "O telefone deve conter exatamente 11 dígitos")
        String phone
) {
}
