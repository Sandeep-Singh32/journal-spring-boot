package com.sandeep.journalApp.services;

import com.sandeep.journalApp.entity.JournalEntity;
import com.sandeep.journalApp.entity.User;
import com.sandeep.journalApp.repositories.JournalEntryRepo;
import org.apache.coyote.BadRequestException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JournalEntryService {
   @Autowired
    private JournalEntryRepo journalRepo;

   @Autowired
   private UserService userService;
   private User user;

   public List<JournalEntity> getAll(){
       return this.journalRepo.findAll();
   }

   public JournalEntity findOne(ObjectId id){
       try{
           JournalEntity je = this.journalRepo.findById(id).orElse(null);
           if(je == null){
               throw new BadRequestException("No Journal found");
           }

           return je;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   @Transactional
   public JournalEntity addJournal(JournalEntity data){
       try{
          JournalEntity journal = this.journalRepo.insert(data);
          User u = this.userService.getUserByName(data.getUser());
          if(u == null){
              throw new RuntimeException("Badmashi ho gayi ");
          }
          u.getJournals().add(journal);
          this.userService.updateUser(u);
          return journal;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   public JournalEntity updateJournal(JournalEntity data, ObjectId id){
       try{
           JournalEntity je = this.journalRepo.findById(id).orElse(null);
           if(je == null){
               throw new BadRequestException("No Journal found");
           }

           data.setId(id);
           return this.journalRepo.save(data);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   public String deleteJournal(ObjectId id){
       try{
           JournalEntity je = this.journalRepo.findById(id).orElse(null);
           if(je == null){
               throw new BadRequestException("No Journal found");
           }

           this.journalRepo.deleteById(id);
           return "Delete Successfully";
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
}
