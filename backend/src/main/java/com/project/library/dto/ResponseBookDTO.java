package com.project.library.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ResponseBookDTO(UUID id, String title, String author, LocalDate publishedDate) {  
}
