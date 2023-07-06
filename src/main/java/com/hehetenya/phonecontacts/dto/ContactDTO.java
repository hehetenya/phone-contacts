package com.hehetenya.phonecontacts.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ContactDTO {
    @NotBlank(message = "Login must not be blank.")
    private String name;

    private List<String> emails;
    private List<String> phones;
}
