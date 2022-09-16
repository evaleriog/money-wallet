package com.evalgam.moneywallet.controllers;

import com.evalgam.moneywallet.modelDTOs.UserDTO;
import com.evalgam.moneywallet.models.User;
import com.evalgam.moneywallet.repositories.UserRepository;
import com.evalgam.moneywallet.services.PasswordChecker;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Model model, @RequestParam("confirmPwd") String confirmPwd){
        if(userDao.findByEmail(user.getEmail()) != null){
            model.addAttribute("message", "A user with that email already exists.");
            return "users/create";
        }

        if(user.getPassword().equals(confirmPwd)){
            model.addAttribute("confPasswordError", "Passwords do not match.");
            return "users/create";
        }

        if(!user.IsUserValid(user, model)){
            return "users/create";
        }

        //User createdUser = this.mapUser(user);
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "login";
    }

    private User mapUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        
        return user;
    }
}
