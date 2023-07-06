package com.js.springbootcrmbackend.repository;

import com.js.springbootcrmbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUniqueString (String uniqueString);
    Optional<User> findByEmail(String email);

}
