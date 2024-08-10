package com.app.user_app.service.security;

import com.app.user_app.model.ApplicationUser;
import com.app.user_app.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUserOptional = applicationUserRepository.findByEmail(username);

        if (applicationUserOptional.isPresent()) {
            ApplicationUser applicationUser = applicationUserOptional.get();

            List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority(applicationUser.getRole().getRoleString()));
            return new User(applicationUser.getEmail(), applicationUser.getPassword(), authorities);
        }
        return null;
    }
}