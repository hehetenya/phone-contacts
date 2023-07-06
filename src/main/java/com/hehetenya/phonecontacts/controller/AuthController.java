package com.hehetenya.phonecontacts.controller;


import com.hehetenya.phonecontacts.dto.UserDTO;
import com.hehetenya.phonecontacts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        return userService.register(userDTO, bindingResult);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid UserDTO userDTO) {
        return userService.login(userDTO);
    }
}
