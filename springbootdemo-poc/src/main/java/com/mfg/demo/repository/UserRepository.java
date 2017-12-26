package com.mfg.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mfg.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
}
