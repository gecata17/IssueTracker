package com.IssueTracker;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder pswEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder pswEncoder) {
        this.pswEncoder = pswEncoder;
        this.userRepository = userRepository;
    }

    public void register(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(pswEncoder.encode(user.getPassword())); //hash user's password
        userRepository.save(newUser);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
