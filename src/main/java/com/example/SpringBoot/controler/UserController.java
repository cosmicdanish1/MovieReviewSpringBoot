package com.example.SpringBoot.controler;

import com.example.SpringBoot.entry.User;
import com.example.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User userEntry){
        userService.saveEntry(userEntry);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
     User userInDb = userService.findByUserName(user.getUserName());
     if(userInDb != null){
         userInDb.setUserName(user.getUserName());
         userInDb.setPassword(user.getPassword());
         userService.saveEntry(userInDb);
     }
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
