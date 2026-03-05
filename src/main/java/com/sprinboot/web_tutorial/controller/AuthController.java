package com.sprinboot.web_tutorial.controller;

import com.sprinboot.web_tutorial.advices.ApiResponse;
import com.sprinboot.web_tutorial.dto.LoginDto;
import com.sprinboot.web_tutorial.dto.SignUpDto;
import com.sprinboot.web_tutorial.dto.UserDto;
import com.sprinboot.web_tutorial.service.AuthService;
import com.sprinboot.web_tutorial.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        UserDto userDto= userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){

        String token= authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);   at the time of prod
        response.addCookie(cookie);

        return ResponseEntity.ok(new ApiResponse<>(token));
    }
}
