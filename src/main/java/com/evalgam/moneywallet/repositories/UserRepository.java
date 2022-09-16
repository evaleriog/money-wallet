package com.evalgam.moneywallet.repositories;

import com.evalgam.moneywallet.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findById(long id);
    User findByEmail(String email);
    User getByName(String name);
    User findByName(String name);
}
