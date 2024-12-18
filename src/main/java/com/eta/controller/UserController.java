package com.eta.controller;

import com.eta.model.User;
import com.eta.model.UserLite;
import com.eta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getUsername(), user.getPassword());
        return registeredUser.getUsername()+" Registered!";
    }

    @PostMapping("/login")
    public ResponseEntity<UserLite> login(@RequestBody User user) {
        UserLite loginUser = userService.authenticateUser(user);
        System.out.println("loginUser::"+loginUser);
        if(loginUser != null) {
            return ResponseEntity.ok(loginUser);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
