package com.sandeep.journalApp.services;

import com.sandeep.journalApp.entity.JournalEntity;
import com.sandeep.journalApp.repositories.JournalEntryRepo;
import org.apache.coyote.BadRequestException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {
   @Autowired
    private JournalEntryRepo journalRepo;

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

   public JournalEntity addJournal(JournalEntity data){
       return this.journalRepo.insert(data);
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
