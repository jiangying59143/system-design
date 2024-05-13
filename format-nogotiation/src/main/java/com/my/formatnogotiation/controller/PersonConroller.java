package com.my.formatnogotiation.controller;

import com.my.formatnogotiation.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonConroller {

    @GetMapping("/person")
    public Person getPerson(){
        return new Person(1L, "test", "test@email.com", 11);
    }
}
