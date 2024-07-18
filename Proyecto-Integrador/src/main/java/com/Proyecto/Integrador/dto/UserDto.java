package com.Proyecto.Integrador.dto;

public class UserDto {
    private String id;
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;

    public UserDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
