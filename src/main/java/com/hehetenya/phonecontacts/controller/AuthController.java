package com.hehetenya.phonecontacts.controller;


import com.hehetenya.phonecontacts.dto.UserDTO;
import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.security.JWTUtil;
import com.hehetenya.phonecontacts.service.UserService;
import com.hehetenya.phonecontacts.util.UserValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        User user = modelMapper.map(userDTO, User.class);
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) throw new IllegalArgumentException("Registration error");

        userService.register(user);
        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid UserDTO userDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            return Map.of("error", "Wrong credentials");
        }
        User user = modelMapper.map(userDTO, User.class);
        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }
}
