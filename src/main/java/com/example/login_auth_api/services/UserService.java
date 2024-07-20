package com.example.login_auth_api.services;

import com.example.login_auth_api.Domain.user.User;
import com.example.login_auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public User updateAuthenticatedUser(User updatedUser) {
        User currentUser = getAuthenticatedUser();
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());

        return userRepository.save(currentUser);
    }

    public void deleteAuthenticatedUser() {
        User currentUser = getAuthenticatedUser();
        userRepository.deleteById(currentUser.getId());
    }

    public void updatePassword(String currentPassword, String newPassword) {
        User currentUser = getAuthenticatedUser();
        if (!passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(currentUser);
    }
}
