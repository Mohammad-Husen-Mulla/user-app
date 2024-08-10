package com.app.user_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDto {

    @NotEmpty(message = "Old password cannot be empty")
    private String oldPassword;

    @Pattern(regexp = "[a-z]+ [0-9]+ [A-Z]+ [^a-zA-Z]+", message = "Password must contain \n  \t Atleast One Lower case letter \n \t One Uppercase letter \n \t One Special character \n \t 1 Number ")
    private String newPassword;

    @Email(message = "Invalid email format")
    private String email;
}