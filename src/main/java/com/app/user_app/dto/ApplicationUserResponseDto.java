package com.app.user_app.dto;

import com.app.user_app.model.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserResponseDto {

    private String name;
    private String email;
    private Roles role;

}