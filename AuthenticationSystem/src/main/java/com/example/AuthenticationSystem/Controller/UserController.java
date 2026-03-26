package com.example.AuthenticationSystem.Controller;

import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private userService userService;

    @PutMapping("update")
    public User update(@RequestBody User user1){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.update(user1,email);
    }

    @DeleteMapping("delete")
    public void deletebyid(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        userService.deletebyid(email);
    }

}
