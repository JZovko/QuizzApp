package com.quiz_app.service;

import com.quiz_app.dao.RoleDao;
import com.quiz_app.model.Role;
import com.quiz_app.repository.RoleRepository;
import com.quiz_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.quiz_app.dao.UserDao;
import com.quiz_app.model.User;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Pretpostavljam da postoji RoleRepository za dohvat uloga

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String email, String gender, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email); // Dodano polje email
        user.setGender(gender); // Dodano polje gender

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role does not exist");
        }
        user.setRoles(Collections.singleton(role)); // Postavite ulogu korisniku

        userRepository.save(user);
    }
}