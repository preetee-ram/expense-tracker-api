package com.eta.service;

import com.eta.commons.exceptions.UserNotFoundException;
import com.eta.model.User;
import com.eta.model.UserLite;
import com.eta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public UserLite authenticateUser(User user) {
        UserLite userToAuthenticate = getUserLiteById(user.getUserId());
        if(userToAuthenticate!=null && passwordEncoder.matches(userToAuthenticate.password(),user.getPassword())){
            return userToAuthenticate;
        }
        return null;
    }

    public UserLite getUserLiteById(Long id) {
        return userRepository.getUserByUserId(id);
    }
}
