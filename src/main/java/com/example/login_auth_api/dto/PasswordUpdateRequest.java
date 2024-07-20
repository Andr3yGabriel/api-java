package com.example.login_auth_api.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordUpdateRequest(
        @NotBlank(message = "Current password is mandatory")
        String currentPassword,

        @NotBlank(message = "New password is mandatory")
        String newPassword
) {}
