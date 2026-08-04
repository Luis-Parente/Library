package com.project.library.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.entities.SystemUser;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SystemUser, UUID> {

    Optional<SystemUser> findByUsername(String username);

}
