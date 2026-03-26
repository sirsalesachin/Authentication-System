package com.example.AuthenticationSystem.Repo;

import com.example.AuthenticationSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface userRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User deleteByEmail(String email);
}
