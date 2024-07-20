package com.example.login_auth_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateResponse(
        @NotBlank(message = "Nome é obrigatório!")
        String name,

        @Email(message = "Endereço de email é inválido!")
        @NotBlank(message = "Email é obrigatório")
        String email
) {}
