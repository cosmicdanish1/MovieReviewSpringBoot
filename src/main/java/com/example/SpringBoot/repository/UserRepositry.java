package com.example.SpringBoot.repository;

import com.example.SpringBoot.entry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositry extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
