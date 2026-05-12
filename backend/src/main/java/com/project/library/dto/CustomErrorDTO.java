package com.project.library.dto;

public record CustomErrorDTO(Integer status, String message, String path) {
}