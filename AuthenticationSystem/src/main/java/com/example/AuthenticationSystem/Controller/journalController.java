package com.example.AuthenticationSystem.Controller;

import com.example.AuthenticationSystem.Entity.Journal;
import com.example.AuthenticationSystem.Entity.User;
import com.example.AuthenticationSystem.Repo.userRepo;
import com.example.AuthenticationSystem.Service.journalService;
import com.example.AuthenticationSystem.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("journals")
public class journalController {

    @Autowired
    private journalService journalService;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private userService userService;

    @PostMapping
    public Journal create(@RequestBody Journal journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return journalService.create(journal, email);
    }

    @GetMapping
    public List<Journal> getUserJournals() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return journalService.getUserJournals(email);
    }

    @GetMapping("/{id}")
    public List<Journal> getById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        List<Journal> collect = user.getJournalEntries().stream().filter(s -> s.getId().equals(id)).collect(Collectors.toList());
        return collect;
    }

    @PutMapping("/{id}")
    public Journal update(@PathVariable Long id, @RequestBody Journal journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        user.getJournalEntries().stream().filter(s -> s.getId().equals(id)).collect(Collectors.toList());
        userService.saveUser2(user);
        return journalService.update(id, journal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        boolean removed = user.getJournalEntries().removeIf(s -> s.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalService.delete(id);
        }
    }
}
