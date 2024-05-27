package com.bezkoder.springjwt.payload.request;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    private String username;
 
    @NotBlank
    @Size(max = 50)
    private String name;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String confirmPassword;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public @NotBlank @Size(max = 50) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 50) String name) {
        this.name = name;
    }

    public @NotBlank @Size(min = 6, max = 40) String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank @Size(min = 6, max = 40) String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
