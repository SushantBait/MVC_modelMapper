package com.sprinboot.web_tutorial.service;

import com.sprinboot.web_tutorial.config.MapperConfig;
import com.sprinboot.web_tutorial.dto.SignUpDto;
import com.sprinboot.web_tutorial.dto.UserDto;
import com.sprinboot.web_tutorial.entity.User;
import com.sprinboot.web_tutorial.exceptions.ResourceNotFoundException;
import com.sprinboot.web_tutorial.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user with email "+username+" not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()){
           throw new BadCredentialsException("User with email already exits "+ signUpDto.getEmail());
        }
        User tobeCreatedUser = modelMapper.map(signUpDto, User.class);

        tobeCreatedUser.setPassword(passwordEncoder.encode(tobeCreatedUser.getPassword()));

        User savedUser = userRepository.save(tobeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
