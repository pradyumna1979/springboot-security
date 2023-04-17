package com.security.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotNull(message = "User name should not be null")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String roles;

}
