package com.example.AuthenticationSystem.Controller;

import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Repo.userRepo;
import com.example.AuthenticationSystem.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class adminController {

    @Autowired
    private userService userService;

    @Autowired
    private userRepo userRepo;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
}
