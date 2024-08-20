package com.quiz_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz_app.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
}