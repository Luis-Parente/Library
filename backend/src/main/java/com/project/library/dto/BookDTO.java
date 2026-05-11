package com.project.library.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record BookDTO(UUID id, @NotBlank String title, @NotBlank String author, LocalDate publishedDate) {
}