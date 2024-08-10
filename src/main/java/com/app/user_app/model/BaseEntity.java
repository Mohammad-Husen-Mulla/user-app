package com.app.user_app.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    protected LocalDateTime lastModifiedAt;
    protected String lastModifiedBy;
}
