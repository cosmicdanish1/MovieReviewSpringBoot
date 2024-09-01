package com.example.SpringBoot.controler;

import com.example.SpringBoot.entry.JournalEntry;
import com.example.SpringBoot.entry.User;
import com.example.SpringBoot.service.JournalEntryService;
import com.example.SpringBoot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControler {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
      @GetMapping("?{userName}")
      public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName)
      {
          User user = userService.findByUserName(userName);
          List<JournalEntry> all = user.getJournalEntries();
          if ( all != null && !all.isEmpty()){
              return new ResponseEntity<>(all, HttpStatus.OK);
          }

          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      }

      @PostMapping
      public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
          try {
              myEntry.setData(LocalDateTime.now());
              journalEntryService.saveEntry(myEntry);
              return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
          }catch (Exception e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }


      }


      @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalentry(@PathVariable ObjectId myId){
          Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
          if (journalEntry.isPresent()){
              return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
          }

          return new ResponseEntity<>(HttpStatus.NOT_FOUND);


      }

      @DeleteMapping("id/{myId}")
    public  ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
          journalEntryService.deleteById(myId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }


      @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
          JournalEntry old = journalEntryService.findById(id).orElse(null);
          if (old != null){
              old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
              old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
              journalEntryService.saveEntry(old);
              return  new ResponseEntity<>(old,HttpStatus.OK);

          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

}
