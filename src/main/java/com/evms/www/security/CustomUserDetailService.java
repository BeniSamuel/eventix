package com.evms.www.security;

import com.evms.www.exceptions.NotFoundException;
import com.evms.www.model.User;
import com.evms.www.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Optional<User> user = this.userRepository.getUserByEmail(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        } else {
            throw new NotFoundException("Sorry user not found!!! 💔💔😔");
        }
    }
}
