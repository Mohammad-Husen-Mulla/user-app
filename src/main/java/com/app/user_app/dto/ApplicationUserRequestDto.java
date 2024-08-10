package com.app.user_app.dto;

import com.app.user_app.model.Roles;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserRequestDto {

    @Size(min = 2, max = 30, message = "Name should be in between 2-30 characters")
    @Pattern(regexp = "[A-Z-a-z ]+")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)[A-Za-z\\d\\W]{5,20}$\n", message = "Password must contain \n  \t Atleast One Lower case letter \n \t Atleast One Uppercase letter \n \t Atleast One Special character \n \t Atleast One Number ")
    @Size(min = 5, max = 20, message = "Password should be between 5-20 characters")
    private String password;

    @NotNull(message = "Role cannot be empty")
    private Roles role;
}