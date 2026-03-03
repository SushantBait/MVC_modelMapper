package com.sprinboot.web_tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/employees","/error", "/auth/**").permitAll()
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService myInmemoryUserDeatilsService(){
//        UserDetails NormalUserDetails = User
//                .withUsername("sush")
//                .password("sush123")
//                .roles("USER")
//                .build();
//
//        UserDetails AdminUserDetails = User
//                .withUsername("admin")
//                .password("admin123")
//                .roles("USER")
//                .build();
//        return  new InMemoryUserDetailsManager(NormalUserDetails, AdminUserDetails);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
