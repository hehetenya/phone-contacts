package com.hehetenya.phonecontacts.service;

import com.hehetenya.phonecontacts.entity.User;
import com.hehetenya.phonecontacts.repository.UserRepository;
import com.hehetenya.phonecontacts.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("LOGIN:" + s);
        Optional<User> user = userRepository.getByLogin(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException(String.format("User with login %s was not found", s));

        return new UserDetailsImpl(user.get());
    }
}
