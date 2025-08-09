package com.sandeep.journalApp.controllers;

import com.sandeep.journalApp.entity.JournalApp;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class journalEntry {
    private Map<Long, JournalApp> journals  = new HashMap<>();
    private List<JournalApp> userJournals = new ArrayList<>();

    @GetMapping("list")
    public List<JournalApp> getValues(){
        return userJournals;
    }

    @GetMapping("mapList")
    public List<JournalApp> getMapList(){
        return new ArrayList<>(journals.values());
    }

    @PostMapping
    public JournalApp AddItem(@RequestBody() JournalApp data){
         this.userJournals.add(data);
        return data;
    }

    @PostMapping("addMap")
    public JournalApp AddToMap(@RequestBody() JournalApp data){
        this.journals.put(data.getId(), data);
        return data;
    }

    @GetMapping("{id}")
    public JournalApp getJournalById(@PathVariable() Long id){
        return this.userJournals.stream().filter(data->data.getId() == id).findFirst().orElse(null);
    }

    @GetMapping("map/{id}")
    public JournalApp getJournalFromMap(@PathVariable Long id) {
        return this.journals.get(id);
    }


    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable Long id){
        this.userJournals.removeIf(data->data.getId() == id);
        return true;
    }

    @DeleteMapping("map/{id}")
    public boolean deleteMapById(@PathVariable Long id){
//        this.journals.remove(id);
//        return true;
        return this.userJournals.removeIf(data -> data.getId().equals(id));
    }

    @PutMapping()
    public JournalApp updateItem(@RequestBody() JournalApp data){
        this.journals.put(data.getId(), data);
        return journals.get(data.getId());
    }

}
