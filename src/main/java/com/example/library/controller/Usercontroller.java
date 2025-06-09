package com.example.lms.controller;

import com.example.lms.entity.User;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "User registered successfully.";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "Login successful.";
        } else {
            return "Invalid credentials.";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "User logged out.";
    }
}
