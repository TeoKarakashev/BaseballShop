package com.softuni.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min = 1, message = "First name is required")
    private String firstName;
    @Size(min = 1, message = "Last name is required")
    private String lastName;
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @Pattern(regexp = "\\S+@\\S+", message = "Email must be valid")
    private String email;
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;
    @Min(value = 0, message = "Age must be positive")
    private int age;
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
