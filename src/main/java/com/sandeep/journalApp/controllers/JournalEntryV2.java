package com.sandeep.journalApp.controllers;

import com.sandeep.journalApp.entity.JournalEntity;
import com.sandeep.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("journal")
public class JournalEntryV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("")
    public List<JournalEntity> getValues(){
        return this.journalEntryService.getAll();
    }



    //important in the response entity we can set this "?" means we can return anything and we dont even know that
    @PostMapping
    public ResponseEntity<JournalEntity> AddItem(@RequestBody() JournalEntity data){
         try{
             JournalEntity je = this.journalEntryService.addJournal(data);
             return new ResponseEntity<>(je, HttpStatus.CREATED);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @GetMapping("{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable() ObjectId id){

        try{
            JournalEntity je = this.journalEntryService.findOne(id);
            return new ResponseEntity<>(je, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable ObjectId id){
       return this.journalEntryService.deleteJournal(id);
    }

    @PutMapping("{id}")
    public JournalEntity updateItem(@RequestBody() JournalEntity data, @PathVariable ObjectId id){
        return this.journalEntryService.updateJournal(data, id);
    }
}
