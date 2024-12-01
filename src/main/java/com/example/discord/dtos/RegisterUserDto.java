package com.example.discord.dtos;

public class RegisterUserDto {
    private String email;
    private String password;
    private String display_name;
    private String username;
    private String dob;

    public RegisterUserDto(String email, String password, String display_name, String username, String dob) {
        this.email = email;
        this.password = password;
        this.display_name = display_name;
        this.username = username;
        this.dob = dob;
    }

    public RegisterUserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
