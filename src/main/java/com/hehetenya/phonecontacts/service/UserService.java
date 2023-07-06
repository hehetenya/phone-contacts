package com.hehetenya.phonecontacts.service;

import com.hehetenya.phonecontacts.dto.UserDTO;
import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.repository.UserRepository;
import com.hehetenya.phonecontacts.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public Optional<User> getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Transactional
    public Map<String, String> register(UserDTO userDTO, BindingResult bindingResult) {
        User user = modelMapper.map(userDTO, User.class);
//        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) throw new IllegalArgumentException("Registration error");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }

    public Map<String, String> login(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword());
        authenticationManager.authenticate(authInputToken);
        User user = modelMapper.map(userDTO, User.class);
        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }
}
