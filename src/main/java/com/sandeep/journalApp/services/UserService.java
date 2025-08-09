package com.sandeep.journalApp.services;

import com.sandeep.journalApp.entity.User;
import com.sandeep.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public User addUser(User user){
        try{
            return this.userRepo.insert(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers(){
        return this.userRepo.findAll();
    }

    public User getUserByName(String name){
        return this.userRepo.findByName(name);
    }

    public User updateUser(User user){
        try{
           User existingUser = this.userRepo.findByName((user.getName()));
           if(existingUser ==null){
               throw new RuntimeException("User not found");
           }

           return this.userRepo.save(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
