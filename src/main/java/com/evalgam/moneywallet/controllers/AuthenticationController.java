package com.evalgam.moneywallet.controllers;

import com.evalgam.moneywallet.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
}
