package com.project.library.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.library.dto.RequestUserDTO;
import com.project.library.dto.ResponseUserDTO;
import com.project.library.entities.SystemUser;
import com.project.library.entities.enums.Role;
import com.project.library.exceptions.DataIntegrityException;
import com.project.library.mappers.UserMapper;
import com.project.library.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    public UserService(UserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Transactional
    public ResponseUserDTO createUser(RequestUserDTO dto) {
        try {
            String encodedPassword = encoder.encode(dto.password());
            SystemUser newUser = repository.save(mapper.toEntity(dto, encodedPassword, Role.USER));
            
            return mapper.toDto(newUser);

        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Username already exists!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username!"));
    }
}
