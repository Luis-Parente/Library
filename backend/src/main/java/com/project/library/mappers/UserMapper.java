package com.project.library.mappers;

import org.springframework.stereotype.Component;

import com.project.library.dto.RequestUserDTO;
import com.project.library.dto.ResponseUserDTO;
import com.project.library.entities.SystemUser;
import com.project.library.entities.enums.Role;

@Component
public class UserMapper {

    public SystemUser toEntity(RequestUserDTO dto, String password, Role role) {
        return new SystemUser(
                null,
                dto.username(),
                password,
                role);
    }

    public ResponseUserDTO toDto(SystemUser entity) {
        return new ResponseUserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getRole());
    }
}
