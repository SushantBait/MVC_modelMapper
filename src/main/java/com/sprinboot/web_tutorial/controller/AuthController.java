package com.sprinboot.web_tutorial.controller;

import com.sprinboot.web_tutorial.dto.SignUpDto;
import com.sprinboot.web_tutorial.dto.UserDto;
import com.sprinboot.web_tutorial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        UserDto userDto= userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }
}
