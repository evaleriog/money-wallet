package com.evalgam.moneywallet.modelDTOs;

import org.springframework.ui.Model;

public class UserDTO {
    private final int MIN_LENGTH = 8;

    public UserDTO(){}

    private String email;
    private String name;
    private String password;
    private String confirmPwd;

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConfirmPwd(){
        return this.confirmPwd;
    }

    public void setConfirmPwd(String confirm){
        this.confirmPwd = confirm;
    }

    public boolean IsUserValid(UserDTO user, Model model){
        boolean isValid = true;
        if(!user.password.equals(user.confirmPwd)){
            isValid = false;
            model.addAttribute("confPasswordError", "Passwords do not match.");
        }

        if (!(user.password.matches("(?=.*[0-9]).*"))
            || !(user.password.matches("(?=.*[a-z]).*"))
            || !(user.password.matches("(?=.*[A-Z]).*"))
            || !(user.password.matches("(?=.*[~!@#$%^&*()_><=+?.,/]).*"))
            || !(user.password.length() >= MIN_LENGTH)) 
        {
            isValid = false;
            model.addAttribute("passwordError", "Password does not meet the minimum requirements.");
        }

        if(user.email == null || user.email == "" || user.email.length() < 1){
            isValid = false;
            model.addAttribute("emailError", "Email is required");
        }

        if(user.name == null || user.name == "" || user.name.length() < 1){
            isValid = false;
            model.addAttribute("nameError", "Name is required");
        }

        return isValid;
    }
}
