package com.project.library.dto;

import java.util.UUID;

import com.project.library.entities.enums.Role;

public record ResponseUserDTO(
        UUID id,
        String username,
        Role role) {
}
