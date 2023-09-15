package com.example.DesafioLapes.domain.user;

public enum UserRole {
    ADMIN("ADMIN"),
    COMMON("COMMON");

    private String role;
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
