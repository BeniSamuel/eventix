package com.evms.www.service;

import com.evms.www.dto.RegisterDto;
import com.evms.www.model.User;
import com.evms.www.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers () {
        return this.userRepository.findAll();
    }

    public User getUserById (Long id) {
        return this.userRepository.getUserById(id).orElse(null);
    }

    public User getUserByEmail (String email) {
        return this.userRepository.getUserByEmail(email).orElse(null);
    }

    public User updateUserById (Long id, RegisterDto registerDto) {
        User user = getUserById(id);
        if (user != null) {
            user.setEmail(registerDto.getEmail());
            user.setName(registerDto.getName());
            user.setLocation(registerDto.getLocation());
            user.setRole(registerDto.getRole());
            user.setPhone(registerDto.getPhone());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            return this.userRepository.save(user);
        }
        return null;
    }

    public User createUser (RegisterDto registerDto) {
        User user = getUserByEmail(registerDto.getEmail());
        if (user == null) {
            User new_user = new User(registerDto.getName(), registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getPhone(), registerDto.getLocation(), registerDto.getRole());
            return this.userRepository.save(new_user);
        }
        return null;
    }

    public Boolean deleteUser (Long id) {
        User user = getUserById(id);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }
        return false;
    }
}
