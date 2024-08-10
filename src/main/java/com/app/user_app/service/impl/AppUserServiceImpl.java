package com.app.user_app.service.impl;

import com.app.user_app.dto.ApplicationUserRequestDto;
import com.app.user_app.dto.ApplicationUserResponseDto;
import com.app.user_app.dto.ApplicationUserUpdateDto;
import com.app.user_app.exception.ApplicationUserNotFoundException;
import com.app.user_app.exception.EmailAlreadyExistsException;
import com.app.user_app.exception.InvalidPasswordException;
import com.app.user_app.model.ApplicationUser;
import com.app.user_app.repository.ApplicationUserRepository;
import com.app.user_app.service.ApplicationUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long registerUser(ApplicationUserRequestDto userRequestDto) {

        if (userRequestDto == null) {
            throw new IllegalArgumentException("Method arguments are missing, provide valid arguments");
        }

        if (applicationUserRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email Already exists");
        }

        ApplicationUser user = modelMapper.map(userRequestDto, ApplicationUser.class);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        ApplicationUser savedUser = applicationUserRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public ApplicationUserResponseDto getUser(Long id) {
        ApplicationUser user = getExistingUser(id);
        ApplicationUserResponseDto userResponse = modelMapper.map(user, ApplicationUserResponseDto.class);
        return userResponse;
    }

    @Override
    public Long updateUser(Long id, ApplicationUserUpdateDto userRequestDto) {

        if (userRequestDto == null || id == null) {
            throw new IllegalArgumentException("Method arguments are missing, provide valid arguments");
        }
        ApplicationUser existingUser = getExistingUser(id);

        String loggedInEmail = getLoggedInEmail();

        if (!existingUser.getEmail().equals(loggedInEmail)) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setName(userRequestDto.getName());
        applicationUserRepository.save(existingUser);
        return id;
    }

    @Override
    public void deleteUser(Long id) {
        ApplicationUser user = getExistingUser(id);
        applicationUserRepository.delete(user);
    }

    @Override
    @Transactional
    public void resetApssword(String oldPassword, String newPassword, String email) {

        String loggedInEmail = getLoggedInEmail();

        if (!email.equals(loggedInEmail)) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        ApplicationUser existingUser = applicationUserRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationUserNotFoundException("User Not Found"));

        boolean checkPassword = BCrypt.checkpw(oldPassword, existingUser.getPassword());
        if (checkPassword) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            applicationUserRepository.save(existingUser);
        } else {
            throw new InvalidPasswordException("Password is not valid");
        }

    }

    private static String getLoggedInEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private ApplicationUser getExistingUser(Long id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new ApplicationUserNotFoundException(String.format("User with id %d not found...!", id)));
    }
}
