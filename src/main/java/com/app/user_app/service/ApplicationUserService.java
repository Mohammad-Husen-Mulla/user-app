package com.app.user_app.service;

import com.app.user_app.dto.ApplicationUserRequestDto;
import com.app.user_app.dto.ApplicationUserResponseDto;
import com.app.user_app.dto.ApplicationUserUpdateDto;

public interface ApplicationUserService {

    Long registerUser(ApplicationUserRequestDto userRequestDto);

    ApplicationUserResponseDto getUser(Long id);

    Long updateUser(Long id, ApplicationUserUpdateDto userRequestDto);

    void deleteUser(Long id);

    void resetAppPassword(String oldPassword, String newPassword, String email);
}