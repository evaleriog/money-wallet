package com.evalgam.moneywallet.models;

import javax.persistence.*;

import org.springframework.ui.Model;

@Entity
@Table(name = "users")
public class User {
    private final int MIN_LENGTH = 8;

    public User(){

    }
    public User(User copy){
        id=copy.id;
        email = copy.email;
        name = copy.name;
        password = copy.password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

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

    public boolean IsUserValid(User user, Model model){
        boolean isValid = true;
        
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
