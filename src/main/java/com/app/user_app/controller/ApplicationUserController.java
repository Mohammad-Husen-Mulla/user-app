package com.app.user_app.controller;

import com.app.user_app.dto.ApplicationUserRequestDto;
import com.app.user_app.dto.ApplicationUserResponseDto;
import com.app.user_app.dto.ApplicationUserUpdateDto;
import com.app.user_app.dto.PasswordUpdateDto;
import com.app.user_app.service.ApplicationUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/user")
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ApplicationUserRequestDto userRequestDto) {
        Long id = applicationUserService.registerUser(userRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/get/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body("User registered successfully!");
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        ApplicationUserResponseDto user = applicationUserService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody ApplicationUserUpdateDto userUpdateDto) {
        Long responseId
                = applicationUserService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok("User details updated successfully!");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        applicationUserService.deleteUser(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("update/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody PasswordUpdateDto passwordUpdateDto) {
        applicationUserService.resetAppPassword(passwordUpdateDto.getOldPassword(),
                passwordUpdateDto.getNewPassword(),
                passwordUpdateDto.getEmail());
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }

}