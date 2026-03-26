package com.example.AuthenticationSystem.Service;

import com.example.AuthenticationSystem.Entity.Journal;
import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Repo.journalRepo;
import com.example.AuthenticationSystem.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class journalService {

    @Autowired
    private journalRepo journalRepo;

    @Autowired
    private userRepo userRepo;


    public Journal create(Journal journal, String email) {
        User user = userRepo.findByEmail(email);
        journal.setUser(user);
        return journalRepo.save(journal);
    }

    public List<Journal> getUserJournals(String email) {
        User user = userRepo.findByEmail(email);
        List<Journal> journalEntries = user.getJournalEntries();
        return journalEntries;
    }

    public Journal getById(Long id) {
        return journalRepo.findById(id).orElseThrow();
    }

    public Journal update(Long id, Journal newJournal) {
        Journal j = journalRepo.findById(id).orElseThrow();
        j.setTitle(newJournal.getTitle());
        j.setContent(newJournal.getContent());
        return journalRepo.save(j);
    }

    public void delete(Long id) {
        journalRepo.deleteById(id);
    }
}
