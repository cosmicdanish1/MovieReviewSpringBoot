package com.example.SpringBoot.service;

import com.example.SpringBoot.entry.JournalEntry;
import com.example.SpringBoot.entry.User;
import com.example.SpringBoot.repository.JournalEntryRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepositry journalEntrryRepositry;

    @Autowired
    private UserService userService;
    @Transactional
    public  void  saveEntry(JournalEntry journalEntry,String userName){

        try {
            User user = userService.findByUserName(userName);
            journalEntry.setData(LocalDateTime.now());
            JournalEntry saved = journalEntrryRepositry.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occurred while saving the entry."+e);
        }

    }


    public List<JournalEntry> getAll(){
        return journalEntrryRepositry.findAll();
    }


    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntrryRepositry.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntrryRepositry.deleteById(id);
    }
}
