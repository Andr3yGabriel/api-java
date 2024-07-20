package com.example.login_auth_api.controllers;

import com.example.login_auth_api.Domain.user.User;
import com.example.login_auth_api.dto.PasswordUpdateRequest;
import com.example.login_auth_api.dto.UserResponse;
import com.example.login_auth_api.dto.UserUpdateResponse;
import com.example.login_auth_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        User authenticatedUser = userService.getAuthenticatedUser();
        UserResponse userResponse = new UserResponse(authenticatedUser.getName(), authenticatedUser.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateResponse userUpdateResponse) {
        User updatedUser = userService.updateAuthenticatedUser(new User(
                null,
                userUpdateResponse.name(),
                userUpdateResponse.email(),
                null
        ));

        UserResponse userResponse = new UserResponse(updatedUser.getName(), updatedUser.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        userService.updatePassword(passwordUpdateRequest.currentPassword(), passwordUpdateRequest.newPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        userService.deleteAuthenticatedUser();
        return ResponseEntity.noContent().build();
    }
}
