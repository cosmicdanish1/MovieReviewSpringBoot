package com.example.SpringBoot.service;

import com.example.SpringBoot.entry.User;
import com.example.SpringBoot.repository.UserRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {


    @Autowired
    private UserRepositry userRepositry;

    public void saveEntry(User user){
        userRepositry.save(user);
    }


    public List<User> getAll(){

        return userRepositry.findAll();
    }


    public Optional<User> findById(ObjectId id){
        return userRepositry.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepositry.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepositry.findByUserName(username);
    }
}
