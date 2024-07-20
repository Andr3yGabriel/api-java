package com.example.login_auth_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO (
        @NotBlank(message = "Campo nome é obrigatório")
        String name,

        @Email(message = "O email é inválido")
        @NotBlank(message = "Campo email é obrigatório")
        String email,
        @NotBlank(message = "Campo senha é obrigatório")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
        String password
) {}
