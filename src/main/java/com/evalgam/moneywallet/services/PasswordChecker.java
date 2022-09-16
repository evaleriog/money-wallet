package com.evalgam.moneywallet.services;

import org.springframework.stereotype.Service;

@Service("passwordChecker")
public class PasswordChecker {
    //The minimum length required for password
    private final int MIN_LENGTH = 8;

    public boolean CheckPassword(String password) {
        //  at least one uppercase, one lowercase, one number, one special character, and at least 8 characters long
        if ((password.matches("(?=.*[0-9]).*"))
            && (password.matches("(?=.*[a-z]).*"))
            && (password.matches("(?=.*[A-Z]).*"))
            && (password.matches("(?=.*[~!@#$%^&*()_><=+?.,/]).*"))
            && (password.length() >= MIN_LENGTH)) 
        {
            return true;
        } else  {
            return false;
        }
    }
}
