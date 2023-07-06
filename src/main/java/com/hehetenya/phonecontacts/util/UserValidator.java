package com.hehetenya.phonecontacts.util;

import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.annotation.MultipartConfig;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userService.getByLogin(user.getLogin()).isPresent()) {
            errors.rejectValue("login", "", "Login is already in use");
        }
    }
}
