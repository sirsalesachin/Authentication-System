package com.example.AuthenticationSystem.Service;

import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class userService {

    @Autowired
    private userRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getbyid(Long id, String email){
        User user = userRepo.findByEmail(email);
        return user;
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void saveUser2(User user){
        userRepo.save(user);
    }


    public User update(User user, String email){
        User user1 = userRepo.findByEmail(email);
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());

        return userRepo.save(user1);
    }

    public void deletebyid(String email){
        userRepo.deleteByEmail(email);
    }
}
