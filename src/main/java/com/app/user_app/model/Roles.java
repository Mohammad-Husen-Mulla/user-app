package com.app.user_app.model;

public enum Roles {
    USER, ADMIN;

    public String getRoleString() {
        return "ROLE_"+name();
    }
}