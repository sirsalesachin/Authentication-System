package com.example.AuthenticationSystem.Controller;

import com.example.AuthenticationSystem.Entity.Journal;
import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Repo.journalRepo;
import com.example.AuthenticationSystem.Repo.userRepo;
import com.example.AuthenticationSystem.Service.userService;
import com.example.AuthenticationSystem.Service.userdetailes;
import com.example.AuthenticationSystem.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
@Slf4j
public class publicController {

    @Autowired
    private userService userService;

    @Autowired
    private journalRepo journalRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("register")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<String> UserLogin(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String Token = jwtUtil.generateToken(user.getEmail());
            return new ResponseEntity<>(Token , HttpStatus.OK);
        }catch(Exception e){
            log.error("Token not create" , e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("Inner-Join")
    public List<Journal> getAll(){
        return journalRepo.getAllMatchedJournal();
    }
}
