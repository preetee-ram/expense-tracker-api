package com.eta.commons.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.View;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
        @Bean
        //authentication
        public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
            UserDetails admin = User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN")
                    .build();
            UserDetails user = User.withUsername("user")
                    .password(encoder.encode("user"))
                    .roles("USER","ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(admin, user);
           // return new UserInfoUserDetailsService();
        }


        @Bean
        //authorisation
        public SecurityFilterChain securityFilterChain(HttpSecurity http, View error) throws Exception {
            return http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth->auth.requestMatchers(
                            "/api/register","api/login").permitAll()
                            .requestMatchers("/api/expenses/**","/api/users/**").authenticated())
                    .formLogin(f->f.loginPage("")).build();

        }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, View error) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable)
                .headers(HeadersConfigurer::disable);

        return http.build();
    }

}
