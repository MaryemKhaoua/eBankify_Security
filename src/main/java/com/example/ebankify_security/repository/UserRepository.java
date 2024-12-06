package com.example.ebankify_security.repository;

import com.example.ebankify_security.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
