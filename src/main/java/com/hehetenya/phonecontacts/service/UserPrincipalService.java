package com.hehetenya.phonecontacts.service;

import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.repository.UserRepository;
import com.hehetenya.phonecontacts.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByLogin(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException(String.format("User with login %s was not found", s));

        return new UserPrincipal(user.get());
    }
}
