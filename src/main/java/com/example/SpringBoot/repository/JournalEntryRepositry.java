package com.example.SpringBoot.repository;

import com.example.SpringBoot.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepositry extends MongoRepository<JournalEntry, ObjectId> {
}
