package com.evms.www.repository;

import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById (Long id);
    Optional<User> getUserByEmail (String email);
}
