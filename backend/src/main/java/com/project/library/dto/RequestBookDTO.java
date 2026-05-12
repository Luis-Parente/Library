package com.project.library.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestBookDTO(
                @NotBlank(message = "Title must not be blank") @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters") String title,
                @NotBlank(message = "Author must not be blank") @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters") String author,
                LocalDate publishedDate) {
}