package com.sandeep.journalApp.repositories;

import com.sandeep.journalApp.entity.JournalEntity;
import com.sandeep.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    public User findByName(String name);
}
