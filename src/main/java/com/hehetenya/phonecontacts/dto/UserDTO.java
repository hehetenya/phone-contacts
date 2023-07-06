package com.hehetenya.phonecontacts.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {
    @NotBlank(message = "Login must not be blank.")
    private String login;
    @NotBlank(message = "Password must not be blank.")
    private String password;
}
