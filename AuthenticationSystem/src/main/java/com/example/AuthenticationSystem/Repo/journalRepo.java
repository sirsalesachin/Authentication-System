package com.example.AuthenticationSystem.Repo;

import com.example.AuthenticationSystem.Entity.Journal;
import com.example.AuthenticationSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface journalRepo extends JpaRepository<Journal, Long> {
    List<Journal> findByUserId(Long userId);
    @Query("SELECT j FROM Journal j RIGHT JOIN j.user u")
    List<Journal> getAllMatchedJournal();
}
