package com.hehetenya.phonecontacts.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class ContactDTO {
    @NotBlank(message = "Login must not be blank.")
    private String name;

    private Set<String> emails;
    private Set<String> phones;
}
