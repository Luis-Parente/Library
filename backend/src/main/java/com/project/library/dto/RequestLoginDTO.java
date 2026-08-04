package com.project.library.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestLoginDTO(
        @NotBlank(message = "Username must not be blank") String username,
        @NotBlank(message = "Password must not be blank") String password) {}
