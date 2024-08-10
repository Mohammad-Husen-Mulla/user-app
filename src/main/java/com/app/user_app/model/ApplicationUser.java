package com.app.user_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ApplicationUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    @PostPersist
    @PostUpdate
    public void setLastModifiedValues() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !Objects.equals(authentication.getName(), "anonymousUser")) {
            setLastModifiedBy(authentication.getName());
        } else {
            setLastModifiedBy(this.email);
        }
        setLastModifiedAt(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}