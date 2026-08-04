package com.project.library.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.library.dto.CustomErrorDTO;
import com.project.library.dto.RequestUserDTO;
import com.project.library.dto.ResponseUserDTO;
import com.project.library.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Endpoints for user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register user", description = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = ResponseUserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = CustomErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = CustomErrorDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = CustomErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = CustomErrorDTO.class)))
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseUserDTO> registerUser(@RequestBody @Valid RequestUserDTO newUser) {
        ResponseUserDTO result = userService.createUser(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{uuid}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
