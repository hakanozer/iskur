package com.works.repositories;

import com.works.entities.JWTUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTUserRepository extends JpaRepository<JWTUser, Long> {

    Optional<JWTUser> findByEmailEqualsIgnoreCase(String email);

}