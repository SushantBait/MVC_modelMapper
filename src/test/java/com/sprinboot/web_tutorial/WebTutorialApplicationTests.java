package com.sprinboot.web_tutorial;

import com.sprinboot.web_tutorial.entity.User;
import com.sprinboot.web_tutorial.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebTutorialApplicationTests {

    @Autowired
    private JwtService jwtService;

	@Test
	void contextLoads() {
        User user= new User(4L, "sushant@gmail.com", "12345");

        String token = jwtService.generateToken(user);
        System.out.println(token);

        Long id = jwtService.getUserIdFromToken(token);
        System.out.println(id);
	}

}
