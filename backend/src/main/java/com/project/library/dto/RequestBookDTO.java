package com.project.library.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

public record RequestBookDTO(@NotBlank String title, @NotBlank String author, LocalDate publishedDate) {
}