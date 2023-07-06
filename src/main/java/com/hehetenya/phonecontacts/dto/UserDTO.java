package com.hehetenya.phonecontacts.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank(message = "Login must not be blank.")
    private String login;
    @NotBlank(message = "Password must not be blank.")
    private String password;
}
